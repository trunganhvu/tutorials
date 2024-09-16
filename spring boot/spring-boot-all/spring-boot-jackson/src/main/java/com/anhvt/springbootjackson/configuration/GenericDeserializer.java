/**
 * Copyright 2024
 * Name: GenericDeserializer
 */
package com.anhvt.springbootjackson.configuration;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/15/2024
 */
public class GenericDeserializer<T> extends JsonDeserializer<T> {
    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JavaType javaType = ctxt.getContextualType();
        if (javaType == null) {
            throw new IOException("Type information is missing");
        }

        BasicBeanDescription beanDesc = (BasicBeanDescription) mapper.getSerializationConfig().introspect(javaType);
        List<BeanPropertyDefinition> properties = beanDesc.findProperties();

        T instance;
        try {
            instance = (T) javaType.getRawClass().getDeclaredConstructor().newInstance();
            JsonNode rootNode = mapper.readTree(p);

            for (BeanPropertyDefinition prop : properties) {
                String name = prop.getName();
                JsonNode valueNode = rootNode.get(name);

                if (valueNode != null) {
                    Method setter = prop.getSetter() != null ? prop.getSetter().getAnnotated() : null;
                    if (setter != null) {
                        Class<?> paramType = setter.getParameterTypes()[0];
                        Object value = mapper.treeToValue(valueNode, paramType);
                        setter.invoke(instance, value);
                    }
                }
            }
        } catch (Exception e) {
            throw new IOException("Failed to deserialize", e);
        }

        return (T) instance;
    }
//    @Override
//    public T deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
//        JsonToken currentToken = jsonParser.getCurrentToken();
//        if (JsonToken.VALUE_STRING.equals(currentToken)) {
//            return (T) jsonParser.getText();
//        } else if (JsonToken.VALUE_NULL.equals(currentToken)) {
//            return null;
//        } else if (currentToken.isScalarValue()) {
//            return (T) jsonParser.getText();
//        } else if (currentToken.isStructStart()) {//(JsonToken.START_OBJECT.equals(currentToken)) {
//            System.out.println(currentToken.toString());
////            Map<String, Object> currentValue = jsonParser.readValueAs(new TypeReference<Map<String, Object>>() {
////            });
//            JsonNode currentValue = jsonParser.readValueAsTree();
//            System.out.println(currentValue.getClass().toString());
//            System.out.println(currentValue.toString());
//            System.out.println(currentValue.getNodeType());
//            return afterDeserialize((T) currentValue);
//        }
//        return null;
//    }
//    protected T afterDeserialize(T obj) {
//        return obj;
//    }
}
