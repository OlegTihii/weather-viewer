package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.weather.dto.WeatherDto;
import org.weather.entity.Location;
import org.weather.service.ExternalWeatherService;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;

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
        //https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
        log.info("getWeather start {}", location);


        WeatherDto weatherDto = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/weather")
                        .queryParam("lat", String.format(Locale.US, "%.2f", location.getLatitude()))
                        .queryParam("lon", String.format(Locale.US, "%.2f", location.getLongitude()))
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

        log.info("getWeather finish {}", weatherDto);
        return weatherDto;
    }

    @Override
    public List<Location> getLocationsByCity(String city) {
        //   http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid={API key}
        //   https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
        //todo 1. Если города нет, то отправляет null. Возможно косяк
        //todo 2. Токен у всех на виду
        log.info("getLocationsByCity start {}", city);
        List<Location> locationList = webClient.get()
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
                .bodyToFlux(Location.class)
                .collectList()
                .block();

        log.info("getLocationsByCity finish {}", locationList);
        return locationList;
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
