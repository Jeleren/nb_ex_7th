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
    private String image;
    private String label;
    private String cates;
    private int user_id;
    private String description;
    private String keywords;//按空格分类
    private String pre_image;//预览图

    public ImageInfo() {
        cates ="";
        user_id=1;
        description="";
        keywords="";
        pre_image="";
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPre_image() {
        return pre_image;
    }

    public void setPre_image(String pre_image) {
        this.pre_image = pre_image;
    }
}
