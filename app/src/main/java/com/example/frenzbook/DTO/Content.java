package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

public class Content{

	@SerializedName("image")
	private String image;

	@SerializedName("video")
	private String video;

	@SerializedName("text")
	private String text;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setVideo(String video){
		this.video = video;
	}

	public String getVideo(){
		return video;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"Content{" + 
			"image = '" + image + '\'' + 
			",video = '" + video + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}