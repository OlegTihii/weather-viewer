package org.weather.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.controller.UserLoginController;
import org.weather.dto.UserLoginOrRegistrationDto;
import org.weather.service.UserService;

@Controller
@RequestMapping("/login")
public class UserLoginControllerImpl implements UserLoginController {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginControllerImpl.class);
    private UserService userService;

    @Autowired
    public UserLoginControllerImpl(@Qualifier("UserServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public String showLoginForm(Model model) {
        return "sign-in";
    }

    @Override
    @PostMapping
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        UserLoginOrRegistrationDto userLoginOrRegistrationDto = UserLoginOrRegistrationDto.builder()
                .username(username)
                .password(password)
                .build();
    try {
        userService.checkLogin(userLoginOrRegistrationDto);
    } catch (UsernameNotFoundException ex){
        return "sign-in-with-errors";
    }


        return "homePage";
    }
}

