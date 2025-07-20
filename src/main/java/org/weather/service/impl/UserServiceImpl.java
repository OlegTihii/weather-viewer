package org.weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.weather.dto.UserDto;
import org.weather.dto.UserLoginOrRegistrationDto;
import org.weather.entity.User;
import org.weather.mapper.UserMapper;
import org.weather.repository.UserRepository;
import org.weather.service.UserService;

import java.util.Optional;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //todo без transactional не работает. Опять прокси и прочая ересь
    @Override
    @Transactional(readOnly = true)
    public UserDto checkLogin(UserLoginOrRegistrationDto userLoginOrRegistrationDto) {
        Optional<User> byLogin = userRepository.findByLoginAndPassword(UserMapper.INSTANCE.toEntity(userLoginOrRegistrationDto));

        if (byLogin.isEmpty()) {
            throw (new UsernameNotFoundException("User not found"));
        }
        return null;
    }

    @Override
    @Transactional
    public UserDto registration(UserLoginOrRegistrationDto userLoginOrRegistrationDto) {
        userRepository.saveUser(UserMapper.INSTANCE.toEntity(userLoginOrRegistrationDto));
        return null;
    }
}
