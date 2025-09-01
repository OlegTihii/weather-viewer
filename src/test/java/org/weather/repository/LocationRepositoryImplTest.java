package org.weather.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.weather.config.DataSourceConfig;
import org.weather.config.HibernateConfig;
import org.weather.entity.Location;
import org.weather.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DataSourceConfig.class})
@Transactional
class LocationRepositoryImplTest {

    @Autowired
    LocationRepositoryImpl locationRepository;

    User userTest = User.builder()
            .login("testUser")
            .password("testPassword")
            .build();

    Location locationTest = Location.builder()
            .city("TestCity")
            .user(userTest)
            .latitude(BigDecimal.valueOf(33.333333))
            .longitude(BigDecimal.valueOf(22.222222))
            .build();


    @Test
    @Rollback
    void saveAndFindById_shouldWorkCorrectly() {
        //   userRepositoryImpl.saveUser(userTest);
        locationRepository.save(locationTest);

        Optional<Location> found = locationRepository.findById(locationTest.getId());


    }


    @Test
    void findAllByUserId() {
    }

    @Test
    void findAllByName() {
    }

    @Test
    void findByCoordinates() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }
}