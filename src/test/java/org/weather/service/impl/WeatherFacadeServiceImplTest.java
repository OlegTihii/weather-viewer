package org.weather.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.weather.config.TestConfig;
import org.weather.entity.Location;
import org.weather.entity.WeatherInfo;
import org.weather.service.ExternalWeatherService;
import org.weather.service.LocationService;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class WeatherFacadeServiceImplTest {

    @Autowired
    private WeatherFacadeServiceImpl weatherFacadeService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ExternalWeatherService externalWeatherService;


    @Test
    void getWeatherForUserLocation_returnData() {

        Location location = new Location();
        when(locationService.listLocationsByUserId(1)).thenReturn(Collections.singletonList(location));

        WeatherInfo fakeWeather = WeatherInfo.builder().build();
        when(externalWeatherService.getWeather(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(fakeWeather);

        weatherFacadeService.getWeatherForUserLocation(1);

        verify(locationService).listLocationsByUserId(1);
        verify(externalWeatherService).getWeather(any(), any());
    }


}