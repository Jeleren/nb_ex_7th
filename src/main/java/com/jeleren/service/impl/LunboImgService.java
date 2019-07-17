package com.jeleren.service.impl;


import com.jeleren.bean.LunboImg;
import com.jeleren.dao.ILunboImgDao;
import com.jeleren.service.ILunboImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***********************************************
 #
 #     @Classname LunboImgService
 #     @Description TODO
 #     @Date 2019/7/17 14:23
 #     Created by weihuan 1548710086@qq.com
 #     
 ***********************************************/
@Service("lunboImgService")
public class LunboImgService implements ILunboImgService {
    @Autowired
    ILunboImgDao lunboImgDao;


    @Override
    public List<LunboImg> findShowImg() {
        return lunboImgDao.findShowImg();
    }
}
