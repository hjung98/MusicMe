package com.musicme.musicme.entities;

import javax.persistence.*;

@Entity
public class DirectMessage {

    @EmbeddedId
    private DirectMessageIdentity directMessageIdentity;

    // something to note here is that neither content nor video is set to not-null and that's because either can be null
    // but BOTH can't be null... we need to somehow enforce this idea
    @Column(name="content")
    private String content;

    // TODO join on composite videoId rather than path_to_video
    @OneToOne
    @JoinColumn(name="video", referencedColumnName="path_to_video", nullable=true)
    private Video video;

    public DirectMessage(DirectMessageIdentity directMessageIdentity, String content, Video video) {
        this.directMessageIdentity = directMessageIdentity;
        this.content = content;
        this.video = video;
    }

    public DirectMessage() {
    }

    public DirectMessageIdentity getDirectMessageIdentity() {
        return this.directMessageIdentity;
    }

    public void setDirectMessageIdentity(DirectMessageIdentity directMessageIdentity) {
        this.directMessageIdentity = directMessageIdentity;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

}
