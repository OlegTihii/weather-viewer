package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.support.TransactionTemplate;
import org.weather.config.DataSourceTestConfig;
import org.weather.config.HibernateTestConfig;
import org.weather.dto.SessionDto;
import org.weather.dto.UserDto;
import org.weather.dto.UserLoginOrRegistrationDto;
import org.weather.entity.User;
import org.weather.repository.SessionRepositoryImpl;
import org.weather.repository.UserRepository;
import org.weather.repository.UserRepositoryImpl;
import org.weather.service.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceTestConfig.class,
        HibernateTestConfig.class,
        UserServiceImpl.class,
        UserRepositoryImpl.class,
        SessionServiceImpl.class,
        SessionRepositoryImpl.class,})
@ActiveProfiles("test")
@Slf4j
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionTemplate transactionTemplate;


    User testUser = User.builder()
            .login("loginTest")
            .password("passwordTest")
            .build();

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void checkProxy() {
        System.out.println("UserService bean class: " + userService.getClass());
        log.info("proxy? {}", userService.getClass());
    }

    @Test
    void checkLogin() {

        UserLoginOrRegistrationDto dto = UserLoginOrRegistrationDto.builder()
                .username("loginTest")
                .password("passwordTest")
                .build();

        userRepository.save(testUser);
        UserDto result = userService.checkLogin(dto);

        assertNotNull(result);
    }

    @Test
    void checkLogin_userDontFind() {

        UserLoginOrRegistrationDto dto = UserLoginOrRegistrationDto.builder()
                .username("loginTest")
                .password("passwordTest")
                .build();

        assertThrows(UsernameNotFoundException.class, () -> userService.checkLogin(dto));
    }

    @Test
    void registration() {
        UserLoginOrRegistrationDto userLocationsWeatherDto = UserLoginOrRegistrationDto.builder()
                .username("loginTest")
                .password("passwordTest")
                .build();

        SessionDto registration = userService.registration(userLocationsWeatherDto);

        assertNotNull(registration);
    }

    @Test
    void registration_dontSuccess() {
        UserLoginOrRegistrationDto userLocationsWeatherDto = UserLoginOrRegistrationDto.builder()
                .username("loginTest")
                .password("passwordTest")
                .build();

        userService.registration(userLocationsWeatherDto);

        assertThrows(IllegalStateException.class, () -> userService.registration(userLocationsWeatherDto));
    }

    @Test
    void authorisation() {
        UserLoginOrRegistrationDto userLocationsWeatherDto = UserLoginOrRegistrationDto.builder()
                .username("loginTest")
                .password("passwordTest")
                .build();

        userService.registration(userLocationsWeatherDto);

        SessionDto result = userService.authorisation(userLocationsWeatherDto);

        assertNotNull(result);
    }

    @Test
    void authorisation_UserNameNotFound() {
        UserLoginOrRegistrationDto userLocationsWeatherDto = UserLoginOrRegistrationDto.builder()
                .username("loginTest")
                .password("passwordTest")
                .build();

        assertThrows(UsernameNotFoundException.class, () -> userService.authorisation(userLocationsWeatherDto));
    }
}