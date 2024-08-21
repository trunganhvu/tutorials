package com.example.springjparest.onetomany.simplepk.service;

import com.example.springjparest.onetomany.simplepk.model.Restaurant;
import com.example.springjparest.onetomany.simplepk.rest.dto.CreateRestaurantAllFieldRequest;

import java.util.List;

public interface RestaurantService {

    Restaurant validateAndGetRestaurant(String id);

    Restaurant saveRestaurant(Restaurant restaurant);

    void deleteRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurant();

    Restaurant updateRestaurant(String id, CreateRestaurantAllFieldRequest restaurant);
}