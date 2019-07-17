package com.jeleren.bean;

public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String image;
    private String description;
    private int fan_num;
    private int follow_num;
    private int upload_num;
    private int download_num;

    public UserInfo() {
    }

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
        this.image = "default user image";
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFan_num() {
        return fan_num;
    }

    public void setFan_num(int fan_num) {
        this.fan_num = fan_num;
    }

    public int getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(int follow_num) {
        this.follow_num = follow_num;
    }

    public int getUpload_num() {
        return upload_num;
    }

    public void setUpload_num(int upload_num) {
        this.upload_num = upload_num;
    }

    public int getDownload_num() {
        return download_num;
    }
    public void setDownload_num(int download_num) {
        this.download_num = download_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
