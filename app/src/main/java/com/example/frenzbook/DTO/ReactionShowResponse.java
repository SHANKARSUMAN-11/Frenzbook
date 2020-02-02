package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

public class ReactionShowResponse {

	@SerializedName("timeStamp")
	private String timeStamp;

	@SerializedName("activity")
	private String activity;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("userName")
	private String userName;

	public void setTimeStamp(String timeStamp){
		this.timeStamp = timeStamp;
	}

	public String getTimeStamp(){
		return timeStamp;
	}

	public void setActivity(String activity){
		this.activity = activity;
	}

	public String getActivity(){
		return activity;
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

	@Override
 	public String toString(){
		return 
			"ReactionShowResponse{" +
			"timeStamp = '" + timeStamp + '\'' + 
			",activity = '" + activity + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",userName = '" + userName + '\'' + 
			"}";
		}
}