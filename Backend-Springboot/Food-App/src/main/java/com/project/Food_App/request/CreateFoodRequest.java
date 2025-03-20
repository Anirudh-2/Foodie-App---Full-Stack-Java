package com.project.Food_App.request;

import com.project.Food_App.Model.IngredientsItem;

import java.util.List;

public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;
    private String categoryId; // Change from Category object to categoryId (String)
    private List<String> images;
    private String restaurantId; // Restaurant ID as String
    private boolean vegetarian;
    private boolean seasonal;
    private boolean available;  // Add this field
    private List<String> id;

    // Constructor, getters, and setters
    public CreateFoodRequest(String name, String description, Long price, String categoryId, List<String> images, String restaurantId, boolean vegetarian, boolean seasonal, boolean available, List<String> id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.images = images;
        this.restaurantId = restaurantId;
        this.vegetarian = vegetarian;
        this.seasonal = seasonal;
        this.available = available;
        this.id = id;
    }

    public CreateFoodRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCategoryId() { // Getter for categoryId
        return categoryId;
    }

    public void setCategoryId(String categoryId) { // Setter for categoryId
        this.categoryId = categoryId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isSeasonal() {
        return seasonal;
    }

    public void setSeasonal(boolean seasonal) {
        this.seasonal = seasonal;
    }

    public boolean isAvailable() {  // Add this getter method
        return available;
    }

    public void setAvailable(boolean available) {  // Add this setter method
        this.available = available;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }
}
