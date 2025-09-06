package org.weather.service;

import org.weather.dto.LocationDto;
import org.weather.entity.Location;

import java.util.List;

public interface LocationService {

    List<Location> listLocationsByUserId(int id);

    LocationDto findLocationByCoordinates(int lat, int lon);

    void addLocation(LocationDto locationDto, String userCookies);

    void deleteLocation(String cookie, String lat, String lon);
}
