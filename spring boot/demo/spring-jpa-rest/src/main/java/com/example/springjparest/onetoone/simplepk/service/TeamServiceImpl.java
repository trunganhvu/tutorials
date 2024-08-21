package com.example.springjparest.onetoone.simplepk.service;

import com.example.springjparest.onetoone.simplepk.exception.TeamNotFoundException;
import com.example.springjparest.onetoone.simplepk.model.Team;
import com.example.springjparest.onetoone.simplepk.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public Team validateAndGetTeam(String id) {
        return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Team team) {
        teamRepository.delete(team);
    }

    @Override
    public List<Team> getAllTeam() {
        return teamRepository.findAll();
    }

    @Override
    public Team updateTeam(String teamId, Team team) {
        Team curentTeam = validateAndGetTeam(teamId);
        curentTeam.setName(team.getName());
        curentTeam.getTeamDetail().setDescription(team.getTeamDetail().getDescription());
        return saveTeam(curentTeam);
    }
}
