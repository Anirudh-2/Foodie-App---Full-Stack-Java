package com.project.Food_App.repository;

import com.project.Food_App.Model.Events;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Events, String> {

    // Add this custom query to find events by restaurant ID
    List<Events> findEventsByRestaurantId(String restaurantId);
}
