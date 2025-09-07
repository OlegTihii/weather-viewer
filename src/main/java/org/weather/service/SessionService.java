package org.weather.service;

import org.weather.dto.SessionDto;
import org.weather.entity.User;

import java.util.UUID;

public interface SessionService {
    SessionDto createSession(User user);

    boolean validateSession(String sessionId);

    User findUserByIdSession(UUID idForSession);

    void removeSession(String cookie);

    void deleteExpiredSessions();
}
