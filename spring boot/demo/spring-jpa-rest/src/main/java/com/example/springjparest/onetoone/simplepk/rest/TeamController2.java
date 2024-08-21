package com.example.springjparest.onetoone.simplepk.rest;

import com.example.springjparest.onetoone.simplepk.mapper.TeamMapper;
import com.example.springjparest.onetoone.simplepk.model.Team;
import com.example.springjparest.onetoone.simplepk.model.TeamDetail;
import com.example.springjparest.onetoone.simplepk.rest.dto.CreateTeamAllFieldsRequest;
import com.example.springjparest.onetoone.simplepk.rest.dto.TeamResponse;
import com.example.springjparest.onetoone.simplepk.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teams2")
public class TeamController2 {

    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @GetMapping
    public List<TeamResponse> getTeam() {
        List<Team> teams = teamService.getAllTeam();
        return teams.stream().map(teamMapper::toTeamResponse).toList();
    }

    @GetMapping("/{teamId}")
    public TeamResponse getTeam(@PathVariable String teamId) {
        Team team = teamService.validateAndGetTeam(teamId);
        return teamMapper.toTeamResponse(team);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public TeamResponse createTeam(@Valid @RequestBody CreateTeamAllFieldsRequest request) {
        // Get main object
        Team team = teamMapper.createTeamFromRequest(request);

        // Get sub object
        TeamDetail detail = teamMapper.createTeamDetailFromRequest(request);

        // Set value join column in sub object
        detail.setTeam(team);

        // Set foreign key to main object
        team.setTeamDetail(detail);
        team = teamService.saveTeam(team);
        return teamMapper.toTeamResponse(team);
    }

    @PutMapping("/{teamId}")
    public TeamResponse updateTeam(@PathVariable String teamId,
                                   @Valid @RequestBody CreateTeamAllFieldsRequest request) {

        Team team = teamMapper.createTeamFromRequest(request);
        teamService.updateTeam(teamId, team);
        team = teamService.saveTeam(team);
        return teamMapper.toTeamResponse(team);
    }
}
