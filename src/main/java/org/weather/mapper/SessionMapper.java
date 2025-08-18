package org.weather.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.weather.dto.SessionDto;
import org.weather.entity.Session;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    SessionDto toDto(Session session);
    Session toSession(SessionDto sessionDto);
}
