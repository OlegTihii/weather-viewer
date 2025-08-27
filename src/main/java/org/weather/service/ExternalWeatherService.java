package org.weather.service;

import org.weather.dto.LocationDto;
import org.weather.entity.WeatherInfo;

import java.math.BigDecimal;
import java.util.List;

public interface ExternalWeatherService {

    WeatherInfo getWeather(BigDecimal lat, BigDecimal lon);

    List<LocationDto> getLocationsByCity(String city);
}


