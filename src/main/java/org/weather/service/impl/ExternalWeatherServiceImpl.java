package org.weather.service.impl;

import org.springframework.stereotype.Service;
import org.weather.entity.WeatherInfo;
import org.weather.service.ExternalWeatherService;

import java.math.BigDecimal;

@Service
public class ExternalWeatherServiceImpl implements ExternalWeatherService {


    @Override
    public WeatherInfo getWeather(BigDecimal lat, BigDecimal lon) {
        return null;
    }

    @Override
    public WeatherInfo getLocationsByCity(String city) {
        return null;
    }

}
