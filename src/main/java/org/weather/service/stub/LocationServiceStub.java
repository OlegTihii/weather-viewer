package org.weather.service.stub;

import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.service.LocationService;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceStub implements LocationService {
    private List<LocationDto> locations;

    @Override
    public List<LocationDto> findLocationByName(String name) {

        return locations = List.of(LocationDto.builder().city("Dublin").build()
                , LocationDto.builder().city("Moscow").build()
                , LocationDto.builder().city("New Vasyuki").build());

    }

    @Override
    public Optional<LocationDto> findLocationByCoordinates(double lat, double lon) {
        return Optional.ofNullable(LocationDto.builder().city("Dublin").temperature((short) 22).build());
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
