package com.project.Food_App.controller;


import com.project.Food_App.Exception.FoodException;
import com.project.Food_App.Model.Food;
import com.project.Food_App.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class MenuItemController {
    @Autowired
    private FoodService menuItemService;

//    @Autowired
//    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam String name)  {
        List<Food> menuItem = menuItemService.searchFood(name);
        return ResponseEntity.ok(menuItem);
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getMenuItemByRestaurantId(
            @PathVariable String restaurantId,
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonveg,
            @RequestParam(required = false) String food_category) throws FoodException {
        List<Food> menuItems= menuItemService.getRestaurantsFood(
                restaurantId,vegetarian,nonveg,seasonal,food_category);
        return ResponseEntity.ok(menuItems);
    }



}

