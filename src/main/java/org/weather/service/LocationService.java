package org.weather.service;

import org.weather.dto.LocationDto;

import java.util.List;

public interface LocationService {

    List<LocationDto> listLocationsByUserId(int id);

    LocationDto findLocationByCoordinates(int lat, int lon);

    void addLocation(LocationDto locationDto, String userCookies);

    boolean deleteLocation(int id);

}
