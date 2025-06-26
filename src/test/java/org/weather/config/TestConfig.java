package org.weather.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.weather.service.ExternalWeatherService;
import org.weather.service.LocationService;
import org.weather.service.impl.WeatherFacadeServiceImpl;

@Configuration
public class TestConfig {

    @Bean
    public LocationService locationService() {
        return Mockito.mock(LocationService.class);
    }

    @Bean
    public ExternalWeatherService externalWeatherService() {
        return Mockito.mock(ExternalWeatherService.class);
    }

    @Bean
    public WeatherFacadeServiceImpl weatherFacadeService(LocationService locationService, ExternalWeatherService externalWeatherService) {
        return new WeatherFacadeServiceImpl(locationService, externalWeatherService);
    }
}
