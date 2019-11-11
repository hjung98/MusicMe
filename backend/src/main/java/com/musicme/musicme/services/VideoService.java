package com.musicme.musicme.services;

import com.musicme.musicme.entities.Video;

import java.util.List;

public interface VideoService {

    List<Video> listAll();

    List<Video> getByUser(Long user_id);

    Video saveOrUpdate(Video video);

    void delete(Long user_id, String timestamp);

}
