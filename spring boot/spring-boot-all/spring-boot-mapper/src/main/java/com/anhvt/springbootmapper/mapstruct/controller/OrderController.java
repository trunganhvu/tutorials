/**
 * Copyright 2024
 * Name: OrderController
 */
package com.anhvt.springbootmapper.mapstruct.controller;

import com.anhvt.springbootmapper.mapstruct.model.Customer;
import com.anhvt.springbootmapper.mapstruct.model.Order;
import com.anhvt.springbootmapper.mapstruct.model.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
@RestController
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/hello")
    public String main() {
        entityToDto();
        dtoToEntity();
        return "Hello World!";
    }

    private void entityToDto() {
        // Assuming you have an existing Order object
        Order order = new Order(new Customer("trunganh", "vu"), "CG", "HN");

        // Map Order to OrderDTO
        OrderDTO orderDTO = orderMapper.toOrderDTO(order);

        System.out.println("OrderDTO mapped successfully: " + orderDTO);
        // OrderDTO mapped successfully: OrderDTO(customerFirstName=trunganh, customerLastName=vu, street=CG, city=HN)
    }

    private void dtoToEntity() {
        OrderDTO orderDTO1 = new OrderDTO("trunganh",
                "vu", "CG", "HN");
        // Map OrderDTO to Order
        Order order2 = orderMapper.toOrder(orderDTO1);

        System.out.println("Order mapped successfully: " + order2);
        // Order mapped successfully: Order(customer=Customer(firstName=trunganh, lastName=vu), street=CG, city=HN)
    }
}
