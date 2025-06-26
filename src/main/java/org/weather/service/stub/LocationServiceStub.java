package org.weather.service.stub;

import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.dto.WeatherDto;
import org.weather.entity.Location;
import org.weather.service.LocationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceStub implements LocationService {

    private List<LocationDto> locations;

    @Override
    public List<Location> listLocationsByUserId(int id) {

        return null;

    }

    @Override
    public WeatherDto findLocationByCoordinates(int lat, int lon) {
        return null;
    }

    @Override
    public Optional<WeatherDto> addLocation(int id) {
        return Optional.empty();
    }


    @Override
    public boolean deleteLocation(int id) {
        return false;
    }
}
