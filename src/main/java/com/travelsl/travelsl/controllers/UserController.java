package com.travelsl.travelsl.controllers;

import com.travelsl.travelsl.dtos.UserDto;
import com.travelsl.travelsl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController implements Controller {

    @Autowired
    private UserService userService;
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok().body(userService.saveNewUser(userDto));
    }
}
