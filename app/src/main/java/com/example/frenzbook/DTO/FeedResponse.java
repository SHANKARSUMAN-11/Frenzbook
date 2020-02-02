package com.example.frenzbook.DTO;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FeedResponse{

	@SerializedName("postUserDTOList")
	private List<PostUserDTOListItem> postUserDTOList;

	@SerializedName("messages")
	private List<String> messages;

	@SerializedName("user2Ids")
	private List<String> user2Ids;

	@SerializedName("userId")
	private String userId;

	public void setPostUserDTOList(List<PostUserDTOListItem> postUserDTOList){
		this.postUserDTOList = postUserDTOList;
	}

	public List<PostUserDTOListItem> getPostUserDTOList(){
		return postUserDTOList;
	}

	public void setMessages(List<String> messages){
		this.messages = messages;
	}

	public List<String> getMessages(){
		return messages;
	}

	public void setUser2Ids(List<String> user2Ids){
		this.user2Ids = user2Ids;
	}

	public List<String> getUser2Ids(){
		return user2Ids;
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
			"FeedResponse{" + 
			"postUserDTOList = '" + postUserDTOList + '\'' + 
			",messages = '" + messages + '\'' + 
			",user2Ids = '" + user2Ids + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}