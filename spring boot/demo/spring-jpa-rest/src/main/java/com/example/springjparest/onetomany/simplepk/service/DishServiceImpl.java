package com.example.springjparest.onetomany.simplepk.service;

import com.example.springjparest.onetomany.simplepk.exception.DishNotFoundException;
import com.example.springjparest.onetomany.simplepk.model.Dish;
import com.example.springjparest.onetomany.simplepk.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    public Dish validateAndGetDish(String dishId, String restaurantId) {
        return dishRepository.findByIdAndRestaurantId(dishId, restaurantId)
                .orElseThrow(() -> new DishNotFoundException(dishId, restaurantId));
    }

    @Override
    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Dish dish) {
        dishRepository.delete(dish);
    }
}
