package com.project.User.Services.Response;

import com.project.User.Services.Domain.USER_ROLE;

public class AuthResponse {

    private String message;
    private String jwt;
    private USER_ROLE role;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }
}
