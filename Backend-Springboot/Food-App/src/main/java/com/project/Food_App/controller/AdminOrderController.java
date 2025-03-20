package com.project.Food_App.controller;

import com.project.Food_App.Exception.OrderException;
import com.project.Food_App.Model.Order;
import com.project.Food_App.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    private final OrderService orderService;

    @Autowired
    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId) throws OrderException {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order deleted with id: " + orderId);
    }

    @GetMapping("/order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getRestaurantOrders(
            @PathVariable String restaurantId,
            @RequestParam(required = false) String orderStatus,
            @RequestHeader("Authorization") String jwt) {

        if (jwt == null || jwt.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (restaurantId == null || !restaurantId.matches("[0-9a-fA-F]{24}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Order> orders = orderService.findOrdersByRestaurant(restaurantId, orderStatus);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/orders/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrders(@PathVariable String orderId, @PathVariable String orderStatus) throws OrderException {
        Order order = orderService.updateOrder(orderId, orderStatus);
        return ResponseEntity.ok(order);
    }
}
