package com.musicme.musicme.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class VideoIdentity implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "user_email", insertable = false, updatable = false)
    private User user;

    @NotNull
    @Column(name = "timestamp")
    private String timestamp;

    public VideoIdentity(User user, String timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    public VideoIdentity() {
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoIdentity that = (VideoIdentity) o;

        if (!user.equals(that.user)) return false;

        return timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = user.getEmail().hashCode();
        result = 31 * result + timestamp.hashCode();

        return result;
    }

}
