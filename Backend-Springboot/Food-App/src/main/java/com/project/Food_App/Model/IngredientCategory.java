package com.project.Food_App.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "ingredient_category")  // Optional: specify collection name
public class IngredientCategory {

    @Id
    private String id;

    private String name;

    @DBRef  // Use @DBRef instead of @ManyToOne
    private Restaurant restaurant;  // Reference to Restaurant (MongoDB)

    @DBRef  // Use @DBRef instead of @OneToMany
    private List<IngredientsItem> ingredients = new ArrayList<>();  // Reference to IngredientsItem (MongoDB)

    public IngredientCategory(String id, String name, Restaurant restaurant, List<IngredientsItem> ingredients) {
        this.id = id;
        this.name = name;
        this.restaurant = restaurant;
        this.ingredients = ingredients;
    }

    public IngredientCategory() {
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<IngredientsItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsItem> ingredients) {
        this.ingredients = ingredients;
    }
}
