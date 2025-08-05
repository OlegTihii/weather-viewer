package org.weather.util;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SessionStore {

    //   private static final Map<String, Integer> SESSIONS = new ConcurrentHashMap<>();

    public void put(String token, Integer idUser) {
        //     SESSIONS.put(token, idUser);
    }

    public void get(String token) {
        //   sessions.get(token);
    }

    public void remove(String token) {
        //  sessions.remove(token);
    }

    public String generateSessionUUID() {
        return UUID.randomUUID().toString();
    }

    private Cookie addCookieForSession(String token) {
        Cookie cookie = new Cookie("CurrentSession", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 3);
        return cookie;
    }
}
