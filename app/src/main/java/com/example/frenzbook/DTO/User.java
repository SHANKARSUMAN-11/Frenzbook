package com.example.frenzbook.DTO;

import java.util.HashSet;
import java.util.List;

public class User {

    private String userId;
    private String userName;
    private String imageUrl;
    private String gender;
    private String email;
    private String DOB;
    private Long mobileNumber;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public void setFriendIds(HashSet<String> friendIds) {
        this.friendIds = friendIds;
    }

    public void setPendingFriendIds(HashSet<String> pendingFriendIds) {
        this.pendingFriendIds = pendingFriendIds;
    }

    private List<String> interests;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getDOB() {
        return DOB;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public List<String> getInterests() {
        return interests;
    }

    public String getProfileType() {
        return profileType;
    }

    public String getDisplayType() {
        return displayType;
    }

    public HashSet<String> getFriendIds() {
        return friendIds;
    }

    public HashSet<String> getPendingFriendIds() {
        return pendingFriendIds;
    }

    private String profileType;
    private String displayType;
    private HashSet<String> friendIds;
    private HashSet<String> pendingFriendIds;
}
