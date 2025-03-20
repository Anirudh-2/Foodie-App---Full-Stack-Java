package com.project.Food_App.service;

import com.project.Food_App.Model.Notification;
import com.project.Food_App.Model.Order;
import com.project.Food_App.Model.Restaurant;
import com.project.Food_App.repository.NotificationRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationServiceImplementation implements NotificationService{

    @Autowired
    private NotificationRepostiory notificationRepository;

    @Override
    public Notification sendOrderStatusNotification(Order order) {
        Notification notification = new Notification();
        notification.setMessage("your order is "+order.getOrderStatus()+ " order id is - "+order.getId());
        notification.setCustomer(order.getCustomer());
        notification.setSentAt(new Date());

        return notificationRepository.save(notification);
    }

    @Override
    public void sendRestaurantNotification(Restaurant restaurant, String message) {

    }

    @Override
    public void sendPromotionalNotification(String userName, String message) {

    }

    @Override
    public List<Notification> findUsersNotification(String userName) {
        return notificationRepository.findByCustomer(userName);
    }
}
