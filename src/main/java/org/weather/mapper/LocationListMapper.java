package org.weather.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.weather.dto.LocationDto;
import org.weather.entity.Location;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationListMapper {
    LocationDto toLocationDTO(Location location);

    List<LocationDto> toDTOList(List<Location> locations);

}
