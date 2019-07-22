package com.jeleren.bean;

public class ImageLike {
    private int rela_id;
    private int image;
    private int user;

    public ImageLike() {
    }

    public int getRela_id() {
        return rela_id;
    }

    public void setRela_id(int rela_id) {
        this.rela_id = rela_id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ImageLike{" +
                "rela_id=" + rela_id +
                ", image=" + image +
                ", user=" + user +
                '}';
    }
}
