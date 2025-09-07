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
import org.weather.entity.Session;
import org.weather.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Transactional
@ContextConfiguration(classes = {DataSourceTestConfig.class, HibernateTestConfig.class, SessionRepositoryImpl.class, UserRepositoryImpl.class})
@Slf4j
class SessionRepositoryImplTest {

    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    UserRepository userRepository;

    User userTest;
    Session sessionTest;


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        userTest = User.builder()
                .login("test")
                .password("test_password")
                .build();

        sessionTest = Session.builder()
                .user(userTest)
                .expiresAt(LocalDateTime.of(2020, 1, 10, 15, 16, 17))
                .build();
    }


    @Test
    void saveTest() {
        userRepository.save(userTest);

        Session save = sessionRepository.save(sessionTest);

        assertNotNull(save);
        assertEquals(LocalDateTime.of(2020, 1, 10, 15, 16, 17), save.getExpiresAt());
    }

    @Test
    void findBySessionIdTest() {
        userRepository.save(userTest);
        Session save = sessionRepository.save(sessionTest);

        Optional<Session> result = sessionRepository.findBySessionId(save.getId());

        assertTrue(result.isPresent());
        assertEquals(save.getId(), result.get().getId());
        assertEquals(save.getUser().getLogin(), result.get().getUser().getLogin());
    }

    @Test
    void findBySessionId_whenSessionIsEmpty() {
        UUID uuid = UUID.randomUUID();

        Optional<Session> result = sessionRepository.findBySessionId(uuid);

        assertFalse(result.isPresent());
    }

    @Test
    void removeTest() {
        userRepository.save(userTest);
        Session save = sessionRepository.save(sessionTest);

        sessionRepository.remove(save.getId());
        Optional<Session> result = sessionRepository.findBySessionId(save.getId());

        assertTrue(result.isEmpty());
    }

    @Test
    void removeTest_whenRemoveFailed() {
        UUID uuid = UUID.randomUUID();
        userRepository.save(userTest);
        Session save = sessionRepository.save(sessionTest);

        sessionRepository.remove(uuid);
        Optional<Session> result = sessionRepository.findBySessionId(save.getId());

        assertTrue(result.isPresent());
    }

    @Test
    void hasSessionUserTest() {
        userRepository.save(userTest);
        sessionRepository.save(sessionTest);

        boolean result = sessionRepository.hasSessionUser(sessionTest);

        assertTrue(result);
    }

    @Test
    void hasSessionUserTestFailed() {
        boolean result = sessionRepository.hasSessionUser(sessionTest);

        assertFalse(result);
    }

    @Test
    void deleteExpiredSessionsTest() {
        userRepository.save(userTest);
        sessionRepository.save(sessionTest);

        boolean result = sessionRepository.deleteExpiredSessions();

        assertTrue(result);
    }

    @Test
    void deleteExpiredSessions_NoDeletion() {
        userRepository.save(userTest);
        sessionTest.setExpiresAt(LocalDateTime.now().plusHours(2));
        sessionRepository.save(sessionTest);

        boolean result = sessionRepository.deleteExpiredSessions();

        assertFalse(result);
    }
}