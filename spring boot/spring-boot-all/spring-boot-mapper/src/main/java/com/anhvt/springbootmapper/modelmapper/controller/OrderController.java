/**
 * Copyright 2024
 * Name: OrderController
 */
package com.anhvt.springbootmapper.modelmapper.controller;

import com.anhvt.springbootmapper.modelmapper.model.Customer;
import com.anhvt.springbootmapper.modelmapper.model.Order;
import com.anhvt.springbootmapper.modelmapper.model.OrderDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
public class OrderController {
    public static void main(String[] args) {
        entityToDto();
        dtoToEntity();
    }

    private static void entityToDto() {
        Order order = new Order(new Customer("trunganh", "vu"),
                "CG", "HN");

        ModelMapper modelMapper1 = new ModelMapper();
        OrderDTO orderDTO1 = modelMapper1.map(order, OrderDTO.class);
        System.out.println("Default mapper");
        System.out.println(orderDTO1);  // OrderDTO(customerFirstName=trunganh, customerLastName=vu, street=CG, city=HN)
        System.out.println(order);      // Order(customer=Customer(firstName=trunganh, lastName=vu), street=CG, city=HN)

        System.out.println("Custom mapper");
        ModelMapper modelMapper2 = new ModelMapper();
        modelMapper2.addMappings(new PropertyMap<Order, OrderDTO>() {
            @Override
            protected void configure() {
                map().setCustomerLastName(source.getCustomer().getFirstName());
                map().setCustomerFirstName(source.getCustomer().getLastName());
                map().setCity(source.getCity());
                map().setStreet(source.getStreet());
            }
        });
        OrderDTO orderDTO2 = modelMapper2.map(order, OrderDTO.class);
        System.out.println(orderDTO2);  // OrderDTO(customerFirstName=vu, customerLastName=trunganh, street=CG, city=HN)
        System.out.println(order);      // Order(customer=Customer(firstName=trunganh, lastName=vu), street=CG, city=HN)
    }

    private static void dtoToEntity() {
        System.out.println("Default mapper");
        OrderDTO orderDTO1 = new OrderDTO("trunganh",
                "vu", "CG", "HN");
        ModelMapper modelMapper1 = new ModelMapper();
        Order order1 = modelMapper1.map(orderDTO1, Order.class);
        System.out.println(orderDTO1);   // OrderDTO(customerFirstName=trunganh, customerLastName=vu, street=CG, city=HN)
        System.out.println(order1);      // Order(customer=Customer(firstName=trunganh, lastName=vu), street=CG, city=HN)


        System.out.println("Custom mapper");
        ModelMapper modelMapper2 = new ModelMapper();
        modelMapper2.addMappings(new PropertyMap<OrderDTO, Order>() {
            @Override
            protected void configure() {
                // Modal mapper can not set new object
//                map().setCustomer(Customer.builder()
//                        .firstName(source.getCustomerLastName())
//                        .lastName(source.getCustomerFirstName())
//                        .build());

                // New customer created and set attributes only
                map().getCustomer().setFirstName(source.getCustomerLastName()); // Set last to first
                map().getCustomer().setLastName(source.getCustomerLastName());
                map().setCity(source.getCity());
                map().setStreet(source.getStreet());
            }
        });
        Order order2 = modelMapper2.map(orderDTO1, Order.class);
        System.out.println(orderDTO1);      // OrderDTO(customerFirstName=trunganh, customerLastName=vu, street=CG, city=HN)
        System.out.println(order2);         // Order(customer=Customer(firstName=vu, lastName=vu), street=CG, city=HN)

    }
}
