package com.example.springjparest.onetomany.compositepk.mapper;

import com.example.springjparest.onetomany.compositepk.model.Weapon;
import com.example.springjparest.onetomany.compositepk.rest.dto.CreateWeaponRequest;
import com.example.springjparest.onetomany.compositepk.rest.dto.WeaponResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeaponMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "player", ignore = true)
    Weapon toWeapon(CreateWeaponRequest createWeaponRequest);

    WeaponResponse toWeaponResponse(Weapon weapon);
}