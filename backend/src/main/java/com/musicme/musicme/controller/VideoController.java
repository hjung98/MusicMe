package com.musicme.musicme.controller;

import com.musicme.musicme.entities.User;
import com.musicme.musicme.entities.Video;
import com.musicme.musicme.entities.VideoIdentity;
import com.musicme.musicme.entities.Likes;
import com.musicme.musicme.entities.LikesIdentity;
import com.musicme.musicme.services.UserServiceImpl;
import com.musicme.musicme.services.VideoServiceImpl;
import com.musicme.musicme.services.LikesServiceImpl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Example;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VideoController {

    @Autowired
    VideoServiceImpl videoService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    LikesServiceImpl likesService;

    @GetMapping("/feed")
    public List<Video> getVideos() {
        return this.videoService.listAll();
    }

    @GetMapping("/user/video/saveorupdate")
    public Video saveOrUpdate(Video video) {
        return this.videoService.saveOrUpdate(video);
    }

    @GetMapping("/user/video/remove") 
    public Video delete(String timestamp, Long userId) {
        User user = this.userService.getById(userId);
        VideoIdentity videoIdentity = new VideoIdentity(user, timestamp);
        return this.videoService.delete(videoIdentity);
    }

    @PostMapping("/videos/{creatorId}/{timestamp}/like")
    public void likeVideo(@PathVariable Long creatorId, @PathVariable String timestamp, Long likerId) {
        // Grabbing video and user entities to insert into likes table
        String pathToVideo = "" + creatorId + "/" + timestamp + ".mov"; // need to make more generalized
        Video video = this.videoService.getByPathToVideo(pathToVideo);
        User user = this.userService.getById(likerId);

        
        LikesIdentity likesIdentity = new LikesIdentity(user, video);
        this.likesService.saveOrUpdate(new Likes(likesIdentity));
        video.setLikes(video.getLikes() + 1);
        this.videoService.saveOrUpdate(video);
    }

    @PostMapping("/videos/{creatorId}/{timestamp}/unlike")
    public void unlikeVideo(@PathVariable Long creatorId, @PathVariable String timestamp, Long likerId) {
        // Grabbing video and user entities to insert into likes table
        String pathToVideo = "" + creatorId + "/" + timestamp + ".mov"; // need to make more generalized
        Video video = this.videoService.getByPathToVideo(pathToVideo);
        User user = this.userService.getById(likerId);

        LikesIdentity likesIdentity = new LikesIdentity(user, video);
        this.likesService.delete(likesIdentity);
        video.setLikes(video.getLikes() - 1);
        this.videoService.saveOrUpdate(video);
    }

    @GetMapping("/videos/{creatorId}/{timestamp}/getlikes")
    public List<Likes> getLikes(@PathVariable Long creatorId, @PathVariable String timestamp) {
        String pathToVideo = "" + creatorId + "/" + timestamp + ".mov"; // need to make more generalized

        return this.likesService.getByVideo(pathToVideo);
    }

}
