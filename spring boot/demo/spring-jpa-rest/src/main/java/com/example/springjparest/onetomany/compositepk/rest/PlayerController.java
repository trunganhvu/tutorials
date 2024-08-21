package com.example.springjparest.onetomany.compositepk.rest;

import com.example.springjparest.onetomany.compositepk.mapper.PlayerMapper;
import com.example.springjparest.onetomany.compositepk.mapper.WeaponMapper;
import com.example.springjparest.onetomany.compositepk.model.Player;
import com.example.springjparest.onetomany.compositepk.model.Weapon;
import com.example.springjparest.onetomany.compositepk.rest.dto.CreatePlayerRequest;
import com.example.springjparest.onetomany.compositepk.rest.dto.CreateWeaponRequest;
import com.example.springjparest.onetomany.compositepk.rest.dto.PlayerResponse;
import com.example.springjparest.onetomany.compositepk.rest.dto.WeaponResponse;
import com.example.springjparest.onetomany.compositepk.service.PlayerService;
import com.example.springjparest.onetomany.compositepk.service.WeaponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;
    private final WeaponService weaponService;
    private final PlayerMapper playerMapper;
    private final WeaponMapper weaponMapper;

    // ------
    // Player

    @GetMapping("/{playerId}")
    public PlayerResponse getPlayer(@PathVariable String playerId) {
        Player player = playerService.validateAndGetPlayer(playerId);
        return playerMapper.toPlayerResponse(player);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PlayerResponse createPlayer(@Valid @RequestBody CreatePlayerRequest createPlayerRequest) {
        Player player = playerMapper.toPlayer(createPlayerRequest);
        player = playerService.savePlayer(player);
        return playerMapper.toPlayerResponse(player);
    }

    @DeleteMapping("/{playerId}")
    public PlayerResponse deletePlayer(@PathVariable String playerId) {
        Player player = playerService.validateAndGetPlayer(playerId);
        playerService.deletePlayer(player);
        return playerMapper.toPlayerResponse(player);
    }

    // ------
    // Weapon

    @GetMapping("/{playerId}/weapons/{weaponId}")
    public WeaponResponse getWeapon(@PathVariable String playerId, @PathVariable String weaponId) {
        Weapon weapon = weaponService.validateAndGetWeapon(playerId, weaponId);
        return weaponMapper.toWeaponResponse(weapon);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{playerId}/weapons")
    public WeaponResponse addWeapon(@PathVariable String playerId,
                                    @Valid @RequestBody CreateWeaponRequest createWeaponRequest) {
        Player player = playerService.validateAndGetPlayer(playerId);
        Weapon weapon = weaponMapper.toWeapon(createWeaponRequest);

        // to avoid "org.hibernate.HibernateException: No part of a composite identifier may be null"
        // in spite of the fact that it's set a fixed value here, hibernate will generate a new value
        weapon.setId(String.valueOf(UUID.randomUUID()));
        weapon.setPlayer(player);
        weapon = weaponService.saveWeapon(weapon);

        return weaponMapper.toWeaponResponse(weapon);
    }

    @DeleteMapping("/{playerId}/weapons/{weaponId}")
    public WeaponResponse removeWeapon(@PathVariable String playerId, @PathVariable String weaponId) {
        Weapon weapon = weaponService.validateAndGetWeapon(playerId, weaponId);
        weaponService.deleteWeapon(weapon);
        return weaponMapper.toWeaponResponse(weapon);
    }
}
