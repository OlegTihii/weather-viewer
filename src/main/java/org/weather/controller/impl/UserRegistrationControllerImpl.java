package org.weather.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.controller.UserRegistrationController;
import org.weather.dto.UserDto;
import org.weather.dto.UserRegistrationDto;
import org.weather.service.UserService;


@Controller
@RequestMapping("/registration")
public class UserRegistrationControllerImpl implements UserRegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationControllerImpl.class);
    private UserService userService;

    @Autowired
    public UserRegistrationControllerImpl(@Qualifier("UserServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public String showRegistrationForm(Model model) {
        return "sign-up";
    }

    //todo личные данные видны в запросе
    @Override
    @PostMapping
    public String registration(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmPassword
    ) {

        if (!password.equals(confirmPassword)) {
            return "sign-up";
        }
        UserRegistrationDto userRegistrationDto = UserRegistrationDto.builder()
                .username(username)
                .password(password)
                .build();

        userService.registration(userRegistrationDto);

        return "homePage";
    }
}
