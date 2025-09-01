package org.weather.controller.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.controller.UserLoginController;
import org.weather.dto.SessionDto;
import org.weather.dto.UserLoginOrRegistrationDto;
import org.weather.service.SessionService;
import org.weather.service.UserService;
import org.weather.util.CookieConfig;

@Controller
@RequestMapping("/login")
@Slf4j
public class UserLoginControllerImpl implements UserLoginController {

    private UserService userService;
    private CookieConfig cookieConfig;
    private SessionService sessionService;

    @Autowired
    public UserLoginControllerImpl(@Qualifier("UserServiceImpl") UserService userService,
                                   CookieConfig cookieConfig,
                                   SessionService sessionService) {
        this.userService = userService;
        this.cookieConfig = cookieConfig;
        this.sessionService = sessionService;

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
                        HttpServletResponse response,
                        Model model) {

        UserLoginOrRegistrationDto userLoginOrRegistrationDto = UserLoginOrRegistrationDto.builder()
                .username(username)
                .password(password)
                .build();

        //todo возвращать юзерДТО с его сессией
        SessionDto sessionToken = userService.authorisation(userLoginOrRegistrationDto);
        log.info("login {}", sessionToken);
        cookieConfig.createCookie(response, sessionToken.getId().toString());

        log.info("finish");

      //  model.addAttribute("user", .getUserName());
        return "redirect:/";
    }
}


