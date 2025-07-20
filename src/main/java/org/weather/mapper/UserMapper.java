package org.weather.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.weather.dto.UserDto;
import org.weather.dto.UserLoginOrRegistrationDto;
import org.weather.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    @Mapping(source = "username", target = "login")
    User toEntity(UserLoginOrRegistrationDto userLoginOrRegistrationDto);
}
