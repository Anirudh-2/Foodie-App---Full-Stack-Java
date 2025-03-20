package com.project.Food_App.service;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Category;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.repository.CategoryRepository;
import com.project.Food_App.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) throws RestaurantException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Restaurant> restaurantOpt = restaurantRepository.findByUsername(username);
        if (restaurantOpt.isEmpty()) {
            throw new RestaurantException("Restaurant not found for user: " + username);
        }

        category.setRestaurant(restaurantOpt.get());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(String id) throws RestaurantException {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(id);
        if (restaurantOpt.isEmpty()) {
            throw new RestaurantException("Restaurant not found with id: " + id);
        }

        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Optional<Category> findCategoryById(String id) throws RestaurantException {
        Optional<Category> opt = categoryRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RestaurantException("Category not found with id: " + id);
        }
        return opt;
    }
}
