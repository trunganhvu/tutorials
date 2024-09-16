/**
 * Copyright 2024
 * Name: PostLog
 */
package com.anhvt.springbootaoplog.domain.postgresql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/15/2024
 */
@Entity
@Table(name = "post_log")
@Data
public class PostLog implements Serializable {
    @Serial
    private static final long serialVersionUID = 8613838809266028690L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "operation")
    private String operation;
    @Column(name = "time")
    private Integer time;
    @Column(name = "method")
    private String method;
    @Column(name = "params")
    private String params;
    @Column(name = "ip")
    private String ip;
    @Column(name = "create_time")
    private Date createTime;
}

