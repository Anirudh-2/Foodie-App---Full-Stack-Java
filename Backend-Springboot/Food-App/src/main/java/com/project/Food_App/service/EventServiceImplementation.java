package com.project.Food_App.service;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Events;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplementation implements EventsService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestaurantService restaurantService;

    // Method to create an event
    @Override
    public Events createEvent(Events event, String restaurantId) throws RestaurantException {
        // Find the restaurant by ID
        Optional<Restaurant> optionalRestaurant = restaurantService.findRestaurantById(restaurantId);

        // Check if the restaurant exists
        if (!optionalRestaurant.isPresent()) {
            throw new RestaurantException("Restaurant not found with ID " + restaurantId);  // Handle case where restaurant is not found
        }

        Restaurant restaurant = optionalRestaurant.get(); // Extract the restaurant object from the Optional

        // Create and set the event details
        Events createdEvent = new Events();
        createdEvent.setRestaurant(restaurant);
        createdEvent.setImage(event.getImage());
        createdEvent.setStartedAt(event.getStartedAt());
        createdEvent.setEndsAt(event.getEndsAt());
        createdEvent.setLocation(event.getLocation());
        createdEvent.setName(event.getName());

        // Save and return the event
        return eventRepository.save(createdEvent);
    }

    // Method to get all events
    @Override
    public List<Events> findAllEvent() {
        return eventRepository.findAll();
    }

    // Method to get events by restaurant ID
    @Override
    public List<Events> findRestaurantsEvent(String restaurantId) {
        return eventRepository.findEventsByRestaurantId(restaurantId);
    }

    // Method to delete an event by ID
    @Override
    public void deleteEvent(String eventId) throws RestaurantException {
        Events event = findById(eventId);
        eventRepository.delete(event);
    }

    // Method to find an event by ID
    @Override
    public Events findById(String eventId) throws RestaurantException {
        Optional<Events> opt = eventRepository.findById(eventId);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new RestaurantException("Event not found with ID " + eventId);
    }
}
