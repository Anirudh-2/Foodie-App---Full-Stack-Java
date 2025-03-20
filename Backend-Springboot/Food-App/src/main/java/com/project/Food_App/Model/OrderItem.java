package com.project.Food_App.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "order_item")  // Optional: specify the MongoDB collection name
public class OrderItem {

    @Id
    private String id;  // MongoDB will auto-generate the _id field, but you can still use Long for the id.

    @DBRef  // Use @DBRef instead of @ManyToOne for MongoDB references
    private Food food;  // Reference to the Food document

    private int quantity;
    private Long totalPrice;

    private List<String> ingredients;

    public OrderItem( Food food, int quantity, Long totalPrice, List<String> ingredients) {
        this.food = food;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.ingredients = ingredients;
    }

    public OrderItem() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
