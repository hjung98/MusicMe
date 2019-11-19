package com.musicme.musicme.entities;

import javax.persistence.*;

@Entity
@Table(name = "videos")
public class Video {

    @EmbeddedId
    private VideoIdentity videoId;

    @Column(name="location")
    private String location;

    @Column(name="caption")
    private String caption;

    public Video(VideoIdentity videoId, String location, String caption) {
        this.videoId = videoId;
        this.location = location;
        this.caption = caption;
    }

    public Video() {
    }

    public VideoIdentity getVideoId() {
        return this.videoId;
    }

    public void setVideoId(VideoIdentity videoId) {
        this.videoId = videoId;
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

}
