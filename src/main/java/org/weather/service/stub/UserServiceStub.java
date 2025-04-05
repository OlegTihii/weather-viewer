package org.weather.service.stub;

import org.springframework.stereotype.Service;
import org.weather.dto.LocationDto;
import org.weather.dto.UserDto;
import org.weather.entity.User;
import org.weather.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceStub implements UserService {
    //todo где лучше объявлять list? тут или в методе?
    List<LocationDto> locations = new ArrayList<>();


//    public List<LocationDto> findLocationsForPerson(User user) {
//        //тут происходит вызов репозитория и поиск данных
//        return locations = List.of(LocationDto.builder().city("Zalupansk").country("RU").humidity(67).temperature(10).build(),
//                LocationDto.builder().city("Moscow").country("RU").humidity(22).temperature(17).build(),
//                LocationDto.builder().city("Old Vasyuki").country("RU").humidity(43).temperature(28).build());
//    }

    @Override
    public UserDto login(String username, String password) {
        return null;
    }

    @Override
    public UserDto registration(String username, String password, String repeatPassword) {
        return null;
    }

}
