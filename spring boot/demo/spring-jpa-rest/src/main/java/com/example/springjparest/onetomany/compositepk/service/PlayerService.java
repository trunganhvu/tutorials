package com.example.springjparest.onetomany.compositepk.service;

import com.example.springjparest.onetomany.compositepk.model.Player;
import com.example.springjparest.onetomany.compositepk.rest.dto.CreatePlayerAllFieldRequest;

import java.util.List;

public interface PlayerService {

    Player validateAndGetPlayer(String id);

    Player savePlayer(Player player);

    void deletePlayer(Player player);

    List<Player> getAllPlayer();

    Player updatePlayer(String playerId, CreatePlayerAllFieldRequest request);
}
