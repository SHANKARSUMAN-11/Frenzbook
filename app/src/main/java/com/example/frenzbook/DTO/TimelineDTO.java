package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

public class TimelineDTO {

	@SerializedName("timeStamp")
	private String timeStamp;

	@SerializedName("postDTO")
	private PostDTO postDTO;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("postId")
	private String postId;

	@SerializedName("userName")
	private String userName;

	@SerializedName("message")
	private String message;

	public void setTimeStamp(String timeStamp){
		this.timeStamp = timeStamp;
	}

	public String getTimeStamp(){
		return timeStamp;
	}

	public void setPostDTO(PostDTO postDTO){
		this.postDTO = postDTO;
	}

	public PostDTO getPostDTO(){
		return postDTO;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setPostId(String postId){
		this.postId = postId;
	}

	public String getPostId(){
		return postId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"TimelineDTO{" +
			"timeStamp = '" + timeStamp + '\'' + 
			",postDTO = '" + postDTO + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",postId = '" + postId + '\'' + 
			",userName = '" + userName + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}