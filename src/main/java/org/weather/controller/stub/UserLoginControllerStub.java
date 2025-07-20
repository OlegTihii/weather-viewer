package org.weather.controller.stub;

import org.springframework.ui.Model;
import org.weather.controller.UserLoginController;

public class UserLoginControllerStub implements UserLoginController {

    @Override
    public String login(String username, String password, Model model) {
        return "You have entered the following fields: \n"
                + "username: " + username + "\n"
                + "password: " + password;
    }

    @Override
    public String showLoginForm(Model model) {
        return "";
    }
}

