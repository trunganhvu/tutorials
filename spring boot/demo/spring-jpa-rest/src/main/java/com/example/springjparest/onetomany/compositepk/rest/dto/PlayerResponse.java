package com.example.springjparest.onetomany.compositepk.rest.dto;

import java.util.List;

public record PlayerResponse(String id, String name, List<Weapon> weapons) {

    public record Weapon(String id, String name) {
    }
}
