package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

	@SerializedName("data")
	private UserData data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("httpStatus")
	private String httpStatus;

	@SerializedName("errorMessage")
	private Object errorMessage;

	public void setData(UserData data){
		this.data = data;
	}

	public UserData getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setHttpStatus(String httpStatus){
		this.httpStatus = httpStatus;
	}

	public String getHttpStatus(){
		return httpStatus;
	}

	public void setErrorMessage(Object errorMessage){
		this.errorMessage = errorMessage;
	}

	public Object getErrorMessage(){
		return errorMessage;
	}

	@Override
 	public String toString(){
		return 
			"UserResponse{" +
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",httpStatus = '" + httpStatus + '\'' + 
			",errorMessage = '" + errorMessage + '\'' + 
			"}";
		}
}