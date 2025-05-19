package org.weather.mapper;

import org.mapstruct.Mapper;
import org.weather.dto.WeatherDto;
import org.weather.entity.Location;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    WeatherDto toDto(Location location);

    List<WeatherDto> toListDto(List<Location> locations);
}
