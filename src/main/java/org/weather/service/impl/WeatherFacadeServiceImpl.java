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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WeatherFacadeServiceImpl implements WeatherFacadeService {

    private final ExternalWeatherService externalWeatherService;
    private final SessionService sessionService;
    private final LocationService locationService;

    @Autowired
    public WeatherFacadeServiceImpl(ExternalWeatherService externalWeatherService, SessionService sessionService, @Qualifier("locationServiceImpl") LocationService locationService) {
        this.externalWeatherService = externalWeatherService;
        this.sessionService = sessionService;
        this.locationService = locationService;
    }

    //todo Что-то часто запрос в БД с поиском Юзера то по id то по куки. Может его можно куда-то записать?
    @Override
    public List<LocationDto> getLocationsByCity(String userCookie, String city) {
        User user = sessionService.findUserByIdSession(UUID.fromString(userCookie));
        List<Location> locationList = locationService.listLocationsByUserId(user.getId());

        List<LocationDto> searchLocationsResult = externalWeatherService.getLocationsByCity(city);

        for (LocationDto locationDto : searchLocationsResult) {
            BigDecimal latDto = roundToThreeDecimalPlaces(locationDto.getLat());
            BigDecimal lonDto = roundToThreeDecimalPlaces(locationDto.getLon());
            for (Location location : locationList) {
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
    public UserLocationsWeatherDto getUserLocationsWeather(String cookie) {
        User user = sessionService.findUserByIdSession(UUID.fromString(cookie));
        List<Location> listLocations = locationService.listLocationsByUserId(user.getId());
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
