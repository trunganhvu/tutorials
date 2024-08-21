package com.example.springjparest.onetomany.compositepk.service;

import com.example.springjparest.onetomany.compositepk.exception.WeaponNotFoundException;
import com.example.springjparest.onetomany.compositepk.model.Weapon;
import com.example.springjparest.onetomany.compositepk.model.WeaponPk;
import com.example.springjparest.onetomany.compositepk.repository.WeaponRepository;
import com.example.springjparest.onetomany.compositepk.repository.WeaponRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeaponServiceImpl implements WeaponService {

    private final WeaponRepository weaponRepository;

    private final WeaponRepository2 weaponRepository2;

    @Override
    public Weapon validateAndGetWeapon(String playerId, String weaponId) {
        WeaponPk weaponPk = new WeaponPk(weaponId, playerId);
        return weaponRepository.findById(weaponPk).orElseThrow(() -> new WeaponNotFoundException(weaponPk));
    }

    @Override
    public Weapon saveWeapon(Weapon weapon) {
        return weaponRepository.save(weapon);
    }

    @Override
    public void deleteWeapon(Weapon weapon) {
        weaponRepository.delete(weapon);
    }

    @Override
    public void deleteWeaponById(String playerId, String weaponId) {
        WeaponPk weaponPk = new WeaponPk(weaponId, playerId);
        Weapon weapon = weaponRepository.findById(weaponPk).orElseThrow(() -> new WeaponNotFoundException(weaponPk));
        weaponRepository2.delete(weapon);
    }
}
