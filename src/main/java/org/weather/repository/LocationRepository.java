package org.weather.repository;

import org.weather.entity.Location;
import org.weather.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LocationRepository {

    Optional<Location> findById(int id);

    List<Location> findAllByUserId(int userId);

    List<Location> findAllByName(String location);

    Optional<Location> findByCoordinates(int lat, int lon);

    Optional<Location> save(Location location);

    boolean deleteByUserIdAndCoordinates(User user, BigDecimal latitude, BigDecimal longitude);

    void deleteAll();
}
