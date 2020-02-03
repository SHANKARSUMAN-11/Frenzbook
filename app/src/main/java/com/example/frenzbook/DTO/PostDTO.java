package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

public class PostDTO{

	@SerializedName("likeCount")
	private int likeCount;

	@SerializedName("postId")
	private String postId;

	@SerializedName("category")
	private String category;

	@SerializedName("userId")
	private String userId;

	@SerializedName("content")
	private Content content;

	@SerializedName("timestamp")
	private String timestamp;

	public void setLikeCount(int likeCount){
		this.likeCount = likeCount;
	}

	public int getLikeCount(){
		return likeCount;
	}

	public void setPostId(String postId){
		this.postId = postId;
	}

	public String getPostId(){
		return postId;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setContent(Content content){
		this.content = content;
	}

	public Content getContent(){
		return content;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"PostDTO{" + 
			"likeCount = '" + likeCount + '\'' + 
			",postId = '" + postId + '\'' + 
			",category = '" + category + '\'' + 
			",userId = '" + userId + '\'' + 
			",content = '" + content + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}