package org.weather.filter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.weather.service.SessionService;

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
                             Object handler) {

        //todo какого лешего false????
        Cookie cookie = extractSessionCookie(request);
        boolean b = sessionService.validateSession(cookie.getValue());
        return b;
    }


    private Cookie extractSessionCookie(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("CurrentSession")) {
                return cookie;
            }
        }
        return null;
    }


}
