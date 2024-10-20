/**
 * Copyright 2024
 * Name: OrderController
 */
package com.anhvt.springbootmapper.mapstruct.controller;

import com.anhvt.springbootmapper.mapstruct.model.Order;
import com.anhvt.springbootmapper.mapstruct.model.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
@RequiredArgsConstructor
public class OrderController {

    private static final OrderMapper orderMapper = null;

    public static void main(String[] args) {
        entityToDto();
        dtoToEntity();
    }
    private static void entityToDto() {
    }

    private static void dtoToEntity() {
        OrderDTO orderDTO1 = new OrderDTO("trunganh", "vu", "CG", "HN");


        // Map OrderDTO to Order
        Order order2 = orderMapper.toOrder(orderDTO1);

        System.out.println("Order mapped successfully: " + order2);
    }
}
