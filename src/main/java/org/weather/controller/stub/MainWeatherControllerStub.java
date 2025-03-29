package org.weather.controller.stub;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.controller.MainWeatherController;
import org.weather.dto.LocationDto;
import org.weather.entity.Session;
import org.weather.entity.User;
import org.weather.service.LocationService;

import java.util.List;

@Controller
public class MainWeatherControllerStub implements MainWeatherController {
    private final LocationService locationServiceStub;

    public MainWeatherControllerStub(LocationService locationServiceStub) {
        this.locationServiceStub = locationServiceStub;
    }

    @Override
    public String getPersonWeatherCards(User user, Session session) {
        return null;
    }

    @Override
    @GetMapping("/")
    public String findLocation(@RequestParam(name = "city", required = false) String city,
                               @RequestParam(name = "lat", required = false) Integer lat,
                               @RequestParam(name = "lon", required = false) Integer lon,
                               Model model) {
        if (city != null) {
            List<LocationDto> cities = locationServiceStub.findLocationByName("Dublin");
            model.addAttribute("cities", cities);
        } else if (lat != null && lon != null) {
            LocationDto location = locationServiceStub.findLocationByCoordinates(lat, lon);
            model.addAttribute("location", location);
        } else {
            model.addAttribute("error", "please enter the correct name or coordinates");
        }

        return "searchPage";
    }


    @Override
    public String deleteLocation(int id) {
        return "You deleted location: " + id;
    }


}
