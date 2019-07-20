package com.jeleren.controller;

import com.jeleren.bean.ImageLike;
import com.jeleren.bean.UserInfo;
import com.jeleren.bean.UserRelation;
import com.jeleren.service.IImageLikeService;
import com.jeleren.service.IUserRelationService;
import com.jeleren.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("")
public class UserRelationController {

    @Autowired
    private IUserRelationService userRelationService;

    @Autowired
    private IImageLikeService iImageLikeService;


    @GetMapping(value = "/fan/{user_id}")
    public List<UserInfo> getFansList(@PathVariable(name = "user_id", required = true)int user_id) {
        return userRelationService.getFansList(user_id);
    }

    @GetMapping(value = "/follow/{user_id}")
    public List<UserInfo> getFollowerList(@PathVariable(name = "user_id", required = true)int user_id) {
        return userRelationService.getFollowList(user_id);
    }

    @PostMapping(value = "/follow")
    @ResponseBody
    public ResponseData follow(UserRelation focus) {
        int fan_id = focus.getFan_id();    //当前用户
        int follow_id = focus.getFollow_id();  //要关注的人
        boolean tag = userRelationService.checkFollowed(follow_id, fan_id);
        if(tag){
            boolean cancel = userRelationService.cancelFollow(follow_id, fan_id);
            if(cancel)
                return ResponseData.ok();
            else
                return ResponseData.badRequest("取消关注成功");
        }else {
            boolean follow = userRelationService.follow(follow_id, fan_id);
            if(follow)
                return ResponseData.ok();
            else
                return ResponseData.badRequest("关注成功");
        }

    }

    //图片点赞或取消点赞
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseData like(ImageLike imageLike) {
        int image = imageLike.getImage();
        int user = imageLike.getUser();
        boolean tag = iImageLikeService.checkImageLiked(image, user);
        if(tag){
            boolean cancel = iImageLikeService.cancelImageLike(image, user);
            if(cancel)
                return ResponseData.ok();
            else
                return ResponseData.badRequest("取消点赞成功");
        }else {
            boolean like = iImageLikeService.imageLike(image, user);
            if(like)
                return ResponseData.ok();
            else
                return ResponseData.badRequest("点赞成功");
        }

    }

}
