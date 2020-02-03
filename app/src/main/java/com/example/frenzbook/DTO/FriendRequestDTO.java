package com.example.frenzbook.DTO;

public class FriendRequestDTO {

    String userId;
    String friendId;

    public String getUserId() {
        return userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
