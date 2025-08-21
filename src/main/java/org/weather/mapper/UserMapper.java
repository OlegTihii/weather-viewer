package org.weather.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.weather.dto.UserDto;
import org.weather.dto.UserLoginOrRegistrationDto;
import org.weather.dto.UserRegistrationDto;
import org.weather.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    @Mapping(source = "username", target = "login")
    User toEntity(UserLoginOrRegistrationDto userLoginOrRegistrationDto);

    @Mapping(source = "login", target = "login")
    @Mapping(target = "session", expression = "java(user.getSessions().stream().findFirst().map(s -> s.getId().toString()).orElse(null))")
    UserRegistrationDto toRegistrationDto(User user);
}
