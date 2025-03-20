package com.project.Food_App.repository;

import com.project.Food_App.Model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepostiory extends MongoRepository<Notification, String> {
    List<Notification> findByCustomer(String customer); // Query by customer (userName)
}
