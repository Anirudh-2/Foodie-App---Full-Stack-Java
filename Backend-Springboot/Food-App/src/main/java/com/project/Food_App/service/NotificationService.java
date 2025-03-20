package com.project.Food_App.service;

import com.project.Food_App.Model.Notification;
import com.project.Food_App.Model.Order;
import com.project.Food_App.Model.Restaurant;

import java.util.List;

public interface NotificationService {
    public Notification sendOrderStatusNotification(Order order);
    public void sendRestaurantNotification(Restaurant restaurant, String message);
    public void sendPromotionalNotification(String userName, String message);

    public List<Notification> findUsersNotification(String userName);
}
