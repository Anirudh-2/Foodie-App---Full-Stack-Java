package com.project.Food_App.repository;

import com.project.Food_App.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findOrdersByUserName(String userName);  // This is a custom query method

    List<Order> findOrdersByRestaurantId(String restaurantId);

    // Corrected the method signature to use orderStatus instead of status
    List<Order> findByRestaurantIdAndOrderStatus(String restaurantId, String orderStatus);  // Updated to match the correct field name

    List<Order> findByRestaurantId(String restaurantId);

    Optional<Order> findById(String orderId);

    void deleteById(String orderId);
}
