package org.weather.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CookieConfig {

    public void addCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("CurrentSession", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 2);
        response.addCookie(cookie);
    }
}
