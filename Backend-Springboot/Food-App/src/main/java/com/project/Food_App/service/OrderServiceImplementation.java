package com.project.Food_App.service;

import com.project.Food_App.Exception.CartException;
import com.project.Food_App.Exception.OrderException;
import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.*;
import com.project.Food_App.repository.*;
import com.project.Food_App.request.CreateOrderRequest;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {

    private final PaymentService paymentService;
    private final CartService cartService;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepostiory orderItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final NotificationService notificationService;

    @Autowired
    public OrderServiceImplementation(PaymentService paymentService,
                                      @Lazy CartService cartService,
                                      AddressRepository addressRepository,
                                      OrderRepository orderRepository,
                                      OrderItemRepostiory orderItemRepository,
                                      RestaurantRepository restaurantRepository,
                                      NotificationService notificationService) {
        this.paymentService = paymentService;
        this.cartService = cartService;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.restaurantRepository = restaurantRepository;
        this.notificationService = notificationService;
    }

    @Override
    public PaymentResponse createOrder(CreateOrderRequest order, String userName) throws RestaurantException, CartException, StripeException {
        Address savedAddress = addressRepository.save(order.getDeliveryAddress());

        Restaurant restaurant = restaurantRepository.findById(order.getRestaurantId())
                .orElseThrow(() -> new RestaurantException("Restaurant not found with ID " + order.getRestaurantId()));

        Cart cart = cartService.findCartByUserId(userName);
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem(
                    cartItem.getFood(),
                    cartItem.getQuantity(),
                    cartItem.getFood().getPrice() * cartItem.getQuantity(),
                    cartItem.getIngredients()
            );
            orderItems.add(orderItemRepository.save(orderItem));
        }

        Double totalPrice = cartService.calculateCartTotals(cart.getId());

        // Correcting the Order constructor parameter order
        Order createdOrder = new Order(userName, new Date(), "PENDING", totalPrice, restaurant, orderItems, savedAddress);
        Order savedOrder = orderRepository.save(createdOrder);

        restaurant.getOrders().add(savedOrder);
        restaurantRepository.save(restaurant);

        return paymentService.generatePaymentLink(savedOrder);
    }

    @Override
    public Order updateOrder(String orderId, String orderStatus) throws OrderException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Order not found with id " + orderId));

        if (Arrays.asList("OUT_FOR_DELIVERY", "DELIVERED", "Ready For Pickup", "PENDING").contains(orderStatus)) {
            order.setOrderStatus(orderStatus);
            notificationService.sendOrderStatusNotification(order);
            return orderRepository.save(order);
        } else {
            throw new OrderException("Invalid Order Status");
        }
    }

    @Override
    public void cancelOrder(String orderId) throws OrderException {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderException("Order not found with id " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrders(String userName) {
        return orderRepository.findOrdersByUserName(userName);
    }

    @Override
    public List<Order> getOrdersOfRestaurant(String restaurantId, String orderStatus) throws OrderException, RestaurantException {
        List<Order> orders = orderRepository.findOrdersByRestaurantId(restaurantId);
        return orderStatus != null ?
                orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList())
                : orders;
    }

    @Override
    public List<Order> findOrdersByRestaurant(String restaurantId, String orderStatus) {
        return orderStatus != null ?
                orderRepository.findByRestaurantIdAndOrderStatus(restaurantId, orderStatus)
                : orderRepository.findByRestaurantId(restaurantId);
    }
}
