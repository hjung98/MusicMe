package com.musicme.musicme.services;

import com.musicme.musicme.entities.Video;
import com.musicme.musicme.entities.VideoIdentity;

import java.util.List;

public interface VideoService {

    List<Video> listAll();

    List<Video> getByUser(Long userId);

    Video saveOrUpdate(Video video);

    void delete(VideoIdentity videoId);

}
