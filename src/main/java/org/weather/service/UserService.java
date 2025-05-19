package org.weather.service;

import org.weather.dto.UserDto;

public interface UserService {

    UserDto login(String username, String password);

    UserDto registration(String username, String password, String repeatPassword);

}
