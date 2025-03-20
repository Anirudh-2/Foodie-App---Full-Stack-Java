package com.project.Food_App.service;

import com.project.Food_App.Exception.ReviewException;
import com.project.Food_App.Model.Review;
import com.project.Food_App.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    // Submit a review. Now accepts userName directly instead of User object.
    public Review submitReview(ReviewRequest review, String userName) throws ReviewException;

    // Delete a review by its ID.
    public void deleteReview(String reviewId) throws ReviewException;

    // Calculate the average rating of a list of reviews.
    public double calculateAverageRating(List<Review> reviews);
}
