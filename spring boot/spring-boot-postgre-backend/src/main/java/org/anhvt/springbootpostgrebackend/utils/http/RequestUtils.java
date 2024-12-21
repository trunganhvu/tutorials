package org.anhvt.springbootpostgrebackend.utils.http;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class RequestUtils {
    public static Map<String, String> getRequestInfo(HttpServletRequest request) {
        Map<String, String> info = new HashMap<>();

        // Get IP Address
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        info.put("ipAddress", ipAddress);

        // Get User-Agent
        String userAgent = request.getHeader("User-Agent");
        info.put("userAgent", userAgent != null ? userAgent : "Unknown");

        return info;
    }
}
