package org.weather.controller;

import org.springframework.ui.Model;

public interface UserLoginController {

    String showLoginForm(Model model);

    String login(String username, String password, Model model);

}
