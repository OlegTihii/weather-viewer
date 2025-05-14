package org.weather.service;

import org.weather.dto.LocationDto;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    List<LocationDto> listLocationsByUserId(int id);

    LocationDto findLocationByCoordinates(int lat, int lon);

    Optional<LocationDto> addLocation(int id);

    boolean deleteLocation(int id);

}
