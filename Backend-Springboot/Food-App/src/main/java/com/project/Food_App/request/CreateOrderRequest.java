package com.project.Food_App.request;

import com.project.Food_App.Model.Address;

public class CreateOrderRequest {

    private String restaurantId;

    private Address deliveryAddress;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
