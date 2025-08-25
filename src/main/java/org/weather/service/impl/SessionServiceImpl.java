package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.weather.dto.SessionDto;
import org.weather.dto.UserDto;
import org.weather.entity.Session;
import org.weather.entity.User;
import org.weather.mapper.SessionMapper;
import org.weather.mapper.UserMapper;
import org.weather.repository.SessionRepository;
import org.weather.service.SessionService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class SessionServiceImpl implements SessionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionServiceImpl.class);
    private SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    @Transactional
    public SessionDto createSession(User user) {
        Session session = Session.builder()
                .user(user)
                .expiresAt(LocalDateTime.now().plusHours(2))
                .build();

        //todo почему сессия не сохраняется в юзере?
        log.info("HUI {}", session);
        session = sessionRepository.save(session);
        LOGGER.info("SessionServiceImpl sessionEntity {}", session);
        return SessionMapper.INSTANCE.toDto(session);
    }

    @Override
    @Transactional
    public boolean validateSession(String sessionId) {
        UUID uuid = UUID.fromString(sessionId);
        Optional<Session> bySessionId = sessionRepository.findBySessionId(uuid);
        boolean present = bySessionId.filter(this::isSessionActive).isPresent();

        if (!present) {
            removeSession(uuid);
            return false;
        }

        log.info("validateSession {}", sessionRepository.checkSessionByUser(bySessionId.get()));
        return sessionRepository.checkSessionByUser(bySessionId.get());
    }

    public void removeSession(UUID uuid) {
        log.info("removeSession {}", uuid);
        sessionRepository.remove(uuid);
    }

    private boolean isSessionActive(Session session) {
        LocalDateTime expiresAt = session.getExpiresAt();
        return expiresAt.isAfter(LocalDateTime.now());
    }
}
