package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.weather.dto.LocationDto;
import org.weather.dto.WeatherDto;
import org.weather.entity.Location;
import org.weather.service.ExternalWeatherService;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class ExternalWeatherServiceImpl implements ExternalWeatherService {
    private final WebClient webClient;

    @Autowired
    public ExternalWeatherServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public WeatherDto getWeather(Location location) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/weather")
                        .queryParam("lat", location.getLatitude())
                        .queryParam("lon", location.getLongitude())
                        .queryParam("appid", "19bc4504ecd219b17890ec5f31f655da")
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND,
                        clientResponse -> {
                            log.warn("Локации с погодой {} не найдены", location);
                            return Mono.empty();
                        })
                .bodyToMono(WeatherDto.class)
                .block();
    }

    @Override
    public List<LocationDto> getLocationsByCity(String city) {
        //todo 1. Если города нет, то отправляет null. Возможно косяк
        //todo 2. Токен у всех на виду

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/geo/1.0/direct")
                        .queryParam("q", city)
                        .queryParam("limit", 5)
                        .queryParam("appid", "19bc4504ecd219b17890ec5f31f655da")
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND,
                        clientResponse -> {
                            log.warn("Города {} не найдены", city);
                            return Mono.empty();
                        })
                .bodyToFlux(LocationDto.class)
                .collectList()
                .block();
    }
}