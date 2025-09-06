package org.weather.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

public interface MainWeatherController {

    String findLocation(HttpServletRequest request, String city, Model model);

    String getPersonWeatherCards(HttpServletRequest request, Model model);

    String deleteLocationFromUser(@RequestParam String lat,
                                  @RequestParam String lon,
                                  HttpServletRequest request);
}
