package com.jeleren.bean;

public class ImageResult {
    private ImageInfo image;
    private UserInfo user;
    private Integer if_like;
//    private int if_collect;
    private Integer if_follow;
    private  int id;
    private int count;
    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ImageResult{" +
                "image=" + image +
                ", user=" + user +
                ", if_like=" + if_like +
                ", if_follow=" + if_follow +
                '}';
    }

    public ImageInfo getImage() {
        return image;
    }

    public void setImage(ImageInfo image) {
        this.image = image;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Integer getIf_like() {
        return if_like;
    }

    public void setIf_like(Integer if_like) {
        this.if_like = if_like;
    }

    public Integer getIf_follow() {
        return if_follow;
    }

    public void setIf_follow(Integer if_follow) {
        this.if_follow = if_follow;
    }
}
