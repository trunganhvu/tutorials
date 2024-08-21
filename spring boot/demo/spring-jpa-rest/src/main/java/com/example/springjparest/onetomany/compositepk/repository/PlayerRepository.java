package com.example.springjparest.onetomany.compositepk.repository;

import com.example.springjparest.onetomany.compositepk.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
}
