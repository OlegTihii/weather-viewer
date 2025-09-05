package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.dto.UserLocationsWeatherDto;
import org.weather.dto.WeatherDto;
import org.weather.entity.Location;
import org.weather.entity.User;
import org.weather.mapper.LocationMapper;
import org.weather.service.ExternalWeatherService;
import org.weather.service.LocationService;
import org.weather.service.SessionService;
import org.weather.service.WeatherFacadeService;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WeatherFacadeServiceImpl implements WeatherFacadeService {

    private final LocationService locationService;
    private final ExternalWeatherService externalWeatherService;
    private final SessionService sessionService;

    @Autowired
    public WeatherFacadeServiceImpl(@Qualifier("locationServiceImpl") LocationService locationService, ExternalWeatherService externalWeatherService, SessionService sessionService) {
        this.locationService = locationService;
        this.externalWeatherService = externalWeatherService;
        this.sessionService = sessionService;
    }

    //todo Что-то часто запрос в БД с поиском Юзера то по id то по куки. Может его можно куда-то записать?
    @Override
    public List<LocationDto> getLocationsByCity(String userCookie, String city) {
        List<Location> locationsByCity = externalWeatherService.getLocationsByCity(city);
        return LocationMapper.INSTANCE.toListDto(locationsByCity);

    }

    @Override
    public UserLocationsWeatherDto getUserLocationsWeather(String cookie) {
        UUID idForSession = UUID.fromString(cookie);

        User user = sessionService.findUserByIdSession(idForSession);

        List<Location> listLocations = user.getLocations().stream().toList();

        List<LocationDto> listDto = listLocations.stream()
                .map(location -> {
                    LocationDto dto = LocationMapper.INSTANCE.toDto(location);
                    WeatherDto weatherDto = externalWeatherService.getWeather(location);
                    dto.setWeatherDto(weatherDto);
                    return dto;
                })
                .toList();

        UserLocationsWeatherDto build = UserLocationsWeatherDto.builder()
                .username(user.getLogin())
                .locationList(listDto)
                .build();

        log.info("Watching {}", build);
        return build;
    }
}
