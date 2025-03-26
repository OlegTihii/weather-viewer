package org.weather.service;

import org.weather.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    List<Location> findLocationByName(String name);

    Optional<Location> findLocationByCoordinates(double lat, double lon);

    boolean addLocation(int id);

    boolean deleteLocation(int id);

}
