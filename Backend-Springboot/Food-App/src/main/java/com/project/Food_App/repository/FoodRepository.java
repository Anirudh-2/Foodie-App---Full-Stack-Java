package com.project.Food_App.repository;

import com.project.Food_App.Model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {
    List<Food> findByRestaurantId(String restaurantId);  // Ensure this method is available to get food by restaurantId

    // Custom search method for food by name or category
    @Query("{ '$or': [ { 'name': { '$regex': ?0, $options: 'i' } }, { 'foodCategory.name': { '$regex': ?0, $options: 'i' } } ] }")
    List<Food> searchByNameOrCategory(String keyword);

    @Query("{'name': { '$regex': ?0, $options: 'i' }}")  // Case-insensitive search
    List<Food> searchByName(String keyword);

    Optional<Food> findById(String foodId);
}
