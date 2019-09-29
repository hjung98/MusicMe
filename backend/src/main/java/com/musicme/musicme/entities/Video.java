package com.musicme.musicme.entities;

public class Video {

    private Long id;
    private String timestamp;
    private String location;
    private String caption;
    private Long likes;
    private Long shares;

    public Video(Long id, String timestamp, String location, String caption, Long likes, Long shares) {
        this.id = id;
        this.timestamp = timestamp;
        this.location = location;
        this.caption = caption;
        this.likes = likes;
        this.shares = shares;
    }

    public Video() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getShares() {
        return shares;
    }

    public void setShares(Long shares) {
        this.shares = shares;
    }
}
