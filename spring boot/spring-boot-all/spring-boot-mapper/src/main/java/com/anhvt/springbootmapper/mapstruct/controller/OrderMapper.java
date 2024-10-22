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
            expression = "java(new com.anhvt.springbootmapper.mapstruct.model.Customer(orderDTO.getCustomerFirstName(), orderDTO.getCustomerLastName()))")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "city", target = "city")
    Order toOrder(OrderDTO orderDTO);

    @Mapping(source = "customer.firstName", target = "customerFirstName")
    @Mapping(source = "customer.lastName", target = "customerLastName")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "city", target = "city")
    OrderDTO toOrderDTO(Order order);
}
