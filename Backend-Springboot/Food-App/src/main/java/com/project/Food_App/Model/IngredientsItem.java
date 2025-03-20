package com.project.Food_App.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ingredients_item")  // Optional: specify collection name
public class IngredientsItem {

    @Id
    private String id;  // MongoDB will automatically generate the _id field, but if you need Long, use it.

    private String name;

    @DBRef  // Use @DBRef instead of @ManyToOne for MongoDB reference
    private IngredientCategory category;

    @DBRef  // Use @DBRef instead of @ManyToOne for MongoDB reference
    private Restaurant restaurant;

    private boolean inStock = true;  // Fixed typo from "inStoke" to "inStock"

    private int stockQuantity;

    public IngredientsItem() {
    }

    public IngredientsItem(String id, String name, IngredientCategory category, Restaurant restaurant, boolean inStock, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.restaurant = restaurant;
        this.inStock = inStock;
        this.stockQuantity = stockQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public void setCategory(IngredientCategory category) {
        this.category = category;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
