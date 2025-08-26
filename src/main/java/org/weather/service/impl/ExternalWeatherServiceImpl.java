package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.weather.entity.WeatherInfo;
import org.weather.service.ExternalWeatherService;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

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
    public WeatherInfo getLocationsByCity(String city) {
        //   https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
        //todo 1. Если города нет, то отправляет null. Возможно косяк
        //todo 2. Токен у всех на виду
        log.info("getLocationsByCity start {}", city);

        WeatherInfo weatherInfo = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .queryParam("q", city)
                        .queryParam("appid", "19bc4504ecd219b17890ec5f31f655da")
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .onStatus(status -> status ==  HttpStatus.NOT_FOUND,
                        clientResponse -> {
                            log.warn("Город {} не найден", city);
                            return Mono.empty();
                        })
                .bodyToMono(WeatherInfo.class)
                .block();

        log.info("getLocationsByCity finish {}", weatherInfo);
        return weatherInfo;
    }
}
