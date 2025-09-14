package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.weather.dto.SessionDto;
import org.weather.dto.UserDto;
import org.weather.dto.UserLoginOrRegistrationDto;
import org.weather.entity.User;
import org.weather.mapper.UserMapper;
import org.weather.repository.UserRepository;
import org.weather.service.SessionService;
import org.weather.service.UserService;

import java.util.Objects;
import java.util.Optional;

@Service("UserServiceImpl")
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final TransactionTemplate transactionTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SessionService sessionService, TransactionTemplate transactionTemplate) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.transactionTemplate = transactionTemplate;
    }

    //todo без transactional не работает. Опять прокси и прочая ересь
    @Override
    public UserDto checkLogin(UserLoginOrRegistrationDto userLoginOrRegistrationDto) {
        Optional<User> byLogin = transactionTemplate.execute(status -> userRepository.findByLoginAndPassword(UserMapper.INSTANCE.toEntity(userLoginOrRegistrationDto)));
        return Objects.requireNonNull(byLogin).map(UserMapper.INSTANCE::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public SessionDto registration(UserLoginOrRegistrationDto userLoginOrRegistrationDto) {
        User userEntity = UserMapper.INSTANCE.toEntity(userLoginOrRegistrationDto);

        Optional<User> byLoginAndPassword = transactionTemplate.execute(status -> userRepository.findByLoginAndPassword(userEntity));

        if (byLoginAndPassword.isPresent()) {
            throw new IllegalStateException("User already exists");
        }

        Optional<User> user = userRepository.save(userEntity);

        return sessionService.createSession(userEntity);

    }

    @Override
    public SessionDto authorisation(UserLoginOrRegistrationDto userLoginOrRegistrationDto) {
        User userEntity = UserMapper.INSTANCE.toEntity(userLoginOrRegistrationDto);

        Optional<User> byLoginAndPassword = transactionTemplate.execute(status -> userRepository.findByLoginAndPassword(userEntity));

        if (byLoginAndPassword.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return sessionService.createSession(byLoginAndPassword.get());
    }
}
