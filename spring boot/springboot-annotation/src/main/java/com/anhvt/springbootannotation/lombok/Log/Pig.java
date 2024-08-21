/**
 * Copyright 2024
 * Name: Pig
 */
package com.anhvt.springbootannotation.lombok.Log;

import lombok.extern.slf4j.Slf4j;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
@Slf4j
public class Pig {
    public void printlnPig() {
        log.info("info pig");   // 14:52:58.594 [main] INFO com.anhvt.springbootannotation.lombok.Log.Pig -- info pig

        log.error("error pig");   // 14:52:58.594 [main] ERROR com.anhvt.springbootannotation.lombok.Log.Pig -- error pig

        log.debug("debug pig"); // no print

        log.trace("trace pig");   // no print
        log.warn("warn pig");   // 14:52:58.594 [main] WARN com.anhvt.springbootannotation.lombok.Log.Pig -- warn pig

    }
}
