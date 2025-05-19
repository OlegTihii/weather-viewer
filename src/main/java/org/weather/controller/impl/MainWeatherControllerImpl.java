package org.weather.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.controller.MainWeatherController;
import org.weather.dto.LocationDto;
import org.weather.dto.WeatherDto;
import org.weather.entity.Session;
import org.weather.entity.User;
import org.weather.service.WeatherFacadeService;

import java.util.List;

@Controller
public class MainWeatherControllerImpl implements MainWeatherController {
    private static final Logger log = LoggerFactory.getLogger(MainWeatherControllerImpl.class);
    private final WeatherFacadeService weatherFacadeService;

    @Autowired
    public MainWeatherControllerImpl(WeatherFacadeService weatherFacadeService) {
        this.weatherFacadeService = weatherFacadeService;
    }

    @Override
    @GetMapping("/")
    //todo почему заходит 4 раза сюда?
    public String getPersonWeatherCards(User user, Session session, Model model) {
        List<WeatherDto> locationDto = weatherFacadeService.getWeatherForUserLocation(1);
        // как получили сущность из бд, то нужно сделать еще запрос на получение данных с сайта погоды
        model.addAttribute("location", locationDto);

        return "homePage";
    }


    @Override
    @GetMapping("/search")
    public String findLocation(User user,
                               @RequestParam(required = false) String city,
                               Model model) {

        if (city != null && !city.isEmpty() && !city.isBlank()) {
            List<LocationDto> cities = weatherFacadeService.getLocationsByCity(user.getId(), city);
            model.addAttribute("cities", cities);
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
