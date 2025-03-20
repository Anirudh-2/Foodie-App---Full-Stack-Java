package com.project.Food_App.service;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    // Create a category for a specific restaurant
    public Category createCategory(Category category) throws RestaurantException;

    // Find categories by restaurant ID
    public List<Category> findCategoryByRestaurantId(String restaurantId) throws RestaurantException;

    // Find a category by ID
    public Optional<Category> findCategoryById(String id) throws RestaurantException;
}
