package com.project.Food_App.controller;

import com.project.Food_App.Exception.CartException;
import com.project.Food_App.Exception.OrderException;
import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Order;
import com.project.Food_App.Model.PaymentResponse;
import com.project.Food_App.request.CreateOrderRequest;
import com.project.Food_App.service.OrderService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody CreateOrderRequest order)
            throws RestaurantException, CartException, StripeException, OrderException {

        // Get user info from authentication context
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Log user info for debugging
        System.out.println("Request received for user: " + user);

        // Check if the order is valid
        if (order != null) {
            PaymentResponse res = orderService.createOrder(order, user);
            return ResponseEntity.ok(res);
        } else {
            throw new OrderException("Please provide a valid request body.");
        }
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getAllUserOrders() throws OrderException {
        // Get user info from authentication context
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user != null) {
            List<Order> userOrders = orderService.getUserOrders(user);
            return ResponseEntity.ok(userOrders);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
