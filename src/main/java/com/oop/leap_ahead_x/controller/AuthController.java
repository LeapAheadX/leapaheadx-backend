package com.oop.leap_ahead_x.controller;


import com.oop.leap_ahead_x.service.AuthService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/{email}/{password}")
    @ApiResponse(responseCode = "201")
    public List<UUID> login(@PathVariable(name = "email") final String email, @PathVariable(name = "password") final String password) {
        return authService.login(email, password);
    }

}
