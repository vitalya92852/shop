package com.example.shop.controller;

import com.example.shop.entity.User;
import com.example.shop.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/security_controller")
public class SecurityController {

    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    // ВСЕ
    @GetMapping(path = "/first_resource")
    public String firstResource(){
        return "SecurityController.firstResource()";
    }

    // ВСЕ АВТОРИЗОВАННЫЕ
    @GetMapping(path = "/second_resource")
    public String secondResource(){
        return "SecurityController.secondResource()";
    }

    @GetMapping(path = "/third_resource")
    public String thirdResource(){
        return "SecurityController.secondResource()";
    }

    @GetMapping(path = "/current_user")
    public String currentUser(){
        User currentUser = userService.getCurrentUser();
        return currentUser.getLogin();
    }
}
