package com.ministore.api.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.password4j.Password;

public class UserTest {
    
    @Test
    public void mustEncryptThePassword() {
        String password = "abc123";
        User user = new User();
        user.setPassword(password);

        assertEquals(user.getPassword(), Password.hash(password).withArgon2().getResult());
    }

}
