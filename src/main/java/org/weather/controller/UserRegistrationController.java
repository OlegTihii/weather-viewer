package org.weather.controller;

import org.springframework.ui.Model;

public interface UserRegistrationController {

    String showRegistrationForm(Model model);

    String registration(String username, String password, String confirmPassword, Model model);

}
