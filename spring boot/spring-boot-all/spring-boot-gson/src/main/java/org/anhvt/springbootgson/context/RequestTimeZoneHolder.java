package org.anhvt.springbootgson.context;

public class RequestTimeZoneHolder {
    private static final ThreadLocal<String> tzHolder = new ThreadLocal<>();

    public static void set(String zone) {
        tzHolder.set(zone);
    }

    public static String get() {
        String zone = tzHolder.get();
        return zone != null ? zone : "UTC";
    }

    public static void clear() {
        tzHolder.remove();
    }
}