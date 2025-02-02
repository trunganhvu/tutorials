package org.anhvt.springbootelk.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * {@link User} resource persisted in the database.
 *
 * @author trunganhvu
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -1674204092153306884L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "country", nullable = false)
    private String country;

}

