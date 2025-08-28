package org.weather.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.weather.dto.LocationDto;
import org.weather.entity.Location;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(target = "name", source = "city")
    @Mapping(target = "lat", source = "latitude")
    @Mapping(target = "lon", source = "longitude")
    LocationDto toDto(Location location);

    List<LocationDto> toListDto(List<Location> locations);


    @Mapping(target = "city", source = "name")
    @Mapping(target = "latitude", source = "lat")
    @Mapping(target = "longitude", source = "lon")
    Location toEntity(LocationDto locationDto);
}
