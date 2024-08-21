package com.example.springjparest.onetomany.simplepk.service;

import com.example.springjparest.onetomany.simplepk.exception.RestaurantNotFoundException;
import com.example.springjparest.onetomany.simplepk.model.Dish;
import com.example.springjparest.onetomany.simplepk.model.Restaurant;
import com.example.springjparest.onetomany.simplepk.repository.DishRepository;
import com.example.springjparest.onetomany.simplepk.repository.RestaurantRepository;
import com.example.springjparest.onetomany.simplepk.rest.dto.CreateRestaurantAllFieldRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Override
    public Restaurant validateAndGetRestaurant(String id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant updateRestaurant(String id, CreateRestaurantAllFieldRequest restaurant) {
        Restaurant currentRestaurant = validateAndGetRestaurant(id);

        currentRestaurant.setName(restaurant.getRestaurantName());

        for (Dish dish: currentRestaurant.getDishes()) {
            dishRepository.delete(dish);
        }

        List<String> dishes = restaurant.getDishNames();
        if (!dishes.isEmpty()) {
            Restaurant finalRestaurant = currentRestaurant;
            Set<Dish> dishSet = dishes.stream().map(dish -> {
                Dish d = new Dish();
                d.setName(dish);
                d.addRestaurant(finalRestaurant);
                return d;
            }).collect(Collectors.toSet());

            currentRestaurant.resetDishSet(dishSet);
        }
        return saveRestaurant(currentRestaurant);
    }
}
