package org.example.springbootliquibase.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "department", schema="public")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    private String name;


}
