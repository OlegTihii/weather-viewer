package org.weather.repository;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
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
import org.weather.entity.Location;
import org.weather.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceTestConfig.class, HibernateTestConfig.class, LocationRepositoryImpl.class, UserRepositoryImpl.class})
@Transactional
@Slf4j
class LocationRepositoryImplTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationRepository locationRepository;


    User userTest1 = User.builder()
            .login("testUser1")
            .password("testPassword")
            .build();

    Location locationTest1 = Location.builder()
            .city("TestCity1")
            .user(userTest1)
            .latitude(BigDecimal.valueOf(33.333333))
            .longitude(BigDecimal.valueOf(22.222222))
            .build();

    Location locationTest2 = Location.builder()
            .city("TestCity2")
            .user(userTest1)
            .latitude(BigDecimal.valueOf(11.333333))
            .longitude(BigDecimal.valueOf(55.222222))
            .build();

    Location uniqueLocation = Location.builder()
            .city("TestCity1")
            .user(userTest1)
            .latitude(BigDecimal.valueOf(33.333333))
            .longitude(BigDecimal.valueOf(22.222222))
            .build();

    @BeforeEach
    void setUp() {
        locationRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void saveAndFindById_shouldWorkCorrectlyTest() {
        userRepository.saveUser(userTest1);
        locationRepository.save(locationTest1);

        Optional<Location> found = locationRepository.findById(locationTest1.getId());

        assertTrue(found.isPresent());
        assertEquals("TestCity1", found.get().getCity());
        assertNotEquals("Wrong", found.get().getCity());

    }

    @Test
    void saveMoreThanOneLocationTest() {
        userRepository.saveUser(userTest1);
        locationRepository.save(locationTest1);
        locationRepository.save(locationTest2);

        Optional<Location> found1 = locationRepository.findById(locationTest1.getId());
        Optional<Location> found2 = locationRepository.findById(locationTest2.getId());

        assertTrue(found1.isPresent());
        assertTrue(found2.isPresent());
        assertEquals("TestCity1", found1.get().getCity());
        assertEquals("TestCity2", found2.get().getCity());
    }

    @Test
    void saveUniqueLocationTest() {
        userRepository.saveUser(userTest1);
        locationRepository.save(locationTest1);
        log.info("Тест на уникальные значения");

        assertThrows(ConstraintViolationException.class, () -> locationRepository.save(uniqueLocation));
    }

    @Test
    void findLocationByIdTest() {
        userRepository.saveUser(userTest1);
        locationRepository.save(locationTest1);

        Optional<Location> result = locationRepository.findById(1);

        assertTrue(result.isPresent());
        assertEquals("TestCity1", result.get().getCity());
    }

    @Test
    void findLocationByUserId_shouldReturnEmptyTest() {
        userRepository.saveUser(userTest1);
        locationRepository.save(locationTest1);

        Optional<Location> result = locationRepository.findById(333);

        assertTrue(result.isEmpty());
    }

    @Test
    void findLocationByUserIdTest() {
        userRepository.saveUser(userTest1);
        locationRepository.save(locationTest1);
        locationRepository.save(locationTest2);

        List<Location> result = locationRepository.findAllByUserId(1);

        List<String> list = result.stream()
                .map(Location::getCity)
                .toList();

        assertEquals(List.of("TestCity1", "TestCity2"), list);
    }

    @Test
    void findLocationByUserIdTest_shouldReturnEmptyTest() {
        userRepository.saveUser(userTest1);

        List<Location> result = locationRepository.findAllByUserId(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void deleteByUserIdAndCoordinatesTest() {
        userRepository.saveUser(userTest1);
        locationRepository.save(locationTest1);
        locationRepository.save(locationTest2);

        boolean result = locationRepository.deleteByUserIdAndCoordinates(userTest1, locationTest1.getLatitude(), locationTest1.getLongitude());

        assertTrue(result);
    }

    @Test
    void deleteByUserIdAndCoordinatesTest_WrongCoordinate() {
        userRepository.saveUser(userTest1);
        locationRepository.save(locationTest1);
        locationRepository.save(locationTest2);

        boolean result = locationRepository.deleteByUserIdAndCoordinates(userTest1,
                locationTest1.getLatitude(),
                new BigDecimal("77.777777"));

        assertFalse(result);
    }
}