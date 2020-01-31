package com.example.frenzbook;

public class ModelFeed {
    int id,likes,comments,propic,postpic;
    String name,status;
    public ModelFeed(int id,int likes,int comments,int propic,int postpic,String name,String status){
        this.id=id;
        this.likes=likes;
        this.comments=comments;
        this.propic=propic;
        this.postpic=postpic;
        this.name=name;
        this.status=status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getPropic() {
        return propic;
    }

    public void setPropic(int propic) {
        this.propic = propic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPostpic() {
        return postpic;
    }

    public void setPostpic(int postpic) {
        this.postpic = postpic;
    }
}
