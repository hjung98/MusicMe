package com.musicme.musicme.controller;

import com.musicme.musicme.entities.User;
import com.musicme.musicme.entities.Video;
import com.musicme.musicme.security.CurrentUser;
import com.musicme.musicme.security.UserPrincipal;
import com.musicme.musicme.services.UserServiceImpl;
import com.musicme.musicme.services.VideoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    VideoServiceImpl videoService;

    @RequestMapping("/")
    public String getUsers() {
        return "works";
    }

    @RequestMapping("/user/saveupdate")
    public User saveOrUpdate(   User user) {
        return this.userService.saveOrUpdate(user);
    }

    @RequestMapping("/user/delete")
    public User delete(Long id) {
       User user = this.userService.getById(id);
       this.userService.delete(id);
       return user;
    }

    @RequestMapping("/user/listall")
    public List<User> getAll() {
        return this.userService.listAll();
    }

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return this.userService.getCurrentUser(userPrincipal);
    }

}
