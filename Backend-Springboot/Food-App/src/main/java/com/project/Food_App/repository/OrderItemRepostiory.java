package com.project.Food_App.repository;

import com.project.Food_App.Model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepostiory extends MongoRepository<OrderItem,String> {
}
