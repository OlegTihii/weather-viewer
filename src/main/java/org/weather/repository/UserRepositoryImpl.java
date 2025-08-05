package org.weather.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.ResourceTransactionManager;
import org.weather.entity.User;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private final SessionFactory sessionFactory;
 //   private final ResourceTransactionManager resourceTransactionManager;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory, ResourceTransactionManager resourceTransactionManager) {
        this.sessionFactory = sessionFactory;
    //    this.resourceTransactionManager = resourceTransactionManager;
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

        LOGGER.info("Finding by name: {}", findByName.uniqueResultOptional());
        return findByName.uniqueResultOptional();
    }

    @Override
    public Optional<User> saveUser(User user) {

        Session session = getCurrentSession();
        session.persist(user);

        return Optional.of(user);
    }
}
