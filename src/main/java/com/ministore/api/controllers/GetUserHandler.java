package com.ministore.api.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ministore.api.entities.User;
import com.ministore.api.repositories.UserRepository;

@RestController
public class GetUserHandler {

    private UserRepository repository;

    public GetUserHandler(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(
        value = "/user/{id}",
        produces = "application/json"
    )
    public Optional<User> getUser(@PathVariable Long id) {
        return this.repository.findById(id);
    }

}
