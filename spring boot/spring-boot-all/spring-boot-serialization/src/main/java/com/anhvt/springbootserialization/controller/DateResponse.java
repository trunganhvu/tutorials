/**
 * Copyright 2024
 * Name: DateResponse
 */
package com.anhvt.springbootserialization.controller;

import com.anhvt.springbootserialization.jackson.DateTimeDeserializer;
import com.anhvt.springbootserialization.jackson.DateTimeSerializer;
import com.anhvt.springbootserialization.jackson.TrimDeserializer;
import com.anhvt.springbootserialization.jackson.TrimSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.Instant;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 11/13/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateResponse implements Serializable {
    private Long id;

    private int counter;

    @JsonDeserialize(using = DateTimeDeserializer.class)
    @JsonSerialize(using = DateTimeSerializer.class)
    private Instant startSerializer;

    private Instant start;

    @JsonDeserialize(using = TrimDeserializer.class)
    @JsonSerialize(using = TrimSerializer.class)
    private String content;
}
