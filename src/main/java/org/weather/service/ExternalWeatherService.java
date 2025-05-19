package org.weather.service;

import org.weather.entity.WeatherInfo;

import java.math.BigDecimal;

public interface ExternalWeatherService {

    WeatherInfo getWeather(BigDecimal lat, BigDecimal lon);

    WeatherInfo getLocationsByCity(String city);
}


