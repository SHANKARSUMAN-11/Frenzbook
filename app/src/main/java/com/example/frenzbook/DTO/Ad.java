package com.example.frenzbook.DTO;

import com.google.gson.annotations.SerializedName;

public class Ad {

    @SerializedName("adId")
    private String adId;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("location")
    private String location;

    @SerializedName("tag")
    private String tag;

    @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("targetUrl")
    private String targetUrl;

    @SerializedName("advertiserId")
    private String advertiserId;

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getAdId() {
        return adId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getTag() {
        return tag;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }
}
