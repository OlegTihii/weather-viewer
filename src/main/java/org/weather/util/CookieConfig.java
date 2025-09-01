package org.weather.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CookieConfig {

    private static final String USER_SESSION = "CurrentSession";
    private static final int EXPIRATION = 60 * 60 * 2;

    public void createCookie(HttpServletResponse response, String token) {

        Cookie cookie = new Cookie(USER_SESSION, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(EXPIRATION);
        response.addCookie(cookie);
    }
}
