package org.weather.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.weather.dto.LocationDto;
import org.weather.entity.Location;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {

    LocationDto toDto(Location location);

}
