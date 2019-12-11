package com.musicme.musicme.entities;

import javax.persistence.*;

@Entity
@Table(name = "videos")
public class Video {

    @EmbeddedId
    private VideoIdentity videoIdentity;

    @Column(name="location")
    private String location;

    @Column(name="caption")
    private String caption;

    @Column(name="path_to_video")
    private String pathToVideo;

    @Column(name="num_likes")
    private int likes;

    public Video(VideoIdentity videoIdentity, String location, String caption, String pathToVideo, int likes) {
        this.videoIdentity = videoIdentity;
        this.location = location;
        this.caption = caption;
        this.pathToVideo = pathToVideo;
        this.likes = likes;
    }

    public Video() {
    }

    public VideoIdentity getVideoIdentity() {
        return this.videoIdentity;
    }

    public void setVideoIdentity(VideoIdentity videoIdentity) {
        this.videoIdentity = videoIdentity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPathToVideo() {
        return this.pathToVideo;
    }

    public void setPathToVideo(String pathToVideo) {
        this.pathToVideo = pathToVideo;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
