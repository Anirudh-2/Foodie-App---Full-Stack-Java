package com.project.Food_App.controller;

import com.project.Food_App.Exception.FoodException;
import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Category;
import com.project.Food_App.Model.Food;
import com.project.Food_App.Model.IngredientsItem;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.repository.IngredientsItemRepository;
import com.project.Food_App.request.CreateFoodRequest;
import com.project.Food_App.service.CategoryService;
import com.project.Food_App.service.FoodService;
import com.project.Food_App.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/food")
public class AdminMenuItemController {

    @Autowired
    private FoodService menuItemService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IngredientsItemRepository ingredientsItemRepository;

    @PostMapping()
    public ResponseEntity<Food> createItem(@RequestBody CreateFoodRequest item) throws FoodException, RestaurantException {

        // Fetch the restaurant by ID
        Optional<Restaurant> restaurantOptional = restaurantService.findRestaurantById(item.getRestaurantId());
        if (restaurantOptional.isEmpty()) {
            throw new RestaurantException("Restaurant not found");
        }
        Restaurant restaurant = restaurantOptional.get();

        // Fetch the category by ID
        Optional<Category> categoryOptional = categoryService.findCategoryById(item.getCategoryId());
        if (categoryOptional.isEmpty()) {
            throw new FoodException("Category not found");
        }
        Category category = categoryOptional.get();

        List<IngredientsItem> ingredientsItemList = ingredientsItemRepository.findAllById(item.getId());

        // Create the food item and associate it with the restaurant and category
        Food menuItem = menuItemService.createFood(item, category, restaurant, ingredientsItemList);
        return ResponseEntity.ok(menuItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable String id) throws FoodException {
        menuItemService.deleteFood(id);
        return ResponseEntity.ok("Menu item deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Food>> getMenuItemByName(@RequestParam String name) {
        List<Food> menuItem = menuItemService.searchFood(name);
        return ResponseEntity.ok(menuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateAvailabilityStatus(@PathVariable String id) throws FoodException {
        Food menuItems = menuItemService.updateAvailibilityStatus(id);
        return ResponseEntity.ok(menuItems);
    }
}
