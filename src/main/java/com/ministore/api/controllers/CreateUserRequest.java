package com.ministore.api.controllers;

public class CreateUserRequest {
    public String username;

    public String password;

    public boolean isValid() {
        return this.username != null
            && !this.username.isEmpty()
            && this.password != null
            && !this.password.isEmpty();
    }
}
