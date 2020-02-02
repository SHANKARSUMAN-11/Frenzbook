package com.example.frenzbook.DTO;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
public class SecondSignUpDTO implements Serializable {
    @SerializedName("userId")
    private String userId;
    private String userName;
    private String imageUrl;
    private String gender;
    private String email;
    private String DOB;
    private Long mobileNumber;
    private List<String> interests;
    private String profileType;
    private String displayType;
    private HashSet<String> friendIds;
    private HashSet<String> pendingFriendIds;
    public SecondSignUpDTO() {
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDOB() {
        return DOB;
    }
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
    public Long getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public List<String> getInterests() {
        return interests;
    }
    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
    public String getProfileType() {
        return profileType;
    }
    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }
    public String getDisplayType() {
        return displayType;
    }
    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }
    public HashSet<String> getFriendIds() {
        return friendIds;
    }
    public void setFriendIds(HashSet<String> friendIds) {
        this.friendIds = friendIds;
    }
    public HashSet<String> getPendingFriendIds() {
        return pendingFriendIds;
    }
    public void setPendingFriendIds(HashSet<String> pendingFriendIds) {
        this.pendingFriendIds = pendingFriendIds;
    }
}