package org.weather.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class ExtractCookieUtil {
    private static final String USER_SESSION = "CurrentSession";

    public static Optional<String> extractCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> USER_SESSION.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();

    }
}
