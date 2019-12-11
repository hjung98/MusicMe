package com.musicme.musicme.services;

import com.musicme.musicme.entities.Likes;
import com.musicme.musicme.entities.LikesIdentity;

import java.util.List;

public interface LikesService {

    List<Likes> listAll();

    List<Likes> getByVideo(String pathToVideo);

    List<Likes> getByUser(Long id);

    Likes saveOrUpdate(Likes likes);

    void delete(LikesIdentity likesIdentity);

}
