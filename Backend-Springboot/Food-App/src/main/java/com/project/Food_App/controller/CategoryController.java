package com.project.Food_App.controller;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Category;
import com.project.Food_App.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/category")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            Category createdCategory = categoryService.createCategory(category);
            return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
        } catch (RestaurantException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the category.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/restaurant/{restaurantId}")
    public ResponseEntity<?> getCategoriesByRestaurant(@PathVariable String restaurantId) {
        try {
            List<Category> categories = categoryService.findCategoryByRestaurantId(restaurantId);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (RestaurantException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching categories.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
