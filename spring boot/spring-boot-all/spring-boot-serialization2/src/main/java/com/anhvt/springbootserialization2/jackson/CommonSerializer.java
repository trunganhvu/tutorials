/**
 * Copyright 2024
 * Name: CommonSerializer
 */
package com.anhvt.springbootserialization2.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
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
public class CommonSerializer extends JsonSerializer<Object> {
    // ZoneId: UTC+1,UTC-1,...
    // ZoneId: Asia/Tokyo, Asia/Saigon,...
    private final String zoneId = "Asia/Saigon";

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value instanceof Instant) {
            Instant instant = (Instant) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    .withZone(ZoneId.of(zoneId));
            String str = formatter.format(instant);
            gen.writeString(str);
        } else if (value instanceof String) {
            String str = StringUtils.trim((String) value);
            gen.writeString(str);
        } else if (value instanceof java.util.Date) {
            java.util.Date date = (java.util.Date) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    .withZone(ZoneId.of(zoneId));
            String str = formatter.format(date.toInstant());
            gen.writeString(str);
        } else {
            gen.writeObject(value);
        }
    }
}
