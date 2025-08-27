package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.weather.dto.LocationDto;
import org.weather.entity.WeatherInfo;
import org.weather.service.ExternalWeatherService;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
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
    public WeatherInfo getWeather(BigDecimal lat, BigDecimal lon) {
        return null;
    }

    @Override
    public List<LocationDto> getLocationsByCity(String city) {
        //   http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid={API key}
        //   https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
        //todo 1. Если города нет, то отправляет null. Возможно косяк
        //todo 2. Токен у всех на виду
        log.info("getLocationsByCity start {}", city);
        List<LocationDto> locationDtoList = webClient.get()
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

        log.info("getLocationsByCity finish {}", locationDtoList);
        return locationDtoList;
    }
}

//WeatherInfo weatherInfo = webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/weather")
//                        .queryParam("q", city)
//                        .queryParam("appid", "19bc4504ecd219b17890ec5f31f655da")
//                        .queryParam("units", "metric")
//                        .build())
//                .retrieve()
//                .onStatus(status -> status ==  HttpStatus.NOT_FOUND,
//                        clientResponse -> {
//                            log.warn("Город {} не найден", city);
//                            return Mono.empty();
//                        })
//                .bodyToMono(WeatherInfo.class)
//                .block();
