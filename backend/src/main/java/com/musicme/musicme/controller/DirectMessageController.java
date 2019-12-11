package com.musicme.musicme.controller;

import com.musicme.musicme.entities.User;
import com.musicme.musicme.entities.DirectMessage;
import com.musicme.musicme.entities.DirectMessageIdentity;
import com.musicme.musicme.services.UserServiceImpl;
import com.musicme.musicme.services.DirectMessageServiceImpl;
import com.musicme.musicme.services.VideoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import java.util.Date;
import java.util.HashSet;
import java.text.SimpleDateFormat;

@RestController
public class DirectMessageController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    VideoServiceImpl videoService;

    @Autowired
    DirectMessageServiceImpl directMessageService;

    @GetMapping("/messages")
    public List<User> getAllChats(Long id) {        
        List<DirectMessage> dms = this.directMessageService.getByUsersMatching(id, null);

        Set<Long> uniqueIds = new HashSet<Long>(10);
        Stack<User> chatWithUser = new Stack<>();
        uniqueIds.add(id);
        // System.out.println("\n\n\n\n\n\n");
        // uniqueIds.forEach(System.out::println);
        // System.out.println("\n\n\n\n\n\n");

        for (int i = 0; i < dms.size(); ++i) {
            if ((!uniqueIds.contains(dms.get(i).getUser1().getId())) || (!uniqueIds.contains(dms.get(i).getUser2().getId()))) {
                User toAdd;
                if (dms.get(i).getUser1().getId() == id) {
                    toAdd = dms.get(i).getUser2();
                } else if (dms.get(i).getUser1().getId() != id) {
                    toAdd = dms.get(i).getUser1();
                } else if (dms.get(i).getUser1().getId() != id) {
                    toAdd = dms.get(i).getUser1();
                } else {
                    toAdd = dms.get(i).getUser2();
                }
                // System.out.println(toAdd.getId());
                uniqueIds.add(toAdd.getId());
                chatWithUser.push(toAdd);
            }
        }

        return new ArrayList<User>(chatWithUser);
    }

    @GetMapping("/messages/{id}")
    public List<DirectMessage> getMessages(Long id1, @PathVariable Long id2) {
        return this.directMessageService.getByUsersMatching(id1, id2);
    }    

    @PostMapping("/user/dm/send")
    public DirectMessage saveOrUpdate(Long id1, Long id2, String content) {
        User user1 = this.userService.getById(id1);
        User user2 = this.userService.getById(id2);
        DirectMessageIdentity directMessageIdentity = new DirectMessageIdentity(id1, id2, new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()));

        DirectMessage directMessage = new DirectMessage(directMessageIdentity, user1, user2, content);
        return this.directMessageService.save(directMessage);
    }

    @RequestMapping("/user/dm/remove")
    public DirectMessage delete(String timestamp, Long id1, Long id2) {
        DirectMessageIdentity directMessageIdentity = new DirectMessageIdentity(id1, id2, timestamp);
        return this.directMessageService.delete(directMessageIdentity);
    }

}