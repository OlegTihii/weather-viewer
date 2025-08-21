package org.weather.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

public interface UserRegistrationController {

    String showRegistrationForm(Model model);

    String registration(String username, String password, String confirmPassword, Model model, HttpServletResponse response);

}
