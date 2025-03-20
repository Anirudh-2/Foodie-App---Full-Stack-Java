package com.project.Food_App.service;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Events;

import java.util.List;

public interface EventsService {
    // Create a new event linked to a specific restaurant
    public Events createEvent(Events event, String restaurantId) throws RestaurantException;

    // Find all events
    public List<Events> findAllEvent();

    // Find all events for a specific restaurant
    public List<Events> findRestaurantsEvent(String restaurantId);

    // Delete an event by its ID
    public void deleteEvent(String eventId) throws RestaurantException;

    // Find an event by its ID
    public Events findById(String eventId) throws RestaurantException;
}
