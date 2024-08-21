package com.example.springjparest.onetomany.simplepk.repository;

import com.example.springjparest.onetomany.simplepk.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
}
