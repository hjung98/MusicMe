package com.musicme.musicme.entities;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {

    @EmbeddedId
    private LikesIdentity likesIdentity;


    public Likes(LikesIdentity likesIdentity) {
        this.likesIdentity = likesIdentity;
    }

    public Likes() {
    }

    public LikesIdentity getLikesIdentity() {
        return this.likesIdentity;
    }

    public void setLikesIdentity(LikesIdentity likesIdentity) {
        this.likesIdentity = likesIdentity;
    }

}
