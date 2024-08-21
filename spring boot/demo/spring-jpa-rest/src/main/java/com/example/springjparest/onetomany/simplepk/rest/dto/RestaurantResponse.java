package com.example.springjparest.onetomany.simplepk.rest.dto;

import java.util.List;

public record RestaurantResponse(String id, String name, List<DishResponse> dishes) {
}
