package com.ministore.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ministore.api.entities.User;
import com.ministore.api.services.UserService;

@RestController
public class CreateUserHandler {

    @Autowired
    private UserService userService;

    public CreateUserHandler(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping(
        value = "/user",
        consumes = "application/json",
        produces = "application/json"
    )
    private User createUser(@RequestBody CreateUserRequest request)
    {
        if (request.username == null || request.username.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username is invalid");
        }

        return this.userService.createUser(request.username);
    }

}
