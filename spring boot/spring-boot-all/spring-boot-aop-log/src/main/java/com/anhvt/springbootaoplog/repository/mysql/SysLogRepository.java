/**
 * Copyright 2024
 * Name: SysLogRepository
 */
package com.anhvt.springbootaoplog.repository.mysql;

import com.anhvt.springbootaoplog.domain.mysql.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/15/2024
 */
@Repository
public interface SysLogRepository extends JpaRepository<SysLog, Long> {
}
