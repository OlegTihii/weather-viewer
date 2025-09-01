package org.weather.filter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.weather.service.SessionService;

import java.io.IOException;

@Component
@Slf4j
public class SessionInterception implements HandlerInterceptor {
    private static final String CURRENT_SESSION = "CurrentSession";

    private final SessionService sessionService;

    @Autowired
    public SessionInterception(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        Cookie cookie = extractSessionCookie(request);

        if (cookie == null) {
            response.sendRedirect("/login");
            return true;
        }

        return sessionService.validateSession(cookie.getValue());
    }

    //todo возвращать null не самая лучшая идея?
    //todo коряво написаны проверки
    private Cookie extractSessionCookie(HttpServletRequest request) {
        if (request == null || request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(CURRENT_SESSION)) {
                return cookie;
            }
        }
        return null;
    }
}
