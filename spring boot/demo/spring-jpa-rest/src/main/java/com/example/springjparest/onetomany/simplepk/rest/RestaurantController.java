package com.example.springjparest.onetomany.simplepk.rest;

import com.example.springjparest.onetomany.simplepk.mapper.DishMapper;
import com.example.springjparest.onetomany.simplepk.mapper.RestaurantMapper;
import com.example.springjparest.onetomany.simplepk.model.Dish;
import com.example.springjparest.onetomany.simplepk.model.Restaurant;
import com.example.springjparest.onetomany.simplepk.rest.dto.CreateDishRequest;
import com.example.springjparest.onetomany.simplepk.rest.dto.CreateRestaurantRequest;
import com.example.springjparest.onetomany.simplepk.rest.dto.DishResponse;
import com.example.springjparest.onetomany.simplepk.rest.dto.RestaurantResponse;
import com.example.springjparest.onetomany.simplepk.rest.dto.UpdateDishRequest;
import com.example.springjparest.onetomany.simplepk.rest.dto.UpdateRestaurantRequest;
import com.example.springjparest.onetomany.simplepk.service.DishService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final DishService dishService;
    private final RestaurantMapper restaurantMapper;
    private final DishMapper dishMapper;

    //-----------
    // Restaurant

    @GetMapping("/{restaurantId}")
    public RestaurantResponse getRestaurant(@PathVariable String restaurantId) {
        Restaurant restaurant = restaurantService.validateAndGetRestaurant(restaurantId);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RestaurantResponse createRestaurant(@Valid @RequestBody CreateRestaurantRequest createRestaurantRequest) {
        Restaurant restaurant = restaurantMapper.toRestaurant(createRestaurantRequest);
        restaurant = restaurantService.saveRestaurant(restaurant);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    @PutMapping("/{restaurantId}")
    public RestaurantResponse updateRestaurant(@PathVariable String restaurantId,
                                               @Valid @RequestBody UpdateRestaurantRequest updateRestaurantRequest) {
        Restaurant restaurant = restaurantService.validateAndGetRestaurant(restaurantId);
        restaurantMapper.updateRestaurantFromRequest(updateRestaurantRequest, restaurant);
        restaurantService.saveRestaurant(restaurant);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public RestaurantResponse deleteRestaurant(@PathVariable String restaurantId) {
        Restaurant restaurant = restaurantService.validateAndGetRestaurant(restaurantId);
        restaurantService.deleteRestaurant(restaurant);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    //-----
    // Dish

    @GetMapping("/{restaurantId}/dishes/{dishId}")
    public DishResponse getDish(@PathVariable String restaurantId, @PathVariable String dishId) {
        Dish dish = dishService.validateAndGetDish(dishId, restaurantId);
        return dishMapper.toDishResponse(dish);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{restaurantId}/dishes")
    public DishResponse createDish(@PathVariable String restaurantId,
                                   @Valid @RequestBody CreateDishRequest createDishRequest) {
        Restaurant restaurant = restaurantService.validateAndGetRestaurant(restaurantId);
        Dish dish = dishMapper.toDish(createDishRequest);
        dish.addRestaurant(restaurant);
        dish = dishService.saveDish(dish);
        return dishMapper.toDishResponse(dish);
    }

    @PutMapping("/{restaurantId}/dishes/{dishId}")
    public DishResponse updateDish(@PathVariable String restaurantId,
                                   @PathVariable String dishId,
                                   @Valid @RequestBody UpdateDishRequest updateDishRequest) {
        Dish dish = dishService.validateAndGetDish(dishId, restaurantId);
        dishMapper.updateDishFromRequest(updateDishRequest, dish);
        dish = dishService.saveDish(dish);
        return dishMapper.toDishResponse(dish);
    }

    @DeleteMapping("/{restaurantId}/dishes/{dishId}")
    public DishResponse deleteDish(@PathVariable String restaurantId, @PathVariable String dishId) {
        Dish dish = dishService.validateAndGetDish(dishId, restaurantId);
        dish.removeRestaurant();
        dishService.deleteDish(dish);
        return dishMapper.toDishResponse(dish);
    }
}