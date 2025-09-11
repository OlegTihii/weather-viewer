package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.weather.dto.SessionDto;
import org.weather.entity.Session;
import org.weather.entity.User;
import org.weather.mapper.SessionMapper;
import org.weather.repository.SessionRepository;
import org.weather.service.SessionService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

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
        log.info("createSession {}", session);
        session = sessionRepository.save(session);
        log.info("SessionServiceImpl sessionEntity {}", session);
        return SessionMapper.INSTANCE.toDto(session);
    }

    @Override
    @Transactional
    public boolean validateSession(String sessionId) {
        UUID uuid = UUID.fromString(sessionId);
        Optional<Session> bySessionId = sessionRepository.findBySessionId(uuid);
        boolean present = bySessionId.filter(this::isSessionActive).isPresent();

        if (!present) {
            removeSession(sessionId);
            return false;
        }

        log.info("validateSession {}", sessionRepository.hasSessionUser(bySessionId.get()));
        return sessionRepository.hasSessionUser(bySessionId.get());
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByIdSession(UUID idForSession) {
        User user = sessionRepository.findBySessionId(idForSession)
                .map(Session::getUser)
                .orElseThrow(() -> new IllegalStateException("Сессия не найдена"));
        Hibernate.initialize(user);

        return user;
    }

    @Override
    @Transactional
    public void removeSession(String cookie) {
        sessionRepository.remove(UUID.fromString(cookie));
    }

    @Override
    @Transactional
    public void deleteExpiredSessions() {
      LocalDateTime nowTime = LocalDateTime.now();
      sessionRepository.deleteExpiredSessions();
    }

    private boolean isSessionActive(Session session) {
        LocalDateTime expiresAt = session.getExpiresAt();
        return expiresAt.isAfter(LocalDateTime.now());
    }
}
