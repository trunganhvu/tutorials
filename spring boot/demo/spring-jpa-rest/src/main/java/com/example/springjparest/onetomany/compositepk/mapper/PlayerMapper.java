package com.example.springjparest.onetomany.compositepk.mapper;

import com.example.springjparest.onetomany.compositepk.model.Player;
import com.example.springjparest.onetomany.compositepk.rest.dto.CreatePlayerAllFieldRequest;
import com.example.springjparest.onetomany.compositepk.rest.dto.CreatePlayerRequest;
import com.example.springjparest.onetomany.compositepk.rest.dto.PlayerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "weapons", ignore = true)
    Player toPlayer(CreatePlayerRequest createPlayerRequest);

    PlayerResponse toPlayerResponse(Player player);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "weapons", ignore = true)
    @Mapping(target = "name", source = "playerName")
    Player createPlayerFromRequest(CreatePlayerAllFieldRequest request);
}
