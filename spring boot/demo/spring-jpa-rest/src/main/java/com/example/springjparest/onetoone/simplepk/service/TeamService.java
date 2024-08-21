package com.example.springjparest.onetoone.simplepk.service;

import com.example.springjparest.onetoone.simplepk.model.Team;

import java.util.List;

public interface TeamService {
    Team validateAndGetTeam(String id);

    Team saveTeam(Team team);

    void deleteTeam(Team team);

    List<Team> getAllTeam();

    Team updateTeam(String teamId, Team team);
}
