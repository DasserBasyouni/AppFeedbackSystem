package com.tech.futureteric.feedbacklibrary.model;

import com.google.firebase.firestore.Exclude;

public class Forum {

    private String title, byUid, description;
    private int voteCount;

    public Forum(String byUid, String title, String description) {
        this.byUid = byUid;
        this.title = title;
        this.description = description;
    }

    public Forum(String title, String byUid, String description, int voteCount) {
        this.title = title;
        this.byUid = byUid;
        this.description = description;
        this.voteCount = voteCount;
    }

    public String getByUid() {
        return byUid;
    }

    public void setByUid(String byUid) {
        this.byUid = byUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Exclude
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
