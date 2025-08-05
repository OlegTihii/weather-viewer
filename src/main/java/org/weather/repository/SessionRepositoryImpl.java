package org.weather.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.weather.entity.Session;

import java.util.Optional;

@Repository
public class SessionRepositoryImpl implements SessionRepository {
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
    public void save(Session session) {
        org.hibernate.Session currentSession = getCurrentSession();
        currentSession.persist(session);
    }

    private org.hibernate.Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
