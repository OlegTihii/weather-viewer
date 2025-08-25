package org.weather.repository;

import org.weather.entity.Session;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {
    Optional<Session> findBySessionId(UUID sessionId);

    Session save(Session session);

    void remove(UUID uuid);

    boolean checkSessionByUser(Session session);
}
