package com.example.frenzbook;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    @SerializedName("success")
    private boolean success;
    @SerializedName("httpStatus")
    private String httpStatus;
    @SerializedName("errorMessage")
    private Object errorMessage;

    private T data;

    public BaseResponse(boolean success, String httpStatus, Object errorMessage, T data) {
        this.success = success;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
