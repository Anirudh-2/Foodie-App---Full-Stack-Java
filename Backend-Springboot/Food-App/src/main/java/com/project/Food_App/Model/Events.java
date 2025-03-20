package com.project.Food_App.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")  // Optional: specify collection name
public class Events {

    @Id
    private String id;

    private String image;

    private String startedAt;

    private String endsAt;

    private String name;

    @DBRef
    private Restaurant restaurant;  // Use @DBRef instead of @ManyToOne

    private String location;

    public Events() {
    }

    public Events(String id, String image, String startedAt, String endsAt, String name, Restaurant restaurant, String location) {
        this.id = id;
        this.image = image;
        this.startedAt = startedAt;
        this.endsAt = endsAt;
        this.name = name;
        this.restaurant = restaurant;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(String endsAt) {
        this.endsAt = endsAt;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
