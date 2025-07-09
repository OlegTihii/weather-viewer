package org.weather.service;

import org.weather.dto.UserDto;
import org.weather.dto.UserRegistrationDto;

public interface UserService {

    UserDto login(UserDto userDto);

    UserDto registration(UserRegistrationDto userRegistrationDto);

}
