package org.weather.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.weather.entity.Location;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepositoryImpl implements LocationRepository {


    private final SessionFactory sessionFactory;

    @Autowired
    public LocationRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Optional<Location> findById(int id) {
        return Optional.ofNullable(getCurrentSession().get(Location.class, id));
    }

    @Override
    public List<Location> findAllByUserId(int userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Location> findAllByUserId = session.createQuery("SELECT l FROM Location l " +
                "WHERE l.userId = :userId", Location.class).setParameter("userId", userId);

        return findAllByUserId.list();
    }

    @Override
    public List<Location> findAllByName(String location) {
        return null;
    }

    @Override
    public Optional<Location> findByCoordinates(int lat, int lon) {
        return Optional.empty();
    }

    @Override
    public Optional<Location> save(int id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
