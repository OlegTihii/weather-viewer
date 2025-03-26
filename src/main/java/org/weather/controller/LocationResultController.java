package org.weather.controller;

public interface LocationResultController {

    String findLocationsByName(String name);

    String findLocationByCoordinates(double lat, double lon);

    String addLocation(int id);
}
