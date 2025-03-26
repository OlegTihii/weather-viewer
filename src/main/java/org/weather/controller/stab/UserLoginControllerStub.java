package org.weather.controller.stab;

import org.weather.controller.UserLoginController;

public class UserLoginControllerStub implements UserLoginController {

    public String login(String username, String password){
        return "You have entered the following fields: \n"
                + "username: " + username + "\n"
                + "password: " + password;
    }
}

