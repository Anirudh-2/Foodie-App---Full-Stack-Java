package com.project.Food_App.service;

import com.project.Food_App.Model.IngredientCategory;
import com.project.Food_App.Model.IngredientsItem;
import com.project.Food_App.Exception.RestaurantException;

import java.util.List;

public interface IngredientsService {

    IngredientCategory createIngredientsCategory(String name, String restaurantId) throws RestaurantException;

    IngredientCategory findIngredientsCategoryById(String id) throws Exception;

    List<IngredientCategory> findIngredientsCategoryByRestaurantId(String restaurantId) throws Exception;

    List<IngredientsItem> findRestaurantsIngredients(String restaurantId);

    IngredientsItem createIngredientsItem(String restaurantId, String ingredientName, String ingredientCategoryId) throws Exception;

    IngredientsItem updateStock(String id) throws Exception;
}
