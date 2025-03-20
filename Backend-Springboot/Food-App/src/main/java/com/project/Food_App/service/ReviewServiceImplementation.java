package com.project.Food_App.service;

import com.project.Food_App.Exception.ReviewException;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.Model.Review;
import com.project.Food_App.repository.RestaurantRepository;
import com.project.Food_App.repository.ReviewRepository;
import com.project.Food_App.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Review submitReview(ReviewRequest reviewRequest, String userName) throws ReviewException {
        // Create a new Review object
        Review review = new Review();

        // Fetch the restaurant based on the restaurantId from the ReviewRequest
        Optional<Restaurant> restaurant = restaurantRepository.findById(reviewRequest.getRestaurantId());
        if (restaurant.isPresent()) {
            review.setRestaurant(restaurant.get());
        } else {
            throw new ReviewException("Restaurant not found with ID " + reviewRequest.getRestaurantId());
        }

        // Set the review fields based on the ReviewRequest and userName
        review.setUserName(userName);  // Corrected method for setting userName
        review.setMessage(reviewRequest.getReviewText());  // Review text
        review.setRating(reviewRequest.getRating());  // Review rating
        review.setCreatedAt(LocalDateTime.now());  // Timestamp for when the review is created

        // Save and return the review
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(String reviewId) throws ReviewException {
        // Find the review by ID
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        // Delete the review if it exists, otherwise throw an exception
        if (optionalReview.isPresent()) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new ReviewException("Review with ID " + reviewId + " not found");
        }
    }

    @Override
    public double calculateAverageRating(List<Review> reviews) {
        double totalRating = 0;

        // Sum up all ratings from the reviews
        for (Review review : reviews) {
            totalRating += review.getRating();
        }

        // Return average rating, or 0 if no reviews exist
        if (reviews.size() > 0) {
            return totalRating / reviews.size();
        } else {
            return 0;
        }
    }
}
