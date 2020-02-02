package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

public class PostUserDTOListItem{

	@SerializedName("postDTO")
	private PostDTO postDTO;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("userName")
	private String userName;

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

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	@Override
 	public String toString(){
		return 
			"PostUserDTOListItem{" + 
			"postDTO = '" + postDTO + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",userName = '" + userName + '\'' + 
			"}";
		}
}