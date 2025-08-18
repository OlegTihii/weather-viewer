package org.weather.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.ResourceTransactionManager;
import org.weather.dto.UserDto;
import org.weather.dto.UserLoginOrRegistrationDto;
import org.weather.entity.User;
import org.weather.mapper.UserMapper;
import org.weather.repository.UserRepository;
import org.weather.service.SessionService;
import org.weather.service.UserService;

import java.util.Optional;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    //todo почему IDEA всегда хочет сделать поля final?
    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final ResourceTransactionManager resourceTransactionManager;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, SessionService sessionService, ResourceTransactionManager resourceTransactionManager) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.resourceTransactionManager = resourceTransactionManager;
    }

    //todo без transactional не работает. Опять прокси и прочая ересь
    @Override
    @Transactional(readOnly = true)
    public UserDto checkLogin(UserLoginOrRegistrationDto userLoginOrRegistrationDto) {
//        todo строка 40 и 49 дублируются. Стоит ли выносить в отдельный метод или использовать метод checkLogin и для метода регистрации?
//        todo если из метода регистрации вызвать метод проверки то надо менять возвращаемый объект. Если его поменять, то логика отдачи ДТО в контроллер нарушается.
        Optional<User> byLogin = userRepository.findByLoginAndPassword(UserMapper.INSTANCE.toEntity(userLoginOrRegistrationDto));
        return byLogin.map(UserMapper.INSTANCE::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public UserDto registration(UserLoginOrRegistrationDto userLoginOrRegistrationDto) {
        User userEntity = UserMapper.INSTANCE.toEntity(userLoginOrRegistrationDto);
        Optional<User> byLoginAndPassword = userRepository.findByLoginAndPassword(userEntity);

        if (byLoginAndPassword.isPresent()) {
            throw new IllegalStateException("User already exists");
        }

        //todo  смысл от хибернейта если не сохраняется в сущностях значения
        Optional<User> user = userRepository.saveUser(userEntity);
        sessionService.createSession(userEntity);
        LOGGER.info("UserServiceImpl | registration | {}", user);

        return UserMapper.INSTANCE.toDto(user.orElseThrow());
    }
}
