package com.musicme.musicme.controller;

import com.musicme.musicme.entities.User;
import com.musicme.musicme.entities.Video;
import com.musicme.musicme.entities.DirectMessage;
import com.musicme.musicme.entities.DirectMessageIdentity;
import com.musicme.musicme.services.UserServiceImpl;
import com.musicme.musicme.services.DirectMessageServiceImpl;
import com.musicme.musicme.services.VideoServiceImpl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

@RestController
public class DirectMessageController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    VideoServiceImpl videoService;

    @Autowired
    DirectMessageServiceImpl directMessageService;

    @GetMapping("/user/dm")
    public List<DirectMessage> getDirectMessagessForUser(Long id1, Long id2) {
        return this.directMessageService.getByUsersMatching(id1, id2);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    @MessageMapping("/newMessage")
    @SendTo("/topic/newMessage")
    public DirectMessage save(Long id1, Long id2, String content, String pathToVideo) {
        User user1 = this.userService.getById(id1);
        User user2 = this.userService.getById(id2);
        DirectMessageIdentity directMessageIdentity = new DirectMessageIdentity(user1, user2, new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()));

        Video video = this.videoService.getByPathToVideo(pathToVideo);
        DirectMessage directMessage = new DirectMessage(directMessageIdentity, content, video);
        return this.directMessageService.save(directMessage);
    }

    @GetMapping("/messages")
    public List getMessages(Long id1, Long id2) {
        return this.directMessageService.getByUsersMatching(id1, id2);
    }
    
    // Original
    @PostMapping("/user/dm/send")
    public DirectMessage saveOrUpdate(Long id1, Long id2, String content, String pathToVideo) {
        User user1 = this.userService.getById(id1);
        User user2 = this.userService.getById(id2);
        DirectMessageIdentity directMessageIdentity = new DirectMessageIdentity(user1, user2, new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()));

        Video video = this.videoService.getByPathToVideo(pathToVideo);
        DirectMessage directMessage = new DirectMessage(directMessageIdentity, content, video);
        return this.directMessageService.save(directMessage);
    }

    @RequestMapping("/user/dm/remove")
    public DirectMessage delete(String timestamp, Long id1, Long id2) {
        User user1 = this.userService.getById(id1);
        User user2 = this.userService.getById(id2);
        DirectMessageIdentity directMessageIdentity = new DirectMessageIdentity(user1, user2, timestamp);
        return this.directMessageService.delete(directMessageIdentity);
    }

}