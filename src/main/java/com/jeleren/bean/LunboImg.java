package com.jeleren.bean;

/***********************************************
 #
 #     @Classname lunboImg
 #     @Description TODO
 #     @Date 2019/7/17 13:55
 #     Created by weihuan 1548710086@qq.com
 #     
 ***********************************************/
public class LunboImg {
    private int id;
    private  String image;
    private int ifShow;
    private  String title;

    public int getId() {
        return id;
    }

    public String getimage() {
        return image;
    }

    public int getIfShow() {
        return ifShow;
    }

    public String gettitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public void setIfShow(int ifShow) {
        this.ifShow = ifShow;
    }

    public void settitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "LunboImg{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", ifShow=" + ifShow +
                ", title='" + title + '\'' +
                '}';
    }
}
