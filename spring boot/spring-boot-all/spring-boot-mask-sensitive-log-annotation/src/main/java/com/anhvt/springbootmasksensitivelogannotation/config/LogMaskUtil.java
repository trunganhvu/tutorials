package com.anhvt.springbootmasksensitivelogannotation.config;

import com.anhvt.springbootmasksensitivelogannotation.annotation.LogMask;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.lang.reflect.Field;
import java.util.Objects;

public class LogMaskUtil {

    public static final String MASKSTRING = "*****";

    private LogMaskUtil(){
    }

    public static String mask(String s){
        Objects.requireNonNull(s);
        return MASKSTRING;
    }

    public static String maskPrefix(String s, int prefix){
        Objects.requireNonNull(s);
        if (prefix > 0 && prefix < s.length())
            return MASKSTRING + s.substring(prefix);
        else return MASKSTRING;
    }

    public static String maskSuffix(String s, int suffix){
        Objects.requireNonNull(s);
        if (suffix > 0 && suffix < s.length())
            return s.substring(0, s.length() - suffix) + MASKSTRING;
        else return MASKSTRING;
    }

    public static String toString(Object data) {
        return (new ReflectionToStringBuilder(data) {
            protected Object getValue(Field f) {
                try {
                    Object v = f.get(data);
                    if(!Objects.isNull(v) && f.isAnnotationPresent(LogMask.class)){
                        v = LogMaskDTO.getObject(f, v, MASKSTRING);
                    }
                    return v;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }).toString();
    }
}
