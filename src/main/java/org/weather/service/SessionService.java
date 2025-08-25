package org.weather.service;

import org.weather.dto.SessionDto;
import org.weather.dto.UserDto;
import org.weather.dto.UserLoginOrRegistrationDto;
import org.weather.entity.Session;
import org.weather.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface SessionService {
    SessionDto createSession(User user);
    boolean validateSession(String sessionId);
}
