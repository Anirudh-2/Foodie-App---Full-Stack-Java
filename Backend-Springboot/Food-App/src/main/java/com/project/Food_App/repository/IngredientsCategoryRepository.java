package com.project.Food_App.repository;

import com.project.Food_App.Model.IngredientCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientsCategoryRepository extends MongoRepository<IngredientCategory, String> {

    // Custom query method to find Ingredient Categories by Restaurant ID
    List<IngredientCategory> findByRestaurantId(String restaurantId);

    Optional<IngredientCategory> findById(String ingredientCategoryId);
}
