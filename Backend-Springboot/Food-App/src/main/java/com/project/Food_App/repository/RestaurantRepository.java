package com.project.Food_App.repository;

import com.project.Food_App.Model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    // Find a restaurant by its ID (using String)
    Optional<Restaurant> findById(String id);

    // Find restaurants by owner (userName)
   // Restaurant findByUsername(String username);
    // Find restaurants by name (for search functionality)
    List<Restaurant> findByNameContaining(String name);
    List<Restaurant> findByNameContainingIgnoreCase(String name);
    Optional<Restaurant> findByUsername(String username);
}
