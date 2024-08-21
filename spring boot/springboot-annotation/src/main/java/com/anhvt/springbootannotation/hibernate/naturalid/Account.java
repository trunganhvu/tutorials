/**
 * Copyright 2024
 * Name: Account
 */
package com.anhvt.springbootannotation.hibernate.naturalid;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.NaturalId;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/15/2024
 */
@Entity
@Table(name = "tbl_account")
public class Account {
    @Id
    private Long id;

    private String name;

    @NaturalId
    private String email;

    @NaturalId(mutable = true)
    private String subEmail;
}
