package com.project.Food_App.service;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.IngredientCategory;
import com.project.Food_App.Model.IngredientsItem;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.repository.IngredientsCategoryRepository;
import com.project.Food_App.repository.IngredientsItemRepository;
import com.project.Food_App.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImplementation implements IngredientsService {

    @Autowired
    private IngredientsItemRepository ingredientsItemRepository;

    @Autowired
    private IngredientsCategoryRepository ingredientsCategoryRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public IngredientCategory createIngredientsCategory(String name, String restaurantId) throws RestaurantException {
        // Step 1: Check if the restaurant exists
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        if (!restaurantOpt.isPresent()) {
            throw new RestaurantException("Restaurant not found with ID: " + restaurantId);
        }

        // Step 2: Create and save IngredientCategory
        IngredientCategory category = new IngredientCategory();
        category.setName(name);
        category.setRestaurant(restaurantOpt.get());
        return ingredientsCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientsCategoryById(String id) throws Exception {
        Optional<IngredientCategory> categoryOpt = ingredientsCategoryRepository.findById(id);
        if (!categoryOpt.isPresent()) {
            throw new Exception("Ingredient Category not found with ID: " + id);
        }
        return categoryOpt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(String restaurantId) throws Exception {
        List<IngredientCategory> categories = ingredientsCategoryRepository.findByRestaurantId(restaurantId);
        if (categories.isEmpty()) {
            throw new Exception("No Ingredient Categories found for Restaurant with ID: " + restaurantId);
        }
        return categories;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(String restaurantId) {
        return ingredientsItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem createIngredientsItem(String restaurantId, String ingredientName, String ingredientCategoryId) throws Exception {
        // Check if restaurant exists
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        if (restaurantOpt.isEmpty()) {
            throw new RestaurantException("Restaurant not found with ID: " + restaurantId);
        }

        // Check if category exists
        Optional<IngredientCategory> categoryOpt = ingredientsCategoryRepository.findById(ingredientCategoryId);
        if (categoryOpt.isEmpty()) {
            throw new Exception("Ingredient Category not found with ID: " + ingredientCategoryId);
        }

        // Create and save the IngredientsItem
        IngredientsItem ingredientsItem = new IngredientsItem();
        ingredientsItem.setName(ingredientName);
        ingredientsItem.setRestaurant(restaurantOpt.get());
        ingredientsItem.setCategory(categoryOpt.get());
        ingredientsItem.setStockQuantity(0); // Default stock
        ingredientsItem.setInStock(true); // Default stock status
        return ingredientsItemRepository.save(ingredientsItem);
    }

    @Override
    public IngredientsItem updateStock(String id) throws Exception {
        Optional<IngredientsItem> ingredientsItemOpt = ingredientsItemRepository.findById(id);
        if (!ingredientsItemOpt.isPresent()) {
            throw new RestaurantException("Ingredient item not found with ID: " + id);
        }

        IngredientsItem ingredientsItem = ingredientsItemOpt.get();
        int updatedStockQuantity = ingredientsItem.getStockQuantity() + 10; // Increment stock by 10
        ingredientsItem.setStockQuantity(updatedStockQuantity);

        return ingredientsItemRepository.save(ingredientsItem);
    }
}
