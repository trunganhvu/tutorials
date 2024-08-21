package com.example.springjparest.onetomany.compositepk.rest;

import com.example.springjparest.onetomany.compositepk.mapper.PlayerMapper;
import com.example.springjparest.onetomany.compositepk.mapper.WeaponMapper;
import com.example.springjparest.onetomany.compositepk.model.Player;
import com.example.springjparest.onetomany.compositepk.model.Weapon;
import com.example.springjparest.onetomany.compositepk.rest.dto.CreatePlayerAllFieldRequest;
import com.example.springjparest.onetomany.compositepk.rest.dto.PlayerResponse;
import com.example.springjparest.onetomany.compositepk.service.PlayerService;
import com.example.springjparest.onetomany.compositepk.service.WeaponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/players2")
public class PlayerController2 {
    private final PlayerService playerService;
    private final WeaponService weaponService;
    private final PlayerMapper playerMapper;
    private final WeaponMapper weaponMapper;

    // ------
    // Player

    @GetMapping
    public List<PlayerResponse> getAllPlayer() {
        List<Player> players = playerService.getAllPlayer();
        return players.stream().map(playerMapper::toPlayerResponse).toList();
    }

    @GetMapping("/{playerId}")
    public PlayerResponse getPlayer(@PathVariable String playerId) {
        Player player = playerService.validateAndGetPlayer(playerId);
        return playerMapper.toPlayerResponse(player);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PlayerResponse createPlayer(@Valid @RequestBody CreatePlayerAllFieldRequest request) {
        Player player = playerMapper.createPlayerFromRequest(request);

        // Get foreign keys
        List<String> weapons = request.getWeaponNames();
        if (!weapons.isEmpty()) {
            Player finalPlayer = player;

            // Create new object per keys and setter
            Set<Weapon> weaponSet = new LinkedHashSet<>();

            weapons.forEach(s -> {
                Weapon w = new Weapon();
                w.setPlayerCustom(finalPlayer);
                w.setName(s);
                weaponSet.add(w);
            });

            player.setWeaponCustom(weaponSet);
        }
        player = playerService.savePlayer(player);
        return playerMapper.toPlayerResponse(player);
    }

    @PutMapping("/{playerId}")
    public PlayerResponse updatePlayer(@PathVariable String playerId,
                                       @Valid @RequestBody CreatePlayerAllFieldRequest request) {
        Player currentPlayer = playerService.updatePlayer(playerId, request);

        return playerMapper.toPlayerResponse(currentPlayer);
    }

    @DeleteMapping("/{playerId}")
    public PlayerResponse deletePlayer(@PathVariable String playerId) {
        Player player = playerService.validateAndGetPlayer(playerId);
        playerService.deletePlayer(player);
        return playerMapper.toPlayerResponse(player);
    }
}
