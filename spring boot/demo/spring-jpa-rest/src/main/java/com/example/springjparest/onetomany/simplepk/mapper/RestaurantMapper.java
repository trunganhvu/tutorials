package com.example.springjparest.onetomany.simplepk.mapper;

import com.example.springjparest.onetomany.simplepk.model.Dish;
import com.example.springjparest.onetomany.simplepk.model.Restaurant;
import com.example.springjparest.onetomany.simplepk.rest.dto.CreateRestaurantAllFieldRequest;
import com.example.springjparest.onetomany.simplepk.rest.dto.CreateRestaurantRequest;
import com.example.springjparest.onetomany.simplepk.rest.dto.RestaurantResponse;
import com.example.springjparest.onetomany.simplepk.rest.dto.UpdateRestaurantRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RestaurantMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    Restaurant toRestaurant(CreateRestaurantRequest createRestaurantRequest);

    RestaurantResponse toRestaurantResponse(Restaurant restaurant);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    void updateRestaurantFromRequest(UpdateRestaurantRequest updateRestaurantRequest,
                                     @MappingTarget Restaurant restaurant);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    @Mapping(target = "name", source = "restaurantName")
    Restaurant createRestaurantFromRequest(CreateRestaurantAllFieldRequest request);

}
