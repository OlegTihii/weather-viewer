package org.weather.controller;

import org.springframework.ui.Model;

public interface MainWeatherController {

    String findLocationByName(String city, Model model);

    String findLocationByCoordinates(double lat, double lon, Model model);

    String deleteLocation(int id);
}
