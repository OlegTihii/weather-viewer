package org.weather.service;

import jakarta.servlet.http.Cookie;
import org.weather.dto.LocationDto;
import org.weather.dto.WeatherDto;

import java.util.List;


public interface WeatherFacadeService {

    List<WeatherDto> getWeatherForUserLocation(int userId);

    public List<LocationDto> getLocationsByCity(String userCookie, String city);
}
