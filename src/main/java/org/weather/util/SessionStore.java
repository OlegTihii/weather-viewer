package org.weather.util;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionStore {

    private final Map<String, Integer> sessions = new ConcurrentHashMap<>();

    public void put(String token, Integer idUser) {
        addCookieForSession();
        sessions.put(token, idUser);
    }

    public void get(String token) {
        sessions.get(token);
    }

    public void remove(String token) {
        sessions.remove(token);
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private Cookie addCookieForSession() {
        Cookie cookie = new Cookie("MySession", generateUUID());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 3);
        return cookie;
    }
}
