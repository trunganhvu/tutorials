package org.anhvt.springbootmasterslavepostgre.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "test")
@NoArgsConstructor
public class Test {
    @Id
    private Long id;

    private String name;
}
