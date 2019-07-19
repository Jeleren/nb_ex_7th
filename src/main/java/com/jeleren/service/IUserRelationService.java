package com.jeleren.service;

import com.jeleren.bean.UserInfo;

import java.util.List;


public interface IUserRelationService {
    //获取粉丝列表
    List<UserInfo> getFansList(int user_id);

    //关注
    boolean follow(int follow_id, int fan_id);

    //取消关注
    boolean cancelFollow(int follow_id, int fan_id);

    //获取关注列表
    List<UserInfo> getFollowList(int user_id);

    //查询是否已关注
    boolean checkFollowed(int follow_id, int fan_id);
}
