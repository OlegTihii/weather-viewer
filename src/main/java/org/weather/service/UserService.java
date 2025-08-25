package org.weather.service;

import org.weather.dto.SessionDto;
import org.weather.dto.UserDto;
import org.weather.dto.UserLoginOrRegistrationDto;

public interface UserService {

    UserDto checkLogin(UserLoginOrRegistrationDto userLoginOrRegistrationDto);

    SessionDto registration(UserLoginOrRegistrationDto userLoginOrRegistrationDto);

    SessionDto authorisation(UserLoginOrRegistrationDto userLoginOrRegistrationDto);
}
