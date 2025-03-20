package com.project.Food_App.request;


public class ReviewRequest {

    private String restaurantId;

    private double rating;

    private String reviewText;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String  restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
