package org.weather.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.weather.dto.LocationDto;
import org.weather.entity.Location;
import org.weather.mapper.LocationMapper;
import org.weather.repository.LocationRepository;
import org.weather.service.LocationService;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private static final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }


    @Override
    @Transactional(readOnly = true) //todo почему без него не работает??? Причем тут прокси?
    public List<LocationDto> listLocationsByUserId(int id) {
        List<Location> allByUserId = locationRepository.findAllByUserId(id);
        log.info("Locations: {}", allByUserId);

        List<LocationDto> locationDtos = locationMapper.toListDto(allByUserId);
        log.info("Locations: {}", locationDtos);
        //тестируем не лист дто
        Location loc = new Location();
        loc = allByUserId.get(1);
        LocationDto locationDto = locationMapper.toDto(loc);
        System.out.println("locationDto:" + locationDto);


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
