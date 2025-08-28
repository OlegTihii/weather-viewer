package org.weather.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface SearchLocationResultController {

    String findLocationsByName(String name);

    String findLocationByCoordinates(double lat, double lon);

    String addLocation(@RequestParam String name,
                       @RequestParam BigDecimal lat,
                       @RequestParam BigDecimal lon,
                       @RequestParam String country,
                       HttpServletRequest request,
                       Model model);
}
