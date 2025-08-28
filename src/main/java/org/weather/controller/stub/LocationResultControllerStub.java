package org.weather.controller.stub;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.weather.controller.SearchLocationResultController;

import java.math.BigDecimal;

public class LocationResultControllerStub implements SearchLocationResultController {
    @Override
    public String findLocationsByName(String name) {
        return "Location weather " + name;
    }

    @Override
    public String findLocationByCoordinates(double lat, double lon) {
        return "Location weather lat " + lat + ", " + "lon " + lon;
    }

    @Override
    public String addLocation(String name, BigDecimal lat, BigDecimal lon, String country, HttpServletRequest request, Model model) {
        return "";
    }


}
