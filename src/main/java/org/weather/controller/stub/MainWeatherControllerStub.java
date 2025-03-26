package org.weather.controller.stub;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.weather.controller.MainWeatherController;
import org.weather.dto.LocationDto;
import org.weather.service.LocationService;
import org.weather.service.stub.LocationServiceStub;

import java.util.List;

@Controller
public class MainWeatherControllerStub implements MainWeatherController {

    private final LocationService locationServiceStub = new LocationServiceStub();

    @Override
    public String findLocationByName(String city, Model model) {
        List<LocationDto> cities = locationServiceStub.findLocationByName("Dublin");
        model.addAttribute(cities);
        return "homePage";
    }

    @Override
    public String findLocationByCoordinates(double lat, double lon, Model model) {
        LocationDto locationByCoordinates = locationServiceStub.findLocationByCoordinates(33.3, 55.5);
        model.addAttribute(locationByCoordinates);
        return "homePage";
    }

    @Override
    public String deleteLocation(int id) {
        return "You deleted location: " + id;
    }


}
