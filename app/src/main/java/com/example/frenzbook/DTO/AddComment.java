package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

public class AddComment{

	@SerializedName("postId")
	private String postId;

	public void setParentCommentId(String parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public String getParentCommentId() {
		return parentCommentId;
	}

	@SerializedName("text")
	private String text;

	@SerializedName("userId")
	private String userId;

	@SerializedName("parentCommentId")
	private String parentCommentId;

	public void setPostId(String postId){
		this.postId = postId;
	}

	public String getPostId(){
		return postId;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"AddComment{" + 
			"postId = '" + postId + '\'' + 
			",text = '" + text + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}