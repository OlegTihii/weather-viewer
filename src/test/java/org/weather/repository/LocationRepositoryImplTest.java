package org.weather.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.weather.config.DataSourceTestConfig;
import org.weather.config.HibernateConfig;
import org.weather.entity.Location;
import org.weather.entity.User;

import java.math.BigDecimal;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DataSourceTestConfig.class, LocationRepositoryImpl.class})
@Transactional
class LocationRepositoryImplTest {

    @Autowired(required = false)
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

    @Autowired
    ApplicationContext context;

    @Test
    void contexLoad() {
        System.out.println("Старт");
        for (String s : context.getBeanNamesForType(Object.class)) {
            System.out.println(s);
        }
    }


    @Test
    @Rollback
    void saveAndFindById_shouldWorkCorrectly() {
        //   userRepositoryImpl.saveUser(userTest);
//        locationRepository.save(locationTest);
//
//        Optional<Location> found = locationRepository.findById(locationTest.getId());

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