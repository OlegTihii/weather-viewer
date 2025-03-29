package org.weather.service.stub;

import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.service.LocationService;

import java.util.List;

@Service
public class LocationServiceStub implements LocationService {

    private List<LocationDto> locations;

    @Override
    public List<LocationDto> findLocationByName(String name) {

        return locations = List.of(LocationDto.builder().city("Dublin").country("RU").humidity(67).temperature(10).build(),
                LocationDto.builder().city("Moscow").country("RU").humidity(22).temperature(17).build(),
                LocationDto.builder().city("New Vasyuki").country("RU").humidity(43).temperature(28).build());

    }

    @Override
    public LocationDto findLocationByCoordinates(int lat, int lon) {
        return LocationDto.builder().city("Dublin").temperature(22).country("RU").humidity(22).build();
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
