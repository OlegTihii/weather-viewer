package org.weather.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.weather.dto.LocationDto;
import org.weather.entity.Location;
import org.weather.mapper.LocationListMapper;
import org.weather.repository.LocationRepository;
import org.weather.service.LocationService;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class LocationServiceImpl implements LocationService {
    private static final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);
    private final LocationRepository locationRepository;
    private final LocationListMapper locationListMapper;

    public LocationServiceImpl(LocationRepository locationRepository, LocationListMapper locationListMapper) {
        this.locationRepository = locationRepository;
        this.locationListMapper = locationListMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<LocationDto> listLocationsByUserId(int id) {
        List<Location> allByUserId = locationRepository.findAllByUserId(id);
        log.info("Locations: {}", allByUserId);

        return locationListMapper.toDTOList(allByUserId);
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
