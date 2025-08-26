package org.weather.filter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.weather.service.SessionService;

import java.io.IOException;

@Component
public class SessionInterception implements HandlerInterceptor {

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
    private Cookie extractSessionCookie(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("CurrentSession")) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
