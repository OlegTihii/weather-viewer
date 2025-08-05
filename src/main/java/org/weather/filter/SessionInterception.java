package org.weather.filter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.weather.entity.Session;
import org.weather.repository.SessionRepository;
import org.weather.util.SessionStore;

import java.util.Optional;

@Component
public class SessionInterception implements HandlerInterceptor {

    private final SessionStore sessionStore;
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionInterception(SessionStore sessionStore, SessionRepository sessionRepository) {
        this.sessionStore = sessionStore;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("CurrentSession")) {
                    String currentSessionValue = cookie.getValue();
                    Optional<Session> bySessionId = sessionRepository.findBySessionId(currentSessionValue);

                    if (bySessionId.isPresent()) {
                        return true;
                    } else {
                        response.sendRedirect("/login");
                        return false;
                    }
                }
            }
        }
        response.sendRedirect("/login");
        return true;
    }
}
