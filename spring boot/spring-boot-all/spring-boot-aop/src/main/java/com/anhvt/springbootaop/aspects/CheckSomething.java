/**
 * Copyright 2024
 * Name: CheckSomething
 */
package com.anhvt.springbootaop.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/16/2024
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckSomething {
    String[] roles() default "";
}
