/**
 * Copyright 2024
 * Name: Order
 */
package com.anhvt.springbootmapper.modelmapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Customer customer;
    private String street;
    private String city;
}
