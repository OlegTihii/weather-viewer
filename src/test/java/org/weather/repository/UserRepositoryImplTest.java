package org.weather.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.weather.config.DataSourceTestConfig;
import org.weather.config.HibernateTestConfig;
import org.weather.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {HibernateTestConfig.class, DataSourceTestConfig.class, UserRepositoryImpl.class})
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Transactional
@Slf4j
class UserRepositoryImplTest {

    @Autowired
    UserRepository userRepository;

    User userTest = User.builder()
            .login("test")
            .password("test_password")
            .build();

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void saveUserTest() {
        Optional<User> user = userRepository.save(userTest);

        assertTrue(user.isPresent());
        assertEquals(1, user.get().getId());
    }

    @Test
    void findByIdTest() {
        userRepository.save(userTest);

        Optional<User> user = userRepository.findById(1);

        assertTrue(user.isPresent());
        assertEquals("test", user.get().getLogin());
        assertEquals("test_password", user.get().getPassword());
    }


    @Test
    void findByLoginAndPasswordTest() {
        userRepository.save(userTest);

        Optional<User> result = userRepository.findByLoginAndPassword(userTest);

        assertTrue(result.isPresent());
        assertEquals("test", result.get().getLogin());
        assertEquals("test_password", result.get().getPassword());
    }
}