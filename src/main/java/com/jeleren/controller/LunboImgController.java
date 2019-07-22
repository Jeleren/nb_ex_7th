package com.jeleren.controller;


import com.jeleren.bean.LunboImg;
import com.jeleren.service.ILunboImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***********************************************
 #
 #     @Classname LunboImgController
 #     @Description TODO
 #     @Date 2019/7/17 14:19
 #     Created by weihuan 1548710086@qq.com
 #     
 ***********************************************/
@RestController
@RequestMapping("/banner")
@Scope("prototype")
public class LunboImgController {
    @Autowired
    ILunboImgService lunboImgService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List getImg(){
        List<LunboImg> imgs = lunboImgService.findShowImg();
//        System.out.println(imgs.get(0));
        return imgs;
    }
}
