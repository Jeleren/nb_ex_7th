package com.jeleren.bean;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: ImageInfo <br/>
 * Description: <br/>
 * date: 2019/7/17 22:06<br/>
 *
 * @author a8243<br />
 * @since JDK 1.8
 */
public class ImageInfo {
    private MultipartFile imageFile;
    private int id;
    private int if_active;
    private String image;
    private String name;
    private String cates;
    private int user_id;
    private String description;
    private String keywords;//按空格分类

    public ImageInfo() {
        cates ="";
        description="";
        keywords="";
    }

    public int getIf_active() {
        return if_active;
    }

    public void setIf_active(int if_active) {
        this.if_active = if_active;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getCates() {
        return cates;
    }

    public void setCates(String cates) {
        this.cates = cates;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "imageFile=" + imageFile +
                ", id=" + id +
                ", if_active=" + if_active +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", cates='" + cates + '\'' +
                ", user_id=" + user_id +
                ", description='" + description + '\'' +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
