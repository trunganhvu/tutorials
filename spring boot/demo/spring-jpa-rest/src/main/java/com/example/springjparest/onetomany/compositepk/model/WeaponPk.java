package com.example.springjparest.onetomany.compositepk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponPk implements Serializable {

    private String id;
    private String player;
}
