package org.weather.repository;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.weather.entity.Location;
import org.weather.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
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
                "WHERE l.user.id = :userId", Location.class);
        findAllByUserId.setParameter("userId", userId);

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
    public Optional<Location> save(Location location) {
        getCurrentSession().persist(location);
        return Optional.of(location);
    }

    @Override
    public boolean deleteByUserIdAndCoordinates(User user, BigDecimal latitude, BigDecimal longitude) {
        Session currentSession = getCurrentSession();

        List<Location> allByUserId = findAllByUserId(user.getId());
        log.info("deleteByUserIdAndCoordinates start {} {}", user, allByUserId.size());


        int deleteCount = currentSession.createMutationQuery(
                        "DELETE FROM Location l " +
                                "WHERE l.user = :user AND l.latitude = :lat and l.longitude = :lon"
                )
                .setParameter("user", user)
                .setParameter("lat", latitude)
                .setParameter("lon", longitude)
                .executeUpdate();

        List<Location> allByUserId2 = findAllByUserId(user.getId());
        log.info("deleteByUserIdAndCoordinates finish {} {}", user, allByUserId2.size());

        return deleteCount > 0;
    }

    public void deleteAll() {
        Session session = getCurrentSession();
        session.createMutationQuery("DELETE FROM Location").executeUpdate();
        session.createNativeMutationQuery("ALTER TABLE locations ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }
}
