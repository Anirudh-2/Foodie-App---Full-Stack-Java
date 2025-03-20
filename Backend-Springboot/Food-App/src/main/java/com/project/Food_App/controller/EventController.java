package com.project.Food_App.controller;

import com.project.Food_App.Exception.RestaurantException;
import com.project.Food_App.Model.Events;
import com.project.Food_App.response.ApiResponse;
import com.project.Food_App.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    public EventsService eventService;

    // Endpoint to create an event
    @PostMapping("/admin/events/restaurant/{restaurantId}")
    public ResponseEntity<Events> createEvents(@RequestBody Events event,
                                               @PathVariable String restaurantId) throws RestaurantException {
        Events createdEvents = eventService.createEvent(event, restaurantId);
        return new ResponseEntity<>(createdEvents, HttpStatus.ACCEPTED);
    }

    // Endpoint to get all events
    @GetMapping("/events")
    public ResponseEntity<List<Events>> findAllEvents() throws RestaurantException {
        List<Events> events = eventService.findAllEvent();
        return new ResponseEntity<>(events, HttpStatus.ACCEPTED);
    }

    // Endpoint to get events for a specific restaurant
    @GetMapping("/admin/events/restaurant/{restaurantId}")
    public ResponseEntity<List<Events>> findRestaurantsEvents(
            @PathVariable String restaurantId) throws RestaurantException {
        List<Events> events = eventService.findRestaurantsEvent(restaurantId);
        return new ResponseEntity<>(events, HttpStatus.ACCEPTED);
    }

    // Endpoint to delete an event
    @DeleteMapping("/admin/events/{id}")
    public ResponseEntity<ApiResponse> deleteEvents(
            @PathVariable String id) throws RestaurantException {
        eventService.deleteEvent(id);
        ApiResponse res = new ApiResponse("Event Deleted", true);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    // Exception handler for RestaurantException
    @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<ApiResponse> handleRestaurantException(RestaurantException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
