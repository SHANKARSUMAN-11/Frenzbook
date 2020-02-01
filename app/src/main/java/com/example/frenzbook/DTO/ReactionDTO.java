package com.example.frenzbook.DTO;

public class ReactionDTO {

    private String userId;
    private String postId;
    private String activity;

    public String getUserId() {
        return userId;
    }

    public String getPostId() {
        return postId;
    }

    public String getActivity() {
        return activity;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
