package com.jeleren.dao;


import com.jeleren.bean.LunboImg;

import java.util.List;

/***********************************************
 #
 #     @Classname ILunboImgDao
 #     @Description TODO
 #     @Date 2019/7/17 14:00
 #     Created by weihuan 1548710086@qq.com
 #     
 ***********************************************/
public interface ILunboImgDao {
//    public List<LunboImg> findAll();
////    public void insertLunboImg(LunboImg lunboImg);
////    public void deleteLunboImg(int id);
////    public void updateLunboImge(LunboImg lunboImg);
////    public LunboImg findImgById(int id);
    public   List<LunboImg> findShowImg();
}
