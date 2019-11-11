package com.musicme.musicme.controller;

import com.musicme.musicme.entities.Video;
import com.musicme.musicme.services.VideoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class VideoController {

    @Autowired
    VideoServiceImpl videoService;

    @RequestMapping("/videos")
    public List<Video> getVideos() {
        return this.videoService.listAll();
    }

    @RequestMapping("/videos/saveupdate")
    public Video saveOrUpdate(Video video) {
        return this.videoService.saveOrUpdate(video);
    }

}