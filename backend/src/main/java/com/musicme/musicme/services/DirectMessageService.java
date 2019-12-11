package com.musicme.musicme.services;

import com.musicme.musicme.entities.DirectMessage;
import com.musicme.musicme.entities.DirectMessageIdentity;

import java.util.List;

public interface DirectMessageService {

    // haven't included a listAll() bc there's no need for it
    
    List<DirectMessage> getByUsersMatching(Long user1Id, Long user2Id);

    DirectMessage save(DirectMessage directMessage);

    DirectMessage delete(DirectMessageIdentity directMessageIdentity);

}