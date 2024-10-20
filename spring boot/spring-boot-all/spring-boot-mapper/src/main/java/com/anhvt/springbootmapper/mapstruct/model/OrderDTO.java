package com.anhvt.springbootmapper.mapstruct.model;

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
public class OrderDTO {
    private String customerFirstName;
    private String customerLastName;
    private String street;
    private String city;
}
