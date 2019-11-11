package com.musicme.musicme.entities;

import javax.persistence.UniqueConstraint;

import javax.persistence.*;

@Entity
@Table(name = "videos", uniqueConstraints = {
    @UniqueConstraint(columnNames = "user_created")
})
public class Video {

    @Column(name="user_created")
    @OneToOne( targetEntity = User.class)
    private Long userCreated;

    @Column(name="timestamp")
    private String timestamp;

    @Column(name="location")
    private String location;

    @Column(name="caption")
    private String caption;

    public Video(Long userCreated, String timestamp, String location, String caption) {
        this.userCreated = userCreated;
        this.timestamp = timestamp;
        this.location = location;
        this.caption = caption;
    }

    public Video() {
    }

    public Long getUser() {
        return userCreated;
    }

    public void setUser(Long user) {
        this.userCreated = user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
