/**
 * Copyright 2024
 * Name: LogMask1
 */
package com.anhvt.springbootmasksensitivelogannotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogMask {
    int prefix() default 0;
    int suffix() default 0;
}
