package ca.sheridancollege.suranim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ca.sheridancollege.suranim.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String login() {
        return "login"; // Points to login.html (Thymeleaf)
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register"; // Points to register.html (Thymeleaf)
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        service.register(username, password);
        return "redirect:/login";
    }
}
