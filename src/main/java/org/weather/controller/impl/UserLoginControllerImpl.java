package org.weather.controller.impl;

import org.weather.controller.UserLoginController;

public class UserLoginControllerImpl implements UserLoginController {

    public String login(String username, String password){
        return "You have entered the following fields: \n"
                + "username: " + username + "\n"
                + "password: " + password;
    }
}

