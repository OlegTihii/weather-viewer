package org.weather.service;

import org.weather.dto.UserDto;
import org.weather.dto.UserLoginOrRegistrationDto;

public interface UserService {

    UserDto checkLogin(UserLoginOrRegistrationDto userLoginOrRegistrationDto);

    UserDto registration(UserLoginOrRegistrationDto userLoginOrRegistrationDto);

}
