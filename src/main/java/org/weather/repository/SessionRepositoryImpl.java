package org.weather.repository;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.weather.entity.Session;
import org.weather.entity.User;

import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class SessionRepositoryImpl implements SessionRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public SessionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Session> findBySessionId(UUID sessionId) {
        return Optional.ofNullable(getCurrentSession().get(Session.class, sessionId));
    }

    @Override
    @Transactional
    public Session save(Session session) {
        org.hibernate.Session currentSession = getCurrentSession();
        currentSession.persist(session);
        log.info("Saving session with id {} {}", session.getId(), session);
        return session;
    }

    @Override
    public void remove(UUID uuid) {
        log.info("Start remove");
        Session session = getCurrentSession().get(Session.class, uuid);
        if(session != null){
            getCurrentSession().remove(session);
        }
        log.info("Session {}", session);

        log.info("Finish remove");
    }

    @Override
    public boolean hasSessionUser(Session session) {
        log.info("Start checkSessionByUser");
        User user = getCurrentSession().get(User.class, session.getUser().getId());
        log.info("Finish checkSessionByUser {}", user);
        return user != null;
    }

    //todo херь получилась
    private org.hibernate.Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void deleteAll() {
        org.hibernate.Session session = getCurrentSession();
        session.createMutationQuery("DELETE FROM Session").executeUpdate();
        session.createNativeMutationQuery("ALTER TABLE sessions ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }
}
