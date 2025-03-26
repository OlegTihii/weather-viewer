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

        return locations = List.of(LocationDto.builder().city("Dublin").country("RU").humidity((byte) 67).temperature((short) 10).build(),
                LocationDto.builder().city("Moscow").country("RU").humidity((byte) 22).temperature((short) 17).build(),
                LocationDto.builder().city("New Vasyuki").country("RU").humidity((byte) 43).temperature((short) 28).build());

    }

    @Override
    public LocationDto findLocationByCoordinates(double lat, double lon) {
        return LocationDto.builder().city("Dublin").temperature((short) 22).country("RU").humidity((byte) 22).build();
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
