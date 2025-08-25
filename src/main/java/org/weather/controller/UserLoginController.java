package org.weather.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

public interface UserLoginController {

    String showLoginForm(Model model);

    String login(String username, String password, HttpServletResponse response, Model model);

}
