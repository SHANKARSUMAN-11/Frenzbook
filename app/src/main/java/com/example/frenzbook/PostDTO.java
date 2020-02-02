package com.example.frenzbook;

import java.io.Serializable;

public class PostDTO implements Serializable {
    private String category;
    private String userId;
    private Content content;

    public PostDTO(String category,String userId,Content content) {
        this.category = category;
        this.userId = userId;
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "PostDTO{" +
                "category='" + category + '\'' +
                ", userId='" + userId + '\'' +
                ", content=" + content +
                '}';
    }

}
