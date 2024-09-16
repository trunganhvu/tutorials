/**
 * Copyright 2024
 * Name: UserDeserializer
 */
package com.anhvt.springbootjackson.configuration;

import com.anhvt.springbootjackson.model.User;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/15/2024
 */
public class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(com.fasterxml.jackson.core.JsonParser p,
                            DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        String userName = node.get("user-name").asText();
        User user = new User();
        user.setUserName(userName);
        return user;
    }
}
