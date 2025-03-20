package com.project.Food_App.controller;

import com.project.Food_App.Exception.ReviewException;
import com.project.Food_App.Model.Review;
import com.project.Food_App.request.ReviewRequest;
import com.project.Food_App.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Create a new review
    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest review) throws ReviewException {
        // Retrieve authenticated user (this can be adjusted as needed)
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Review submittedReview = reviewService.submitReview(review, user);
        return new  ResponseEntity<>(submittedReview, HttpStatus.CREATED);
    }

    // Delete a review by ID
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable String reviewId) throws ReviewException {
        try {
            reviewService.deleteReview(reviewId);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        } catch (ReviewException e) {
            return new ResponseEntity<>("Error deleting review: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Calculate average rating
    @GetMapping("/average-rating")
    public ResponseEntity<Double> calculateAverageRating(@RequestBody List<Review> reviews) {
        double averageRating = reviewService.calculateAverageRating(reviews);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }
}
