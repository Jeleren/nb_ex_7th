package com.jeleren.bean;

import java.util.Date;
import java.util.List;

/***********************************************
 #
 #     @Classname CollectionInfo
 #     @Description TODO
 #     @Date 2019/7/22 14:47
 #     Created by weihuan 1548710086@qq.com
 #     
 ***********************************************/
public class CollectionInfo {
    List<ImageInfo> imageInfos;  //每个收藏夹前 6 张图片
    int id;// 集合 id
    int num ; //收藏图片数目
    String name; //收藏夹名称
    Date update_time; // 收藏夹变更的时间

    @Override
    public String toString() {
        return "CollectionInfo{" +
                "imageInfos=" + imageInfos +
                ", id=" + id +
                ", num=" + num +
                ", name='" + name + '\'' +
                ", update_time=" + update_time +
                '}';
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public List<ImageInfo> getImageInfos() {
        return imageInfos;
    }

    public void setImageInfos(List<ImageInfo> imageInfos) {
        this.imageInfos = imageInfos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
