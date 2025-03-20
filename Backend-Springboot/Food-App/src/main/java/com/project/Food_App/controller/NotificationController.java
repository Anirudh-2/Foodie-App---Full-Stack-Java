package com.project.Food_App.controller;

import com.project.Food_App.Model.Notification;
import com.project.Food_App.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationSerivce;
//    @Autowired
//    private UserService userService;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> findUsersNotification(){
        // User user=userService.findUserProfileByJwt(jwt);
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        List<Notification> notifications=notificationSerivce.findUsersNotification(user);
        return new ResponseEntity<List<Notification>>(notifications, HttpStatus.ACCEPTED);
    }

}

