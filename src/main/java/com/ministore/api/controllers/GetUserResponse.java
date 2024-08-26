package com.ministore.api.controllers;

public class GetUserResponse {
    public String id;

    public String username;

    public GetUserResponse(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
