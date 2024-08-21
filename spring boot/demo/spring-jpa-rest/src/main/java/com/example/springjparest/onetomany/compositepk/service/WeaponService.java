package com.example.springjparest.onetomany.compositepk.service;


import com.example.springjparest.onetomany.compositepk.model.Weapon;

public interface WeaponService {

    Weapon validateAndGetWeapon(String playerId, String weaponId);

    Weapon saveWeapon(Weapon weapon);

    void deleteWeapon(Weapon weapon);

    void deleteWeaponById(String playerId, String weaponId);
}
