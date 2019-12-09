package com.musicme.musicme.controller;

import com.musicme.musicme.entities.Video;
import com.musicme.musicme.services.VideoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class VideoController {

    @Autowired
    VideoServiceImpl videoService;

    // API for displaying all videos for the Feed
    @GetMapping("/feed")
    public List<Video> getVideos() {
        return this.videoService.listAll();
    }

}
