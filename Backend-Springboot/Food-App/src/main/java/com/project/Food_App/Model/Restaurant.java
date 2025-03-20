package com.project.Food_App.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "restaurants")  // Optional: specify the MongoDB collection name
public class Restaurant {

    @Id
    private String id;  // MongoDB will auto-generate the _id field, but you can still use Long for the id.

    private String username;

    private String name;
    private String description;
    private String cuisineType;

    @DBRef  // Use @DBRef for referencing the Address document
    private Address address;

    @Field  // Embedding ContactInformation directly in the document
    private ContactInformation contactInformation;

    private String openingHours;

    private List<Review> reviews = new ArrayList<>();

    @DBRef
    @JsonBackReference
    private List<Order> orders = new ArrayList<>();

    private int numRating;

    private List<String> images;  // MongoDB stores lists directly without @ElementCollection

    private LocalDateTime registrationDate;  // MongoDB stores LocalDateTime as Date

    private boolean isOpen;

    @DBRef  // One-to-many relationship, use @DBRef for MongoDB
    private List<Food> foods = new ArrayList<>();

    private String status;

    private List<String> favoriteUsers = new ArrayList<>();

    @DBRef
    private List<Category> categories = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(String id, String username, String name, String description, String cuisineType, Address address, ContactInformation contactInformation, String openingHours, List<Review> reviews, List<Order> orders, int numRating,List<Category> categories, List<String> images, LocalDateTime registrationDate, boolean isOpen, List<Food> foods, String status, List<String> favoriteUsers) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.description = description;
        this.cuisineType = cuisineType;
        this.address = address;
        this.contactInformation = contactInformation;
        this.openingHours = openingHours;
        this.reviews = reviews;
        this.orders = orders;
        this.numRating = numRating;
        this.images = images;
        this.registrationDate = registrationDate;
        this.isOpen = isOpen;
        this.foods = foods;
        this.status = status;
        this.favoriteUsers = favoriteUsers;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getNumRating() {
        return numRating;
    }

    public void setNumRating(int numRating) {
        this.numRating = numRating;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getFavoriteUsers() {
        return favoriteUsers;
    }

    public void setFavoriteUsers(List<String> favoriteUsers) {
        this.favoriteUsers = favoriteUsers;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
