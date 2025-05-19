package org.weather.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.dto.WeatherDto;
import org.weather.entity.Location;
import org.weather.entity.WeatherInfo;
import org.weather.service.ExternalWeatherService;
import org.weather.service.LocationService;
import org.weather.service.WeatherFacadeService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WeatherFacadeServiceImpl implements WeatherFacadeService {

    private static final Logger log = LoggerFactory.getLogger(WeatherFacadeServiceImpl.class);
    private final LocationService locationService;
    private final ExternalWeatherService externalWeatherService;

    @Autowired
    public WeatherFacadeServiceImpl(LocationService locationService, ExternalWeatherService externalWeatherService) {
        this.locationService = locationService;
        this.externalWeatherService = externalWeatherService;
    }


    @Override
    public List<WeatherDto> getWeatherForUserLocation(int userId) {
        List<Location> locations = locationService.listLocationsByUserId(userId);
        log.info("Locations: {}", locations);

        //Надо распаралеллить
        WeatherInfo weatherInfo = externalWeatherService.getWeather(new BigDecimal("33.436"), new BigDecimal("66.456"));
        //todo запрос в сервис API для поиска по координатам
        return null;
    }

    @Override
    public List<LocationDto> getLocationsByCity(int userId, String city) {
        WeatherInfo weatherInfo = externalWeatherService.getLocationsByCity(city);

        return null;
    }
}
