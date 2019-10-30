package com.musicme.musicme.controller;

import com.musicme.musicme.entities.User;
import com.musicme.musicme.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/")
    public String getUsers() {
        return "works";
    }
    
}
