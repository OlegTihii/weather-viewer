package org.weather.service.stub;

import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.entity.User;
import org.weather.service.UserHomePageService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceStub implements UserHomePageService {
    //todo где лучше объявлять list? тут или в методе?
    List<LocationDto> locations = new ArrayList<>();

    @Override
    public List<LocationDto> findLocationsForPerson(User user) {
        //тут происходит вызов репозитория и поиск данных
        return locations = List.of(LocationDto.builder().city("Zalupansk").country("RU").humidity(67).temperature(10).build(),
                LocationDto.builder().city("Moscow").country("RU").humidity(22).temperature(17).build(),
                LocationDto.builder().city("Old Vasyuki").country("RU").humidity(43).temperature(28).build());
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean registration(String username, String password, String repeatPassword) {
        return false;
    }

}
