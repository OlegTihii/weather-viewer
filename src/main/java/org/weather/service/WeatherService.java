package org.weather.service;

import org.springframework.stereotype.Service;
import org.weather.repository.WeatherRepo;

@Service
public class WeatherService {

    private final WeatherRepo weatherRepo;

    public WeatherService(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    public String anyMethod() {
        return weatherRepo.anyRepoMethod();

    }
}
