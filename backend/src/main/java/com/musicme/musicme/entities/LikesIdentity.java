package com.musicme.musicme.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class LikesIdentity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "video", referencedColumnName = "path_to_video", nullable = true)
    private Video video;

    public LikesIdentity(User user, Video video) {
        this.user = user;
        this.video = video;
    }

    public LikesIdentity() {
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

}
