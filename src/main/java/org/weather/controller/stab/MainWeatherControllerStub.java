package org.weather.controller.stab;

import org.weather.controller.MainWeatherController;

public class MainWeatherControllerStub implements MainWeatherController {
    @Override
    public String findLocationByName(String name) {
        return null;
    }

    @Override
    public String findLocationByCoordinates(double lat, double lon) {
        return null;
    }

    @Override
    public String deleteLocation(int id) {
        return "You deleted location: " + id;
    }


}
