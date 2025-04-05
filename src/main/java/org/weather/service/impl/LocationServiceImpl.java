package org.weather.service.impl;

import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.service.LocationService;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private List<LocationDto> locations;

    @Override
    public List<LocationDto> findLocationByName(String name) {

        return null;

    }

    @Override
    public LocationDto findLocationByCoordinates(int lat, int lon) {

        return null;
    }


    @Override
    public Optional<LocationDto> addLocation(int id) {
        return null;
    }

    @Override
    public boolean deleteLocation(int id) {
        return false;
    }
}
