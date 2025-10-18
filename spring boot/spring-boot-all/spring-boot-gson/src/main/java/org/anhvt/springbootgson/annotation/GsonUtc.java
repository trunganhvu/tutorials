package org.anhvt.springbootgson.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface GsonUtc {
    String value() default "UTC";
}