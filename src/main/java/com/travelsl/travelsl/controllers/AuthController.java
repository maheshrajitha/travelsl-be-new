package com.travelsl.travelsl.controllers;

import com.travelsl.travelsl.dtos.UserDto;
import com.travelsl.travelsl.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController implements Controller{

    @Autowired
    private AuthService authService;
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UserDto userDto , HttpServletResponse httpServletResponse){
        return ResponseEntity.ok(authService.login(userDto , httpServletResponse));
    }
}
