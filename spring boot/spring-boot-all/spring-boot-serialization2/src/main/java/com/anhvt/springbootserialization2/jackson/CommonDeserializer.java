/**
 * Copyright 2024
 * Name: CommonDeserializer
 */
package com.anhvt.springbootserialization2.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 11/13/2024
 */
public class CommonDeserializer extends JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final JavaType type = ctxt.getTypeFactory().constructType(p.getCurrentValue().getClass());
        final JsonDeserializer<Object> deserializer = ctxt.findRootValueDeserializer(type);
        final Object value = deserializer.deserialize(p, ctxt);
        if (value instanceof String) {
            return StringUtils.trim((String) value);
        } else if (value instanceof java.util.Date) {
            java.util.Date date = (java.util.Date) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    .withZone(ZoneId.of("Asia/Saigon"));
            String str = formatter.format(date.toInstant());
            return str;
        } else if (value instanceof Instant) {
            Instant instant = (Instant) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    .withZone(ZoneId.of("Asia/Saigon"));
            String str = formatter.format(instant);
            return str;
        } else {
            return value;
        }
    }
}
