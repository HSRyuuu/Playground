package com.example.playground.spring.jpa.controller;

import com.example.playground.spring.jpa.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/test")
    public UserDto test(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return userDto;
    }
}
