package com.project.Food_App.service;

import com.project.Food_App.Exception.FoodException;
import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Category;
import com.project.Food_App.Model.Food;
import com.project.Food_App.Model.IngredientsItem;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant, List<IngredientsItem> ingredientsItemList) throws FoodException, RestaurantException;

    void deleteFood(String foodId) throws FoodException;

    public List<Food> getRestaurantsFood(String restaurantId,
                                         boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) throws FoodException;

    public List<Food> searchFood(String keyword);

    public Food findFoodById(String foodId) throws FoodException;

    public Food updateAvailibilityStatus(String foodId) throws FoodException;
}
