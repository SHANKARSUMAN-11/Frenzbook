package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

public class ChildCommentItem{

	@SerializedName("timeStamp")
	private String timeStamp;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("commentId")
	private String commentId;

	@SerializedName("parentCommentId")
	private String parentCommentId;

	@SerializedName("postId")
	private String postId;

	@SerializedName("text")
	private String text;

	@SerializedName("userName")
	private String userName;

	@SerializedName("childComment")
	private String childComment;

	public void setTimeStamp(String timeStamp){
		this.timeStamp = timeStamp;
	}

	public String getTimeStamp(){
		return timeStamp;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setCommentId(String commentId){
		this.commentId = commentId;
	}

	public String getCommentId(){
		return commentId;
	}

	public void setParentCommentId(String parentCommentId){
		this.parentCommentId = parentCommentId;
	}

	public String getParentCommentId(){
		return parentCommentId;
	}

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

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setChildComment(String childComment){
		this.childComment = childComment;
	}

	public String getChildComment(){
		return childComment;
	}

	@Override
 	public String toString(){
		return 
			"ChildCommentItem{" + 
			"timeStamp = '" + timeStamp + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",commentId = '" + commentId + '\'' + 
			",parentCommentId = '" + parentCommentId + '\'' + 
			",postId = '" + postId + '\'' + 
			",text = '" + text + '\'' + 
			",userName = '" + userName + '\'' + 
			",childComment = '" + childComment + '\'' + 
			"}";
		}
}