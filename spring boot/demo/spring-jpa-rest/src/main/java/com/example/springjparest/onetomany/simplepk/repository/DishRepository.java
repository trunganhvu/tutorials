package com.example.springjparest.onetomany.simplepk.repository;

import com.example.springjparest.onetomany.simplepk.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, String> {

    Optional<Dish> findByIdAndRestaurantId(String id, String restaurantId);
}
