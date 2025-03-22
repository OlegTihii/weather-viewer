package org.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("weather")
public class WeatherController {

    private final ObjectMapper objectMapper;

    public WeatherController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public String getWeather() throws JsonProcessingException {
        return objectMapper.writeValueAsString(Map.of(
                "temperature", "15°C",
                "city", "Москва"
        ));
    }
}