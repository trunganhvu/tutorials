package com.anhvt.springbootmapper.modelmapper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Customer {
    private String firstName;
    private String lastName;
}
