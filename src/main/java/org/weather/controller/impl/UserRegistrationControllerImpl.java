package org.weather.controller.impl;

import jakarta.servlet.http.HttpServletResponse;
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
import org.weather.dto.SessionDto;
import org.weather.dto.UserLoginOrRegistrationDto;
import org.weather.service.UserService;
import org.weather.util.CookieConfig;


@Controller
@RequestMapping("/registration")
public class UserRegistrationControllerImpl implements UserRegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationControllerImpl.class);
    private UserService userService;
    private CookieConfig cookieConfig;

    @Autowired
    public UserRegistrationControllerImpl(@Qualifier("UserServiceImpl") UserService userService, CookieConfig cookieConfig) {
        this.userService = userService;
        this.cookieConfig = cookieConfig;
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
                               @RequestParam String confirmPassword,
                               Model model,
                               HttpServletResponse response
    ) {

        //todo обработать ошибку что пароли должны быть одинаковые
        if (!password.equals(confirmPassword)) {
            return "sign-up";
        }
        UserLoginOrRegistrationDto userLoginOrRegistrationDto = UserLoginOrRegistrationDto.builder()
                .username(username)
                .password(password)
                .build();

        //todo сделано супер коряво! Я хотел возвращать UserDto и из него доставать сессию, но почему то хибер не хочет записывать сессию в юзера
        SessionDto sessionToken = userService.registration(userLoginOrRegistrationDto);

        cookieConfig.createCookie(response, sessionToken.getId().toString());

        return "redirect:/";
    }
}
