package com.example.springjparest.onetoone.simplepk.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "team")
@EqualsAndHashCode(exclude = "team")
@Entity
@Table(name = "team_details")
public class TeamDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", columnDefinition = "VARCHAR(255) REFERENCES teams(id)")
    private Team team;

    @Column(nullable = false)
    private String description;
}
