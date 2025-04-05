package org.weather.service.stub;

import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.service.LocationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceStub implements LocationService {

    private List<LocationDto> locations;

    @Override
    public List<LocationDto> findLocationByName(String name) {

        return locations = List.of(LocationDto.builder()
                        .city("Dublin")
                        .country("RU")
                        .build(),
                LocationDto.builder()
                        .city("Moscow")
                        .country("RU")
                        .latitude(BigDecimal.valueOf(8.5050281))
                        .longitude(BigDecimal.valueOf(125.9771179))
                        .build(),
                LocationDto.builder()
                        .city("New Vasyuki")
                        .country("RU")
                        .latitude(BigDecimal.valueOf(-31.4249815))
                        .longitude(BigDecimal.valueOf(-62.0840299))
                        .build());

    }

    @Override
    public LocationDto findLocationByCoordinates(int lat, int lon) {
        return LocationDto.builder()
                .city("Dublin")
                .temperature(22)
                .country("RU")
                .humidity(22)
                .build();
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
