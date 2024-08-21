package com.example.springjparest.onetomany.compositepk.repository;

import com.example.springjparest.onetomany.compositepk.model.Weapon;
import com.example.springjparest.onetomany.compositepk.model.WeaponPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, WeaponPk> {
}
