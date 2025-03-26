package org.weather.service.stub;

import org.weather.service.UserService;

public class UserServiceStub implements UserService {
    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean registration(String username, String password, String repeatPassword) {
        return false;
    }
}
