package org.weather.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.weather.dto.UserLocationsWeatherDto;
import org.weather.entity.Location;
import org.weather.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserLocationsWeatherMapper {

    UserLocationsWeatherMapper INSTANCE = Mappers.getMapper(UserLocationsWeatherMapper.class);

    UserLocationsWeatherDto toDto(User user, List<Location> locationList);
}
