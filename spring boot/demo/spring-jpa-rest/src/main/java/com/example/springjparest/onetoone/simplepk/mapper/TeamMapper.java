package com.example.springjparest.onetoone.simplepk.mapper;

import com.example.springjparest.onetoone.simplepk.model.Team;
import com.example.springjparest.onetoone.simplepk.model.TeamDetail;
import com.example.springjparest.onetoone.simplepk.rest.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TeamMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teamDetail", ignore = true)
    Team toTeam(CreateTeamRequest createTeamRequest);

    TeamResponse toTeamResponse(Team team);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teamDetail", ignore = true)
    void updateTeamFromRequest(UpdateTeamRequest updateTeamRequest, @MappingTarget Team team);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", ignore = true)
    TeamDetail toTeamDetail(CreateTeamDetailRequest createTeamDetailRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", ignore = true)
    void updateTeamDetailFromRequest(UpdateTeamDetailRequest updateTeamDetailRequest,
                                     @MappingTarget TeamDetail teamDetail);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "description", source = "description")
    TeamDetail createTeamDetailFromRequest(CreateTeamAllFieldsRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teamDetail", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "teamDetail.description", source = "description")
    Team createTeamFromRequest(CreateTeamAllFieldsRequest request);

}