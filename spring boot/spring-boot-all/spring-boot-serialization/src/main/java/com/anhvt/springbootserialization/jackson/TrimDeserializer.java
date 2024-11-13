/**
 * Copyright 2024
 * Name: TrimDeserializer
 */
package com.anhvt.springbootserialization.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 11/13/2024
 */
public class TrimDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return StringUtils.trim(p.getText());
    }
}
