package org.weather.service;

import org.weather.dto.LocationDto;
import org.weather.entity.User;

import java.util.List;

public interface UserHomePageService {

    List<LocationDto> findLocationsForPerson(User user);

    boolean login(String username, String password);

    boolean registration(String username, String password, String repeatPassword);

}
