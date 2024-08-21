package com.example.springjparest.onetomany.simplepk.service;

import com.example.springjparest.onetomany.simplepk.model.Dish;

public interface DishService {

    Dish validateAndGetDish(String dishId, String restaurantId);

    Dish saveDish(Dish dish);

    void deleteDish(Dish dish);
}
