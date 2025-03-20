package com.project.Food_App.controller;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.request.CreateRestaurantRequest;
import com.project.Food_App.response.ApiResponse;
import com.project.Food_App.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<?> createRestaurant(@RequestBody CreateRestaurantRequest req) {
        try {
            String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Restaurant restaurant = restaurantService.createRestaurant(req);
            return ResponseEntity.ok(restaurant);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating restaurant: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String id,
                                                       @RequestBody CreateRestaurantRequest req) {
        try {
            Restaurant restaurant = restaurantService.updateRestaurant(id, req);
            return ResponseEntity.ok(restaurant);
        } catch (RestaurantException e) {
            return ResponseEntity.status(400).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRestaurantById(@PathVariable("id") String restaurantId) {
        try {
            restaurantService.deleteRestaurant(restaurantId);
            ApiResponse res = new ApiResponse("Restaurant Deleted Successfully with id: " + restaurantId, true);
            return ResponseEntity.ok(res);
        } catch (RestaurantException e) {
            return ResponseEntity.status(404).body(new ApiResponse("Restaurant not found", false));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Error deleting restaurant: " + e.getMessage(), false));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@PathVariable String id, @RequestBody Map<String, String> statusData) {
        try {
            String status = statusData.get("status");
            Restaurant restaurant = restaurantService.updateRestaurantStatus(id, status);
            return ResponseEntity.ok(restaurant);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantsByUserName() {
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Restaurant restaurant = restaurantService.getRestaurantByUser();
            return ResponseEntity.ok(restaurant);
        } catch (RestaurantException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        try {
            List<Restaurant> restaurants = restaurantService.getAllRestaurants();
            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
