package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

import javax.xml.transform.Templates;

public class BaseResponse<T> {

    public BaseResponse(boolean success, String httpStatus, Object errorMessage, T data) {
        this.success = success;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public BaseResponse(){

    }

    public boolean isSuccess() {
        return success;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setData(T data) {
        this.data = data;
    }

    @SerializedName("success")
    private boolean success;

    @SerializedName("httpStatus")
    private String httpStatus;

    @SerializedName("errorMessage")
    private Object errorMessage;

    private T data;
}
