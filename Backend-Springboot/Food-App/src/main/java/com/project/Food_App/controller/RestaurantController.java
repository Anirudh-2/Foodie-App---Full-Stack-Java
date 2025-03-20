package com.project.Food_App.controller;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.dto.RestaurantDto;
import com.project.Food_App.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam String keyword) {
        return ResponseEntity.ok(restaurantService.searchRestaurant(keyword));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String id) throws RestaurantException {
        return ResponseEntity.ok(restaurantService.findRestaurantById(id)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with id: " + id)));
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> getRestaurantByUser() throws RestaurantException {
        return ResponseEntity.ok(restaurantService.getRestaurantByUser());
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(@PathVariable String id) throws RestaurantException {
        return ResponseEntity.ok(restaurantService.addToFavorites(id));
    }
    @GetMapping("/{id}/categories")
    public ResponseEntity<?> getRestaurantCategories(@PathVariable String id) throws RestaurantException {
        // Fetch categories related to the restaurant from your service layer
        return ResponseEntity.ok(restaurantService.getCategoriesByRestaurantId(id));
    }

}
