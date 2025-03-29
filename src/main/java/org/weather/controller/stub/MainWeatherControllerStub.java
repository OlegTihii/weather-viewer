package org.weather.controller.stub;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.controller.MainWeatherController;
import org.weather.dto.LocationDto;
import org.weather.service.LocationService;

import java.util.List;

@Controller
public class MainWeatherControllerStub implements MainWeatherController {
    private final LocationService locationServiceStub;

    public MainWeatherControllerStub(LocationService locationServiceStub) {
        this.locationServiceStub = locationServiceStub;
    }

    @Override
    @GetMapping("/")
    public String findLocationByName(@RequestParam(name = "city", required = false) String city, Model model) {
        List<LocationDto> cities = locationServiceStub.findLocationByName("Dublin");
        model.addAttribute("cities", cities);
        return "homePage";
    }

    @Override
    @GetMapping("/test")
    public String findLocationByCoordinates(double lat, double lon, Model model) {
        LocationDto location = locationServiceStub.findLocationByCoordinates(33.3, 55.5);
        model.addAttribute("location", location);
        return "homePage";
    }

    @Override
    public String deleteLocation(int id) {
        return "You deleted location: " + id;
    }


}
