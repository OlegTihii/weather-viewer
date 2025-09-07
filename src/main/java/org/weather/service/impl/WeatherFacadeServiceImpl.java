package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.weather.dto.LocationDto;
import org.weather.dto.UserLocationsWeatherDto;
import org.weather.dto.WeatherDto;
import org.weather.entity.Location;
import org.weather.entity.User;
import org.weather.mapper.LocationMapper;
import org.weather.service.ExternalWeatherService;
import org.weather.service.SessionService;
import org.weather.service.WeatherFacadeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class WeatherFacadeServiceImpl implements WeatherFacadeService {

    private final ExternalWeatherService externalWeatherService;
    private final SessionService sessionService;

    @Autowired
    public WeatherFacadeServiceImpl(ExternalWeatherService externalWeatherService, SessionService sessionService) {
        this.externalWeatherService = externalWeatherService;
        this.sessionService = sessionService;
    }

    //todo Что-то часто запрос в БД с поиском Юзера то по id то по куки. Может его можно куда-то записать?
    @Override
    @Transactional(readOnly = true)
    public List<LocationDto> getLocationsByCity(String userCookie, String city) {
        User user = sessionService.findUserByIdSession(UUID.fromString(userCookie));
        Set<Location> userLocations = user.getLocations();
        List<LocationDto> searchLocationsResult = externalWeatherService.getLocationsByCity(city);

        for (LocationDto locationDto : searchLocationsResult) {
            BigDecimal latDto = roundToThreeDecimalPlaces(locationDto.getLat());
            BigDecimal lonDto = roundToThreeDecimalPlaces(locationDto.getLon());
            for (Location location : userLocations) {
                BigDecimal latLoc = roundToThreeDecimalPlaces(location.getLatitude());
                BigDecimal lonLoc = roundToThreeDecimalPlaces(location.getLongitude());

                if (latDto.compareTo(latLoc) == 0 && lonDto.compareTo(lonLoc) == 0) {
                    locationDto.setAlreadyAdded(true);
                    break;
                }
            }
        }

        return searchLocationsResult;
    }

    @Override
    @Transactional(readOnly = true)
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

    private BigDecimal roundToThreeDecimalPlaces(BigDecimal coordinate) {
        return coordinate.setScale(3, RoundingMode.HALF_UP);
    }
}
