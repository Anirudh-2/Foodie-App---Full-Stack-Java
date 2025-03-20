package com.project.Food_App.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")  // Optional: specify the MongoDB collection name
public class Review {

    @Id
    private String id;  // MongoDB will automatically generate the _id field if not provided

    private String userName;  // Corrected: no need for @DBRef for userName

    @DBRef  // MongoDB reference for restaurant
    private Restaurant restaurant;

    private String message;

    private double rating;

    private LocalDateTime createdAt;  // MongoDB stores LocalDateTime as Date (no need for @Temporal)

    // Default constructor
    public Review() {
    }

    // Constructor for initializing review object
    public Review(String id, String userName, Restaurant restaurant, String message, double rating, LocalDateTime createdAt) {
        this.id = id;
        this.userName = userName;
        this.restaurant = restaurant;
        this.message = message;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
