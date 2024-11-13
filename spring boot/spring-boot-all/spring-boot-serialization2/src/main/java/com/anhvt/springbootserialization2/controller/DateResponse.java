/**
 * Copyright 2024
 * Name: DateResponse
 */
package com.anhvt.springbootserialization2.controller;

import com.anhvt.springbootserialization2.jackson.CommonDeserializer;
import com.anhvt.springbootserialization2.jackson.CommonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonDeserialize(using = CommonDeserializer.class)
    @JsonSerialize(using = CommonSerializer.class)
    private Instant startSerializer;

    private Instant start;

    @JsonDeserialize(using = CommonDeserializer.class)
    @JsonSerialize(using = CommonSerializer.class)
    private String content;
}
