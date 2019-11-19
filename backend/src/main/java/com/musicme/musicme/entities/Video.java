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

    public Video(VideoIdentity videoIdentity, String location, String caption) {
        this.videoIdentity = videoIdentity;
        this.location = location;
        this.caption = caption;
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

}
