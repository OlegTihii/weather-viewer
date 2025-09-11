package org.weather.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceTestConfig.class,
        HibernateTestConfig.class,
        UserServiceImpl.class,
        UserRepositoryImpl.class,
        SessionServiceImpl.class,
        SessionRepositoryImpl.class,})
@ActiveProfiles("test")
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

//    @BeforeEach
//    void setUp() {
//
//        userRepository.deleteAll();
//    }

    @Test
    void checkLogin() {
        UserLoginOrRegistrationDto dto = UserLoginOrRegistrationDto.builder()
                .username("loginTest")
                .password("passwordTest")
                .build();

        UserDto result = transactionTemplate.execute(status -> {
            userRepository.save(testUser);
            return userService.checkLogin(dto);
        });

        assertNotNull(result);
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
    void authorisation() {
    }
}