package com.example.frenzbook.DTO;

import org.jetbrains.annotations.NotNull;

public class onClickRequest {

    @NotNull
    String adId;
    @NotNull
    String tag;

    public String getAdId() {
        return adId;
    }

    public String getTag() {
        return tag;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public String getSource() {
        return source;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @NotNull
    String advertiserId;
    @NotNull
    String categoryId;
    String userId;
    String description;
    @NotNull
    String source;
    @NotNull
    String targetUrl;
}
