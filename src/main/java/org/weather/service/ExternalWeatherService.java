package org.weather.service;

import org.weather.dto.WeatherDto;
import org.weather.entity.Location;

import java.util.List;

public interface ExternalWeatherService {

    WeatherDto getWeather(Location location);

    List<Location> getLocationsByCity(String city);
}


