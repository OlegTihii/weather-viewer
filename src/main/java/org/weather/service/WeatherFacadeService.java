package org.weather.service;

import org.weather.dto.LocationDto;
import org.weather.dto.WeatherDto;

import java.util.List;


public interface WeatherFacadeService {

    List<WeatherDto> getWeatherForUserLocation(int userId);

    public List<LocationDto> getLocationsByCity(int userId, String city);
}
