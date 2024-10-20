package com.anhvt.springbootmapper.mapstruct.controller;

import com.anhvt.springbootmapper.mapstruct.model.Order;
import com.anhvt.springbootmapper.mapstruct.model.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
@Mapper
public interface OrderMapper {
    @Mapping(target = "customer",
            expression = "java(new Customer(orderDTO.getCustomerFirstName(), orderDTO.getCustomerLastName()))")
    Order toOrder(OrderDTO orderDTO);
}
