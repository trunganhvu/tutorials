/**
 * Copyright 2024
 * Name: Media
 */
package com.anhvt.springbootannotation.hibernate.lob;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/15/2024
 */
@Entity
@Table(name="tbl_media")
public class Media {
    @Id
    private int id;

    private String name;

    @Lob
    private byte[] images;

    @Lob
    private String content;
}
