package com.jeleren.bean;

import java.util.List;

public class ImageAndUserResult {
    private List<ImageInfo > imageInfos;
    private UserInfo user;

    public List<ImageInfo> getImageInfos() {
        return imageInfos;
    }

    public void setImageInfos(List<ImageInfo> imageInfos) {
        this.imageInfos = imageInfos;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
