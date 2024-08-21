package com.example.springjparest.onetoone.simplepk.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "teamDetail")
@EqualsAndHashCode(exclude = "teamDetail")
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TeamDetail teamDetail;

    @Column(nullable = false)
    private String name;

    public void addTeamDetail(TeamDetail teamDetail) {
        this.teamDetail = teamDetail;
        teamDetail.setTeam(this);
    }

    public void removeTeamDetail() {
        this.teamDetail.setTeam(null);
        this.teamDetail = null;
    }
}
