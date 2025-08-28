package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.dto.WeatherDto;
import org.weather.entity.WeatherInfo;
import org.weather.service.ExternalWeatherService;
import org.weather.service.LocationService;
import org.weather.service.WeatherFacadeService;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class WeatherFacadeServiceImpl implements WeatherFacadeService {

    private final LocationService locationService;
    private final ExternalWeatherService externalWeatherService;

    @Autowired
    public WeatherFacadeServiceImpl(@Qualifier("locationServiceImpl") LocationService locationService, ExternalWeatherService externalWeatherService) {
        this.locationService = locationService;
        this.externalWeatherService = externalWeatherService;
    }

    @Override
    public List<WeatherDto> getWeatherForUserLocation(int userId) {
        List<LocationDto> locations = locationService.listLocationsByUserId(userId);
        log.info("Locations: {}", locations);

        //Надо распаралеллить
        WeatherInfo weatherInfo = externalWeatherService.getWeather(new BigDecimal("33.436"), new BigDecimal("66.456"));
        //todo запрос в сервис API для поиска по координатам
        return null;
    }

    //todo Что-то часто запрос в БД с поиском Юзера то по id то по куки. Может его можно куда-то записать?
    @Override
    public List<LocationDto> getLocationsByCity(String userCookie, String city) {
        List<LocationDto> locationsByCity = externalWeatherService.getLocationsByCity(city);
        return locationsByCity;
    }
}
