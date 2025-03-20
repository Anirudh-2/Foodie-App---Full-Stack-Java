package com.project.Food_App.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")  // Specify the MongoDB collection name
public class Order {

    @Id
    private String id;  // MongoDB will auto-generate the _id field, but you can still use Long if needed.

    private String userName;
    private String customer;  // Reference to User document

    @DBRef  // Use @DBRef instead of @ManyToOne for MongoDB
    @JsonBackReference
    private Restaurant restaurant;  // Reference to Restaurant document

    private Double totalAmount;

    private String orderStatus;

    private Date createdAt;  // MongoDB stores Date directly, no need for @Temporal

    @DBRef  // Use @DBRef instead of @ManyToOne for MongoDB
    private Address deliveryAddress;  // Reference to Address document

    @DBRef  // Use @DBRef for referencing OrderItem documents (One-to-many relationship)
    private List<OrderItem> items;  // List of OrderItem documents

    @DBRef  // Use @DBRef instead of @OneToOne for MongoDB
    private Payment payment;  // Reference to Payment document

    private int totalItem;

    private int totalPrice;

    public Order(String userName, Date createdAt, String orderStatus, Double totalAmount, Restaurant restaurant, List<OrderItem> items, Address deliveryAddress) {
        this.userName = userName;
        this.createdAt = createdAt;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.restaurant = restaurant;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
    }

    public Order() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
