package org.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("weather")
public class MainWeatherControllerImp implements MainWeatherController {

    @GetMapping
    public String findLocationByName(@RequestParam("name") String name) {
        return "homePage";
    }

    @GetMapping
    public String findLocationByCoordinates(@RequestParam("lat") double latitude,
                                             @RequestParam("lon") double longitude) {

        return "homePage";
    }

    @Override
    public String deleteLocation(int id) {
        return null;
    }
}