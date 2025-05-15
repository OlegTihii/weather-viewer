package org.weather.mapper;

import org.mapstruct.Mapper;
import org.weather.dto.LocationDto;
import org.weather.entity.Location;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto toDto(Location location);

    List<LocationDto> toListDto(List<Location> locations);
}
