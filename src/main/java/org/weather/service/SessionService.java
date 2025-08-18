package org.weather.service;

import org.weather.dto.SessionDto;
import org.weather.entity.Session;
import org.weather.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface SessionService {
    SessionDto createSession(User user);
    Optional<SessionDto> validateSession(UUID sessionId);
}
