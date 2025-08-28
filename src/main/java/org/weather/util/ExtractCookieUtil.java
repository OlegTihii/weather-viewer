package org.weather.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class ExtractCookieUtil {

    public static Optional<String> extractCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> "CurrentSession".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();

    }
}
