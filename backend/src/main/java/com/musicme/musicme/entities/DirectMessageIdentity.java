package com.musicme.musicme.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class DirectMessageIdentity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id_1", insertable = false, updatable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user_id_2", insertable = false, updatable = false)
    private User user2;

    @NotNull
    @Column(name = "timestamp")
    private String timestamp;

    public DirectMessageIdentity(User user1, User user2, String timestamp) {
        this.user1 = user1;
        this.user2 = user2;
        this.timestamp = timestamp;
    }

    public DirectMessageIdentity() {
    }

    public User getUser1() {
        return this.user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return this.user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
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

        DirectMessageIdentity that = (DirectMessageIdentity) o;

        if (!user1.equals(that.user1) && !user2.equals(that.user2)) return false;

        return timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = user1.getId().hashCode() + user2.getId().hashCode();
        result = 31 * result + timestamp.hashCode();

        return result;
    }

}
