package org.weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weather.dto.UserDto;
import org.weather.dto.UserRegistrationDto;
import org.weather.mapper.UserMapper;
import org.weather.repository.UserRepository;
import org.weather.service.UserService;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto login(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto registration(UserRegistrationDto userRegistrationDto) {
      userRepository.saveUser(UserMapper.INSTANCE.toEntity(userRegistrationDto));
      return null;
    }
}
