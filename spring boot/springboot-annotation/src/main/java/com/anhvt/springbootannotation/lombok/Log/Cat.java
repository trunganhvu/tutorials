/**
 * Copyright 2024
 * Name: Cat
 */
package com.anhvt.springbootannotation.lombok.Log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
@CommonsLog
public class Cat {

    public void println() {
        log.info("start");  // 14:47:40.061 [main] INFO com.anhvt.springbootannotation.lombok.Log.Cat -- start

        log.debug("debug log"); // No print

        log.error("finish");    // 14:47:40.077 [main] ERROR com.anhvt.springbootannotation.lombok.Log.Cat -- finish
    }
}
