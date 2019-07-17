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
    private  String imgUrl;
    private int ifShow;
    private  String imgName;

    public int getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getIfShow() {
        return ifShow;
    }

    public String getImgName() {
        return imgName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setIfShow(int ifShow) {
        this.ifShow = ifShow;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "LunboImg{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", ifShow=" + ifShow +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
