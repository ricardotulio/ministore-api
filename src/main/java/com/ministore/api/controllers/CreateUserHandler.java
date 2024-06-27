package com.ministore.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        return this.userService.createUser(request.username);
    }

}
