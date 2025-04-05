package org.weather.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.weather.entity.User;

import java.util.Optional;

@Repository
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
    public Optional<User> findByLogin(String name) {
        Session session = getCurrentSession();
        Query<User> findByName = session.createQuery("SELECT u FROM User u " +
                "WHERE u.login = :name", User.class).setParameter("name", name);

        return findByName.uniqueResultOptional();
    }

    @Override
    @Transactional
    public Optional<User> saveUser(User user) {

        Session session = getCurrentSession();
        session.persist(user);

        return Optional.of(user);
    }
}
