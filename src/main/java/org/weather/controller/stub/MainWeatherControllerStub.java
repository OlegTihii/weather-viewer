package org.weather.controller.stub;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.controller.MainWeatherController;
import org.weather.entity.Session;
import org.weather.entity.User;
import org.weather.service.LocationService;
import org.weather.service.UserService;
import org.weather.service.stub.UserServiceStub;

@Controller
@RequestMapping("/stub")
public class MainWeatherControllerStub implements MainWeatherController {
    private final LocationService locationServiceStub;
    private final UserService userServiceStub;

    public MainWeatherControllerStub(@Qualifier("locationServiceStub") LocationService locationServiceStub, UserServiceStub userServiceStub) {
        this.locationServiceStub = locationServiceStub;
        this.userServiceStub = userServiceStub;
    }

    @Override
    public String findLocation(HttpServletRequest request, String city, Model model) {
        return "";
    }

    @Override
    @GetMapping("/")
    public String getPersonWeatherCards(User user, Session session, Model model) {
        //у нас уже есть авторизация и нам нужно передать в сервис данные
        //авторизации и вернуть карточка погоды

        // List<LocationDto> locationsForPerson = userServiceStub.findLocationsForPerson(user);
        //  model.addAttribute("cities", locationsForPerson);

        return "homePage";
    }


    //todo должен делать редирект или как лучше обработать?

    @GetMapping("/search")
    public String findLocation(User user,
                               @RequestParam(required = false) String city,
                               Model model) {
//        if (city != null && !city.isEmpty()) {
//            List<WeatherDto> cities = locationServiceStub.listLocationsByUserId(66);
//            model.addAttribute("cities", cities);
//        }
//        else if (lat != null && lon != null) {
//            WeatherDto location = locationServiceStub.findLocationByCoordinates(lat, lon);
//            model.addAttribute("location", location);
//         }
//        else {
//            model.addAttribute("error", "please enter the correct name or coordinates");
//        }

        return "searchPage";
    }


    @Override
    public String deleteLocation(int id) {
        return "You deleted location: " + id;
    }


}
