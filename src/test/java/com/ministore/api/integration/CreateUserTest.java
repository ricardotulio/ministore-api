package com.ministore.api.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

class CreateUserResponse {
    public String id;
    public String username;
}

@SpringBootTest
@AutoConfigureMockMvc
public class CreateUserTest {

    @Autowired
    private MockMvc mockMvc;

    private Faker faker = new Faker();

    private ResultActions performGet(String userId) throws Exception {
        return this.mockMvc.perform(
            get("/users/" + userId)
            .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions performPost(String payload) throws Exception {
        MockHttpServletRequestBuilder post = post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);

        if (payload != null) {
            post = post.content(payload);
        }

        return this.mockMvc.perform(post);
    }

    private String createPostPayload(String username) {
        return "{\"username\":\"" + username + "\"}";
    }

    @Test
    void mustBeAbleToCreateAUser() throws Exception
    {
        String username = faker.name().username();
        String payload =  createPostPayload(username);

        MvcResult result = performPost(payload)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").isString())
            .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        CreateUserResponse response = mapper.readValue(
            result.getResponse().getContentAsString(), 
            CreateUserResponse.class
        );
        
        assertEquals(response.username, username);

        performGet(response.id)
            .andExpect(jsonPath("$.id").value(response.id))
            .andExpect(jsonPath("$.username").value(username));
    }

    @Test
    void mustReturnBadRequestWhenUsernameAlreadyExists() throws Exception {
        String username = faker.name().username();
        String payload = createPostPayload(username);

        performPost(payload)
            .andExpect(status().isOk());

        performPost(payload)
            .andExpect(status().isConflict());
    }

    @Test
    void mustReturnNotFoundWhenUserNotFound() throws Exception {
        performGet("whatever")
            .andExpect(status().isNotFound());
    }

    @Test
    void mustReturnBadRequestWhenCreateUserDoesntReceivePayload() throws Exception {
        performPost(null)
            .andExpect(status().isBadRequest());
    }

    @Test
    void mustReturnBadRequestWhenUsernameIsInvalid() throws Exception {
        performPost(createPostPayload(""))
            .andExpect(status().isBadRequest());
    }

    @Test
    void mustReturnBadRequestWhenPayloadDoesntHaveUsername() throws Exception {
        performPost("{}")
            .andExpect(status().isBadRequest());
    }
}
