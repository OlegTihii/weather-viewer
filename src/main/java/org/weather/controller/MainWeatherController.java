package org.weather.controller;

public interface MainWeatherController {

    public String findLocationByName(String name);

    public String findLocationByCoordinates(double lat, double lon);
}
