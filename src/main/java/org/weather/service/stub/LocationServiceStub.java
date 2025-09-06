package org.weather.service.stub;

import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.entity.Location;
import org.weather.service.LocationService;

import java.util.List;

@Service
public class LocationServiceStub implements LocationService {

    private List<LocationDto> locations;

    @Override
    public List<Location> listLocationsByUserId(int id) {

        return null;

    }

    @Override
    public LocationDto findLocationByCoordinates(int lat, int lon) {
        return null;
    }

    @Override
    public void addLocation(LocationDto locationDto, String userCookies) {

    }

    @Override
    public void deleteLocation(String cookie, String lat, String lon) {

    }


}
