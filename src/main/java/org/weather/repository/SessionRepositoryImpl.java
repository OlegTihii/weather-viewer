package org.weather.repository;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.weather.entity.Session;

import java.util.Optional;

@Repository
public class SessionRepositoryImpl implements SessionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public SessionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Session> findBySessionId(String sessionId) {
        return Optional.ofNullable(getCurrentSession().get(Session.class, sessionId));
    }

    @Override
    @Transactional
    public Session save(Session session) {
        org.hibernate.Session currentSession = getCurrentSession();
        currentSession.persist(session);
        LOGGER.info("Saving session with id {} {}", session.getId(), session);
        return session;
    }

    //todo херь получилась
    private org.hibernate.Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
