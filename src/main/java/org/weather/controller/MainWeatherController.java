package org.weather.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.weather.entity.Session;
import org.weather.entity.User;

public interface MainWeatherController {

    String findLocation(HttpServletRequest request, String city, Model model);

    String getPersonWeatherCards(User user, Session session, Model model);

    String deleteLocation(int id);
}
