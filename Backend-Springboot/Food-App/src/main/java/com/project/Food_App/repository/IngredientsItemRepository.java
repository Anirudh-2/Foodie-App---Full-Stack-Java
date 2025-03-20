package com.project.Food_App.repository;

import com.project.Food_App.Model.IngredientsItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientsItemRepository extends MongoRepository<IngredientsItem, String> {
    List<IngredientsItem> findByRestaurantId(String restaurantId);  // Change Long to String

    Optional<IngredientsItem> findById(String id);  // Change Long to String for finding by ID
}
