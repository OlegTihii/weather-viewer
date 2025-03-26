package org.weather.controller;

public interface MainWeatherController {

    String findLocationByName(String name);

    String findLocationByCoordinates(double lat, double lon);

    String deleteLocation(int id);
}
