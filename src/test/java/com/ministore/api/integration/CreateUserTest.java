package com.ministore.api.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.fasterxml.jackson.databind.ObjectMapper;

class CreateUserResponse {
    public Integer id;
    public String username;
}

@SpringBootTest
@AutoConfigureMockMvc
public class CreateUserTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void mustBeAbleToCreateAUser() throws Exception
    {
        MvcResult result = this.mockMvc.perform(
            post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"ricardotulio\"}")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").isNumber())
            .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        CreateUserResponse response = mapper.readValue(
            result.getResponse().getContentAsString(), 
            CreateUserResponse.class
        );

        this.mockMvc.perform(
            get("/user/" + response.id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(response.id))
            .andExpect(jsonPath("$.username").value(response.username));
    }
}
