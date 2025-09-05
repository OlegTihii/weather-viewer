package org.weather.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

public interface MainWeatherController {

    String findLocation(HttpServletRequest request, String city, Model model);

    String getPersonWeatherCards(HttpServletRequest request, Model model);

    String deleteLocation(int id);
}
