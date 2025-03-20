package com.project.Food_App.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "payment")  // Optional: specify the MongoDB collection name
public class Payment {

    @Id
    private Long id;  // MongoDB will automatically generate the _id field, but you can still use Long for the id.

    private Long orderId;
    private String paymentMethod;
    private String paymentStatus;
    private double totalAmount;

    private Date createdAt;  // MongoDB stores Date objects directly, no need for @Temporal

    public Payment(Long id, Long orderId, String paymentMethod, String paymentStatus, double totalAmount, Date createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public Payment() {
    }
}
