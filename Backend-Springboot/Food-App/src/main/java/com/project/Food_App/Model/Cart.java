package com.project.Food_App.Model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "cart")
public class Cart {
    @Id
    private String id;
    private String customerId;

    @NotEmpty(message = "userName cannot be empty or null")
    private String userName;

    private List<CartItem> items = new ArrayList<>();
    private Long total;

    public Cart() {}

    public Cart(String customerId, String userName, List<CartItem> items, Long total) {
        this.customerId = customerId;
        this.userName = userName;
        this.items = items;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
