package org.weather.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface MainWeatherController {

    String findLocation(HttpServletRequest request, String city, Model model);

    String getPersonWeatherCards(HttpServletRequest request, Model model);

    String deleteLocationFromUser(@RequestParam String lat,
                                  @RequestParam String lon,
                                  HttpServletRequest request);

    String logoutUser(HttpServletRequest request, HttpServletResponse response);
}
