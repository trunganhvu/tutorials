package com.example.springjparest.onetomany.simplepk.rest;

import com.example.springjparest.onetomany.simplepk.mapper.RestaurantMapper;
import com.example.springjparest.onetomany.simplepk.model.Dish;
import com.example.springjparest.onetomany.simplepk.model.Restaurant;
import com.example.springjparest.onetomany.simplepk.rest.dto.CreateRestaurantAllFieldRequest;
import com.example.springjparest.onetomany.simplepk.rest.dto.RestaurantResponse;
import com.example.springjparest.onetomany.simplepk.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurants2")
public class RestaurantController2 {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping
    public List<RestaurantResponse> getAllRestaurant() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
        return restaurants.stream().map(restaurantMapper::toRestaurantResponse).toList();
    }

    @GetMapping("/{restaurantId}")
    public RestaurantResponse getRestaurant(@PathVariable String restaurantId) {
        Restaurant restaurant = restaurantService.validateAndGetRestaurant(restaurantId);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RestaurantResponse createRestaurant(@Valid @RequestBody CreateRestaurantAllFieldRequest request) {
        Restaurant restaurant = restaurantMapper.createRestaurantFromRequest(request);

        List<String> dishs = request.getDishNames();
        if (!dishs.isEmpty()) {
            Restaurant finalRestaurant = restaurant;
            Set<Dish> disheSet = dishs.stream().map(dish -> {
                Dish d = new Dish();
                d.setName(dish);
                d.addRestaurant(finalRestaurant);
                return d;
            }).collect(Collectors.toSet());

            restaurant.resetDishSet(disheSet);
        }

        restaurant = restaurantService.saveRestaurant(restaurant);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    @PutMapping("/{restaurantId}")
    public RestaurantResponse updateRestaurant(@PathVariable String restaurantId,
                                               @Valid @RequestBody CreateRestaurantAllFieldRequest request) {
        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, request);

        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public RestaurantResponse deleteRestaurant(@PathVariable String restaurantId) {
        Restaurant restaurant = restaurantService.validateAndGetRestaurant(restaurantId);
        restaurantService.deleteRestaurant(restaurant);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }
}