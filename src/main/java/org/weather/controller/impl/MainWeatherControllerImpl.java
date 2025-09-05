package org.weather.controller.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.controller.MainWeatherController;
import org.weather.dto.LocationDto;
import org.weather.dto.UserLocationsWeatherDto;
import org.weather.service.WeatherFacadeService;
import org.weather.util.ExtractCookieUtil;

import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class MainWeatherControllerImpl implements MainWeatherController {
    private final WeatherFacadeService weatherFacadeService;

    @Autowired
    public MainWeatherControllerImpl(WeatherFacadeService weatherFacadeService) {
        this.weatherFacadeService = weatherFacadeService;
    }

    @Override
    @GetMapping("/")
    //todo почему заходит 4 раза сюда?
    public String getPersonWeatherCards(HttpServletRequest request, Model model) {
        String cookie = ExtractCookieUtil.extractCookie(request)
                .orElseThrow(() -> new IllegalStateException("Куки не найдена"));

        UserLocationsWeatherDto userLocationsWeatherDto = weatherFacadeService.getUserLocationsWeather(cookie);

        model.addAttribute("user", userLocationsWeatherDto);
        model.addAttribute("userWeather", userLocationsWeatherDto);

        return "homePage";
    }

    @Override
    @GetMapping("/search")
    public String findLocation(HttpServletRequest request,
                               @RequestParam(required = false) String city,
                               Model model) {

        log.info("findLocation start");

        String userCookies = ExtractCookieUtil.extractCookie(request)
                .orElseThrow(() -> new IllegalStateException("Куки не найдена"));

        if (city != null && !city.isBlank()) {
            List<LocationDto> cities = weatherFacadeService.getLocationsByCity(userCookies, city);
            model.addAttribute("cities", cities);
        } else {
            model.addAttribute("error", "please enter the correct name or coordinates");
        }

        log.info("findLocation finish");
        return "searchPage";
    }


    @Override
    public String deleteLocation(int id) {
        return "You deleted location: " + id;
    }

}
