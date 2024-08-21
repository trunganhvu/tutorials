/**
 * Copyright 2024
 * Name: Event
 */
package com.anhvt.springbootannotation.hibernate.column;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/14/2024
 */
@Entity(name = "tbl_event")
@Table
public class Event {
    @Id
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_name", unique = true, nullable = false, length = 30)
    private String name;

    @Column(name = "event_title", insertable = false, updatable = false)
    private String title;

    @Column(name = "event_price", precision = 0)
    private Double price;

    @Column(name = "event_cost", scale = 0)
    private Double cost;
}
