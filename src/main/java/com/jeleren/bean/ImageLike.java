package com.jeleren.bean;

public class ImageLike {
    private int id;
    private int image;
    private int user;

    public ImageLike() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", image=" + image +
                ", user=" + user +
                '}';
    }
}
