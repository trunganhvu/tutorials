package com.anhvt.springbootmasksensitivelogannotation.config;

import com.anhvt.springbootmasksensitivelogannotation.annotation.LogMask;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Objects;

public class LogMaskDTO implements Serializable {

    public static final String MASK = "*****";

    @Override
    public String toString(){
        StringBuilder data = new StringBuilder();
        data.append(this.getClass().getName());
        data.append("[ ");

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                f.setAccessible(true);

                String fN = f.getName();
                data.append(fN);
                data.append("=");
                Object v = f.get(this);
                if(!Objects.isNull(v)){
                    if(f.isAnnotationPresent(LogMask.class)) {
                        v = getObject(f, v, MASK);
                    }
                    data.append(v);
                }
                data.append(", ");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        data.append(" ]");
        return data.toString();
    }

    static Object getObject(Field f, Object v, String mask) {
        LogMask lM = f.getAnnotation(LogMask.class);
        var x = v.toString();
        if (lM.prefix() != 0 && lM.prefix() < x.length())
            v = mask + x.substring(lM.prefix());
        else if (lM.suffix() != 0 && lM.suffix() < x.length())
            v = x.substring(0, x.length() - lM.suffix()) + mask;
        else
            v = mask;
        return v;
    }
}
