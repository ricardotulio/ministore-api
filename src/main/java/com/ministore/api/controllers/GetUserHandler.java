package com.ministore.api.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ministore.api.entities.User;
import com.ministore.api.repositories.UserRepository;

@RestController
public class GetUserHandler {

    private UserRepository repository;

    public GetUserHandler(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(
        value = "/users/{id}",
        produces = "application/json"
    )
    public GetUserResponse getUser(@PathVariable String id) {
        User user = this.repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        GetUserResponse response = new GetUserResponse(user.getId(), user.getUsername());

        return response;
    }
}
