package org.weather.service.stub;

import org.weather.dto.LocationDto;
import org.weather.service.LocationService;

import java.util.List;
import java.util.Optional;

public class LocationServiceStub implements LocationService {
    private List<LocationDto> locations;

    @Override
    public List<LocationDto> findLocationByName(String name) {
        locations = List.of(LocationDto.builder().city("Dublin").build()
                , LocationDto.builder().city("Moscow").build()
                , LocationDto.builder().city("New Vasyuki").build());

        return locations;
    }

    @Override
    public Optional<LocationDto> findLocationByCoordinates(double lat, double lon) {
        return Optional.empty();
    }

    @Override
    public boolean addLocation(int id) {
        return false;
    }

    @Override
    public boolean deleteLocation(int id) {
        return false;
    }
}
