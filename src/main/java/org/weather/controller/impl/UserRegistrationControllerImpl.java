package org.weather.controller.impl;

import org.weather.controller.UserRegistrationController;

public class UserRegistrationControllerImpl implements UserRegistrationController {
    @Override
    public String registration(String username, String password, String repeatPassword) {
        return "You have entered the following fields: \n"
                + "username: " + username + "\n"
                + "password: " + password + "\n"
                + "repeatPassword: " + repeatPassword;
    }
}
