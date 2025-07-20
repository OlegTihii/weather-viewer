package org.weather.controller.stub;

import org.springframework.ui.Model;
import org.weather.controller.UserRegistrationController;

public class UserRegistrationControllerStub implements UserRegistrationController {


    @Override
    public String showRegistrationForm(Model model) {
        return "";
    }

    @Override
    public String registration(String username, String password, String confirmPassword, Model model) {
        return "";
    }

}
