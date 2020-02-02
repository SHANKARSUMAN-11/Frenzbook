package com.example.frenzbook.DTO;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FriendsDTO {

	@SerializedName("displayType")
	private String displayType;

	@SerializedName("gender")
	private String gender;

	@SerializedName("profileType")
	private String profileType;

	@SerializedName("friendIds")
	private List<String> friendIds;

	@SerializedName("mobileNumber")
	private long mobileNumber;

	@SerializedName("dob")
	private Object dob;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("userName")
	private String userName;

	@SerializedName("interests")
	private List<String> interests;

	@SerializedName("userId")
	private String userId;

	@SerializedName("email")
	private String email;

	@SerializedName("pendingFriendIds")
	private List<String> pendingFriendIds;

	public void setDisplayType(String displayType){
		this.displayType = displayType;
	}

	public String getDisplayType(){
		return displayType;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setProfileType(String profileType){
		this.profileType = profileType;
	}

	public String getProfileType(){
		return profileType;
	}

	public void setFriendIds(List<String> friendIds){
		this.friendIds = friendIds;
	}

	public List<String> getFriendIds(){
		return friendIds;
	}

	public void setMobileNumber(long mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public long getMobileNumber(){
		return mobileNumber;
	}

	public void setDob(Object dob){
		this.dob = dob;
	}

	public Object getDob(){
		return dob;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setInterests(List<String> interests){
		this.interests = interests;
	}

	public List<String> getInterests(){
		return interests;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setPendingFriendIds(List<String> pendingFriendIds){
		this.pendingFriendIds = pendingFriendIds;
	}

	public List<String> getPendingFriendIds(){
		return pendingFriendIds;
	}

	@Override
 	public String toString(){
		return 
			"FriendsDTO{" +
			"displayType = '" + displayType + '\'' + 
			",gender = '" + gender + '\'' + 
			",profileType = '" + profileType + '\'' + 
			",friendIds = '" + friendIds + '\'' + 
			",mobileNumber = '" + mobileNumber + '\'' + 
			",dob = '" + dob + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",userName = '" + userName + '\'' + 
			",interests = '" + interests + '\'' + 
			",userId = '" + userId + '\'' + 
			",email = '" + email + '\'' + 
			",pendingFriendIds = '" + pendingFriendIds + '\'' + 
			"}";
		}
}