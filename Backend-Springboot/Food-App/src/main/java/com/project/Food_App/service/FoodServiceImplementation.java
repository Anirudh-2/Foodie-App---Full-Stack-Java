package com.project.Food_App.service;

import com.project.Food_App.Exception.FoodException;
import com.project.Food_App.Model.Category;
import com.project.Food_App.Model.Food;
import com.project.Food_App.Model.IngredientsItem;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.repository.FoodRepository;
import com.project.Food_App.repository.IngredientsCategoryRepository;
import com.project.Food_App.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImplementation implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private IngredientsService ingredientService;

    @Autowired
    private IngredientsCategoryRepository ingredientCategoryRepo;

    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant, List<IngredientsItem> ingredientsItemList) {
        Food food = new Food();
        food.setName(request.getName());
        food.setDescription(request.getDescription());
        food.setPrice(request.getPrice());
        food.setFoodCategory(category);  // Set the category
        food.setRestaurant(restaurant);  // Set the restaurant
        food.setImages(request.getImages());
        food.setVegetarian(request.isVegetarian());
        food.setSeasonal(request.isSeasonal());
        food.setIngredients(ingredientsItemList);

        return foodRepository.save(food);  // Save the food object in the database
    }


    @Override
    public void deleteFood(String foodId) throws FoodException {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);  // If you want to set the restaurant to null before deleting, do this.
        foodRepository.delete(food);  // This deletes the food from the DB
    }

    @Override
    public List<Food> getRestaurantsFood(String restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) throws FoodException {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian) {
            foods = filterByVegetarian(foods, isVegetarian);
        }
        if (isNonveg) {
            foods = filterByNonveg(foods, isNonveg);
        }
        if (isSeasonal) {
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if (foodCategory != null && !foodCategory.equals("")) {
            foods = filterByFoodCategory(foods, foodCategory);
        }

        return foods;
    }

    @Override
    public List<Food> searchFood(String keyword) {
        List<Food> items = new ArrayList<>();
        if (keyword != null && !keyword.isEmpty()) {
            // Search by name only
            items = foodRepository.searchByName(keyword);
        }
        return items;
    }

    @Override
    public Food findFoodById(String foodId) throws FoodException {
        Optional<Food> food = foodRepository.findById(foodId);
        if (food.isPresent()) {
            return food.get();
        }
        throw new FoodException("Food with id " + foodId + " not found");
    }

    @Override
    public Food updateAvailibilityStatus(String foodId) throws FoodException {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        foodRepository.save(food);
        return food;
    }

    // Additional helper methods for filtering
    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian() == isVegetarian).collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food -> !food.isVegetarian() == isNonveg).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByFoodCategory(List<Food> foods, String category) {
        return foods.stream().filter(food -> food.getFoodCategory().getName().equals(category)).collect(Collectors.toList());
    }
}
