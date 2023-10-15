package com.example.shop.controller;

import com.example.shop.entity.User;
import com.example.shop.entity.enumeration.Role;
import com.example.shop.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping(path = "/registration")
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registrationAction(
       Model model
    ){
        return "data/registration_form";
    }

    @PostMapping
    public String registrationUser(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "lastName") String lastName
    ){
        User user = new User();
        user.setRole(Role.USER);
        user.setDateRegistration(new Date());
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setLastname(lastName);
        userRepository.save(user);
        return "redirect:/login";
    }
}
