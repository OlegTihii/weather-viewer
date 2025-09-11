package org.weather.controller.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.controller.SearchLocationResultController;
import org.weather.dto.LocationDto;
import org.weather.service.LocationService;
import org.weather.util.ExtractCookieUtil;

import java.math.BigDecimal;

@Controller
@RequestMapping("/locations")
@Slf4j
public class LocationResultControllerImpl implements SearchLocationResultController {

    private final LocationService locationService;

    @Autowired
    public LocationResultControllerImpl(@Qualifier("locationServiceImpl") LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public String findLocationsByName(String name) {
        return "Location weather " + name;
    }

    @Override
    public String findLocationByCoordinates(double lat, double lon) {
        return "Location weather lat " + lat + ", " + "lon " + lon;
    }

    @GetMapping
    public String showLocations(HttpServletRequest request, Model model) {

        return "searchPage";
    }

    @PostMapping("/add")
    @Override
    public String addLocation(@RequestParam String name,
                              @RequestParam BigDecimal lat,
                              @RequestParam BigDecimal lon,
                              @RequestParam String country,
                              @RequestParam boolean alreadyAdded,
                              HttpServletRequest request,
                              Model model) {

        String userCookies = ExtractCookieUtil.extractCookie(request)
                .orElseThrow(() -> new IllegalStateException("Куки не найдена"));

        LocationDto locationDto = LocationDto.builder()
                .name(name)
                .lat(lat)
                .lon(lon)
                .country(country)
                .build();

        locationService.addLocation(locationDto, userCookies);

        return "redirect:/";
    }

}
