package org.weather.repository;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.weather.entity.User;

import java.util.Optional;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(getCurrentSession().get(User.class, id));
    }

    @Override
    public Optional<User> findByLoginAndPassword(User user) {
        Session session = getCurrentSession();
        Query<User> findByName = session.createQuery("SELECT u FROM User u " +
                "WHERE u.login = :name AND u.password = :password", User.class);
        findByName.setParameter("name", user.getLogin());
        findByName.setParameter("password", user.getPassword());

        log.info("Finding by name: {}", findByName.uniqueResultOptional());
        return findByName.uniqueResultOptional();
    }

    @Override
    public Optional<User> save (User user) {

        Session session = getCurrentSession();
        session.persist(user);

        return Optional.of(user);
    }

    public void deleteAll() {
        Session session = getCurrentSession();
        session.createMutationQuery("DELETE FROM User").executeUpdate();
        session.createNativeMutationQuery("ALTER TABLE users ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }
}
