package com.example.frenzbook;

import java.io.Serializable;

public class Content implements Serializable {
    String text;
    String image;
    String video;

    public Content(String text, String image, String video) {
        this.text = text;
        this.image = image;
        this.video = video;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return this.video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Content{" +
                "Text='" + text + '\'' +
                ", Image='" + image + '\'' +
                ", Video='" + video + '\'' +
                '}';
    }
}
