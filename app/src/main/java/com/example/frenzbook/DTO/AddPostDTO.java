package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddPostDTO implements Serializable {

        @SerializedName("category")
        private String category;
        private String userId;
        private Content content;
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

