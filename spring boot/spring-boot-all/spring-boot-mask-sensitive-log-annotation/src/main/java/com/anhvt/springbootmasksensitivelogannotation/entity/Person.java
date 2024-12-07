/**
 * Copyright 2024
 * Name: Person1
 */
package com.anhvt.springbootmasksensitivelogannotation.entity;

import com.anhvt.springbootmasksensitivelogannotation.annotation.LogMask;
import com.anhvt.springbootmasksensitivelogannotation.config.LogMaskDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Person extends LogMaskDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    @LogMask(prefix = 2, suffix = 2)
    private Integer creditCardNumber;
    @LogMask
    private String address;

}