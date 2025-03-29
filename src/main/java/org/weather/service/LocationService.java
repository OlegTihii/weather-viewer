package org.weather.service;

import org.weather.dto.LocationDto;

import java.util.List;

public interface LocationService {

    List<LocationDto> findLocationByName(String name);

    LocationDto findLocationByCoordinates(int lat, int lon);

    boolean addLocation(int id);

    boolean deleteLocation(int id);

}
