package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.entity.User;
import com.finance.finance_dashboard.service.AuthService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> request){

        return authService.login(
                request.get("email"),
                request.get("password")
        );
    }
}