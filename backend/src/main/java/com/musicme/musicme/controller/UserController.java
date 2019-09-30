//package com.musicme.musicme.controller;
//
//
//import com.musicme.musicme.entities.User;
//import com.musicme.musicme.mappers.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    public UserController(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//
//    @GetMapping("/all")
//    public List<User> getAllUsers() {
//        return userMapper.findAll();
//    }
//}
