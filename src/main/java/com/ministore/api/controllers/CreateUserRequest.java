package com.ministore.api.controllers;

public class CreateUserRequest {
    public String username;

    public boolean isValid() {
        return this.username != null && !this.username.isEmpty();
    }
}
