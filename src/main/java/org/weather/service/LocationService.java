package org.weather.service;

import org.weather.dto.WeatherDto;
import org.weather.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    List<Location> listLocationsByUserId(int id);

    WeatherDto findLocationByCoordinates(int lat, int lon);

    Optional<WeatherDto> addLocation(int id);

    boolean deleteLocation(int id);

}
