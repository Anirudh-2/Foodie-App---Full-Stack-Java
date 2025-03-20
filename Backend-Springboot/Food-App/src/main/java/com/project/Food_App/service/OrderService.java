package com.project.Food_App.service;

import com.project.Food_App.Exception.CartException;
import com.project.Food_App.Exception.OrderException;
import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Order;
import com.project.Food_App.Model.PaymentResponse;
import com.project.Food_App.request.CreateOrderRequest;
import com.stripe.exception.StripeException;

import java.util.List;

public interface OrderService {
    PaymentResponse createOrder(CreateOrderRequest order, String userName) throws RestaurantException, CartException, StripeException;

    Order updateOrder(String orderId, String orderStatus) throws OrderException;

    void cancelOrder(String orderId) throws OrderException;

    List<Order> getUserOrders(String userName) throws OrderException;

    List<Order> getOrdersOfRestaurant(String restaurantId, String orderStatus) throws OrderException, RestaurantException;

    List<Order> findOrdersByRestaurant(String restaurantId, String orderStatus);
}
