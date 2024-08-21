package com.example.springjparest.onetoone.sharedpk.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "person")
@EqualsAndHashCode(exclude = "person")
@Entity
@Table(name = "person_details")
public class PersonDetail {

    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Person person;

    @Column(nullable = false)
    private String description;
}
