package com.musicme.musicme.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class LikesIdentity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LikesIdentity that = (LikesIdentity) o;

        if (!user.equals(that.user) && !video.equals(that.video)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 * user.getEmail().hashCode() + video.getPathToVideo().hashCode();

        return result;
    }

}
