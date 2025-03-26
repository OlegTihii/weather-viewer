package org.weather.controller.stub;

import org.weather.controller.UserRegistrationController;

public class UserRegistrationControllerStub implements UserRegistrationController {
    @Override
    public String registration(String username, String password, String repeatPassword) {
        return "You have entered the following fields: \n"
                + "username: " + username + "\n"
                + "password: " + password + "\n"
                + "repeatPassword: " + repeatPassword;
    }
}
