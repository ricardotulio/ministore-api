package com.ministore.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        value = "/users",
        consumes = "application/json",
        produces = "application/json"
    )
    private User createUser(@RequestBody CreateUserRequest request)
    {
        if (!request.isValid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username is invalid");
        }

        try {
            return this.userService.createUser(request.username);
        } catch(DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "username is invalid");
        }
    }
}
