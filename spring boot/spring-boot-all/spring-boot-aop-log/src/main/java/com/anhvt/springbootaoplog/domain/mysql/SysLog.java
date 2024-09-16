/**
 * Copyright 2024
 * Name: SysLog
 */
package com.anhvt.springbootaoplog.domain.mysql;

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
@Table(name = "sys_log")
@Data
public class SysLog implements Serializable {

    @Serial
    private static final long serialVersionUID = -2003888302070844917L;

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

