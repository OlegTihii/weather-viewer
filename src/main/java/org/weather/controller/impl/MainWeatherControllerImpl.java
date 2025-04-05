package org.weather.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.controller.MainWeatherController;
import org.weather.dto.LocationDto;
import org.weather.entity.Session;
import org.weather.entity.User;
import org.weather.service.LocationService;
import org.weather.service.UserService;
import org.weather.service.impl.LocationServiceImpl;
import org.weather.service.impl.UserServiceImpl;

import java.util.List;

@Controller
public class MainWeatherControllerImpl implements MainWeatherController {
    private final LocationService locationService;
    private final UserService userHomePageService;

    @Autowired
    public MainWeatherControllerImpl(LocationServiceImpl locationService, UserServiceImpl userService) {
        this.locationService = locationService;
        this.userHomePageService = userService;
    }

    @Override
    @GetMapping("/")
    public String getPersonWeatherCards(User user, Session session, Model model) {
        //у нас уже есть авторизация и нам нужно передать в сервис данные
        //авторизации и вернуть карточка погоды

      //  List<LocationDto> locationsForPerson = userHomePageService.findLocationsForPerson(user);
      //  model.addAttribute("cities", locationsForPerson);

        return "homePage";
    }


    //todo должен делать редирект или как лучше обработать?
    @Override
    @GetMapping("/search")
    public String findLocation(@RequestParam(name = "city", required = false) String city,
                               @RequestParam(name = "lat", required = false) Integer lat,
                               @RequestParam(name = "lon", required = false) Integer lon,
                               Model model) {
        if (city != null && !city.isEmpty()) {
            List<LocationDto> cities = locationService.findLocationByName(city);
            model.addAttribute("cities", cities);
        } else if (lat != null && lon != null) {
            LocationDto location = locationService.findLocationByCoordinates(lat, lon);
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
