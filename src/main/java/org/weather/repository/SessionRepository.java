package org.weather.repository;

import org.weather.entity.Session;

import java.util.Optional;

public interface SessionRepository {
    Optional<Session> findBySessionId(String sessionId);

    void save(Session session);
}
