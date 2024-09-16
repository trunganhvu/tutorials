/**
 * Copyright 2024
 * Name: GenericSerializer
 */
package com.anhvt.springbootjackson.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/15/2024
 */
public class GenericSerializer<T> extends JsonSerializer<T> {
    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        BasicBeanDescription beanDesc = (BasicBeanDescription) serializers.getConfig().introspect(serializers.getConfig().constructType(value.getClass()));
        List<BeanPropertyDefinition> properties = beanDesc.findProperties();

        for (BeanPropertyDefinition prop : properties) {
            AnnotatedMember member = prop.getGetter();
            if (member != null) {
                String name = prop.getName();
                Object fieldValue = member.getValue(value);
                gen.writeObjectField(name, fieldValue);
            }
        }

        gen.writeEndObject();
    }
}
