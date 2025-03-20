package com.project.Food_App.repository;

import com.project.Food_App.Model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findByRestaurantId(String restaurantId);  // Fetch categories by restaurant ID

    @Override
    Optional<Category> findById(String s);
}
