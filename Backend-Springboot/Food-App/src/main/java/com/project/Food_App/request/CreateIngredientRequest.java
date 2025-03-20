package com.project.Food_App.request;


public class CreateIngredientRequest {

    private String restaurantId;
    private String name;
    private String ingredientCategoryId;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredientCategoryId() {
        return ingredientCategoryId;
    }

    public void setIngredientCategoryId(String ingredientCategoryId) {
        this.ingredientCategoryId = ingredientCategoryId;
    }
}
