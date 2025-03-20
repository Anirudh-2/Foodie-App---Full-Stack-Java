package com.project.Food_App.service;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Address;
import com.project.Food_App.Model.Category;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.dto.RestaurantDto;
import com.project.Food_App.repository.AddressRepository;
import com.project.Food_App.repository.RestaurantRepository;
import com.project.Food_App.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImplementation implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req) throws RestaurantException {
        try {
            Address address = new Address();
            address.setCity(req.getAddress().getCity());
            address.setCountry(req.getAddress().getCountry());
            address.setFullName(req.getAddress().getFullName());
            address.setPostalCode(req.getAddress().getPostalCode());
            address.setState(req.getAddress().getState());
            address.setStreetAddress(req.getAddress().getStreetAddress());

            Address savedAddress = addressRepository.save(address);

            Restaurant restaurant = new Restaurant();
            restaurant.setAddress(savedAddress);
            restaurant.setContactInformation(req.getContactInformation());
            restaurant.setCuisineType(req.getCuisineType());
            restaurant.setDescription(req.getDescription());
            restaurant.setImages(req.getImages());
            restaurant.setName(req.getName());
            restaurant.setOpeningHours(req.getOpeningHours());
            restaurant.setRegistrationDate(req.getRegistrationDate());
            restaurant.setOpen(true);

            return restaurantRepository.save(restaurant);
        } catch (Exception e) {
            throw new RestaurantException("Failed to create restaurant: " + e.getMessage());
        }
    }

    @Override
    public Restaurant updateRestaurant(String restaurantId, CreateRestaurantRequest updatedRestaurant) throws RestaurantException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with id: " + restaurantId));

        if (updatedRestaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if (updatedRestaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if (updatedRestaurant.getName() != null) {
            restaurant.setName(updatedRestaurant.getName());
        }
        if (updatedRestaurant.getOpeningHours() != null) {
            restaurant.setOpeningHours(updatedRestaurant.getOpeningHours());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(String restaurantId) throws RestaurantException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with id: " + restaurantId));
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public Optional<Restaurant> findRestaurantById(String id) throws RestaurantException {
        return Optional.ofNullable(restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + id + " not found.")));
    }

    @Override
    public Restaurant getRestaurantByUser() throws RestaurantException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getName() == null) {
            throw new RestaurantException("User is not authenticated.");
        }

        String username = auth.getName();

        return restaurantRepository.findByUsername(username)
                .orElseThrow(() -> new RestaurantException("Restaurant not found for user: " + username));
    }

    @Override
    public RestaurantDto addToFavorites(String restaurantId) throws RestaurantException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getName() == null) {
            throw new RestaurantException("User is not authenticated.");
        }

        String userId = auth.getName();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with id: " + restaurantId));

        if (restaurant.getFavoriteUsers().contains(userId)) {
            throw new RestaurantException("Restaurant is already in favorites.");
        }

        restaurant.getFavoriteUsers().add(userId);
        restaurantRepository.save(restaurant);

        RestaurantDto dto = new RestaurantDto();
        dto.setId(restaurant.getId());
        dto.setTitle(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());

        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(String id, String status) throws RestaurantException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with id: " + id));

        restaurant.setStatus(status);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Category> getCategoriesByRestaurantId(String id) throws RestaurantException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with id: " + id));
        return restaurant.getCategories();
    }
}
