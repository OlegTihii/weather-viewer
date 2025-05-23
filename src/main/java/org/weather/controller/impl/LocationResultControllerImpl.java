package org.weather.controller.impl;

import org.weather.controller.SearchLocationResultController;

public class LocationResultControllerImpl implements SearchLocationResultController {

    @Override
    public String findLocationsByName(String name) {
        return "Location weather " + name;
    }

    @Override
    public String findLocationByCoordinates(double lat, double lon) {
        return "Location weather lat " + lat + ", " + "lon " + lon;
    }

    @Override
    public String addLocation(int id) {
        return "You add new location: " + id;
    }

}
