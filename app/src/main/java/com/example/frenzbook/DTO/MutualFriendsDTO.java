package com.example.frenzbook.DTO;

import java.util.List;

public class MutualFriendsDTO {

    private Boolean isFriend;
    private List<User> user;

    public void setFriend(Boolean friend) {
        isFriend = friend;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Boolean getFriend() {
        return isFriend;
    }

    public List<User> getUser() {
        return user;
    }
}
