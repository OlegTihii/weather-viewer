package org.weather.service;

import org.weather.dto.LocationDto;
import org.weather.dto.UserLocationsWeatherDto;

import java.util.List;


public interface WeatherFacadeService {

    List<LocationDto> getLocationsByCity(String userCookie, String city);

    UserLocationsWeatherDto getUserLocationsWeather(String cookie);
}
