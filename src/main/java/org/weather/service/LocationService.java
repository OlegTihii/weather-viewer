package org.weather.service;

import org.weather.dto.LocationDto;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    List<LocationDto> findLocationByName(String name);

    Optional<LocationDto> findLocationByCoordinates(double lat, double lon);

    boolean addLocation(int id);

    boolean deleteLocation(int id);

}
