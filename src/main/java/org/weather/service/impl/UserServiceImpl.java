package org.weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weather.dto.WeatherDto;
import org.weather.dto.UserDto;
import org.weather.repository.UserRepository;
import org.weather.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  //  private final UserRepository userRepository;

    //todo где лучше объявлять list? тут или в методе?
    List<WeatherDto> locations = new ArrayList<>();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
      //  this.userRepository = userRepository;
    }

    @Override
    public UserDto login(String username, String password) {
        return null;
    }

    @Override
    public UserDto registration(String username, String password, String repeatPassword) {
        return null;
    }

}
