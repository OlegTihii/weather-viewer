package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.weather.dto.LocationDto;
import org.weather.dto.WeatherDto;
import org.weather.entity.Location;
import org.weather.service.ExternalWeatherService;

import java.util.List;

@Service
@Slf4j
public class ExternalWeatherServiceImpl implements ExternalWeatherService {
    private final RestClient restClient;

    @Autowired
    public ExternalWeatherServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public WeatherDto getWeather(Location location) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/weather")
                        .queryParam("lat", location.getLatitude())
                        .queryParam("lon", location.getLongitude())
                        .queryParam("appid", "19bc4504ecd219b17890ec5f31f655da")
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .body(WeatherDto.class);


    }

    @Override
    public List<LocationDto> getLocationsByCity(String city) {
        //todo 1. Если города нет, то отправляет null. Возможно косяк
        //todo 2. Токен у всех на виду

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/geo/1.0/direct")
                        .queryParam("q", city)
                        .queryParam("limit", 5)
                        .queryParam("appid", "19bc4504ecd219b17890ec5f31f655da")
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

    }
}