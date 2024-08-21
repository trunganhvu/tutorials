package com.example.springjparest.onetomany.compositepk.service;

import com.example.springjparest.onetomany.compositepk.exception.PlayerNotFoundException;
import com.example.springjparest.onetomany.compositepk.model.Player;
import com.example.springjparest.onetomany.compositepk.model.Weapon;
import com.example.springjparest.onetomany.compositepk.repository.PlayerRepository;
import com.example.springjparest.onetomany.compositepk.rest.dto.CreatePlayerAllFieldRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final WeaponService weaponService;

    @Override
    public Player validateAndGetPlayer(String id) {
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
    }

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }

    @Override
    public List<Player> getAllPlayer() {
        return playerRepository.findAll();
    }

    @Transactional
    @Override
    public Player updatePlayer(String playerId, CreatePlayerAllFieldRequest request) {
        Player currentPlayer = validateAndGetPlayer(playerId);
        // Set new name
        currentPlayer.setName(request.getPlayerName());

        // Clean all data in Set
        for (Weapon weapon : currentPlayer.getWeapons()) {
            weaponService.deleteWeapon(weapon);
        }
        currentPlayer.clearWeapons();

        // Get weapon in request
        List<String> weapons = request.getWeaponNames();
        if (!weapons.isEmpty()) {

            // Create new object per keys and re-setter
            Set<Weapon> weaponSet = weapons.stream().map(s -> {
                Weapon w = new Weapon();
                w.setPlayer(currentPlayer);
                w.setName(s);
                return w;
            }).collect(Collectors.toSet());

            currentPlayer.setWeaponCustom(weaponSet);
        }

        return savePlayer(currentPlayer);
    }
}
