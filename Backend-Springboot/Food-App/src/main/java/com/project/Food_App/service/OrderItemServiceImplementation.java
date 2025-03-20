package com.project.Food_App.service;

import com.project.Food_App.Model.OrderItem;
import com.project.Food_App.repository.OrderItemRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImplementation implements OrderItemService {

    @Autowired
    private OrderItemRepostiory orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        // Create a new OrderItem with the provided details
        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setFood(orderItem.getFood());  // Set the food reference
        newOrderItem.setQuantity(orderItem.getQuantity());  // Set quantity
        newOrderItem.setTotalPrice(orderItem.getTotalPrice());  // Set the total price
        newOrderItem.setIngredients(orderItem.getIngredients());  // Set ingredients

        // Save the new OrderItem to the repository
        return orderItemRepository.save(newOrderItem);
    }
}
