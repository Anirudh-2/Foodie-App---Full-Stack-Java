package com.project.Food_App.service;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Category;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.dto.RestaurantDto;
import com.project.Food_App.request.CreateRestaurantRequest;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    Restaurant createRestaurant(CreateRestaurantRequest req) throws RestaurantException;

    Restaurant updateRestaurant(String restaurantId, CreateRestaurantRequest updatedRestaurant) throws RestaurantException;

    void deleteRestaurant(String restaurantId) throws RestaurantException;

    List<Restaurant> getAllRestaurants();

    List<Restaurant> searchRestaurant(String keyword);

    Optional<Restaurant> findRestaurantById(String id) throws RestaurantException;

    Restaurant getRestaurantByUser() throws RestaurantException;

    RestaurantDto addToFavorites(String restaurantId) throws RestaurantException;

    Restaurant updateRestaurantStatus(String id, String status) throws RestaurantException;

    List<Category> getCategoriesByRestaurantId(String id) throws RestaurantException;
}
