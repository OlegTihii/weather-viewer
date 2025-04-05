package org.weather.repository;

import org.weather.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {

    Optional<Location> findById(int id);

    List<Location> findLocationByUserId(int userId);

    List<Location> findLocationByName(String location);

    Optional<Location> findLocationByCoordinates(int lat, int lon);

    Optional<Location> saveLocation(int id);

    boolean deleteLocation(int id);
}
