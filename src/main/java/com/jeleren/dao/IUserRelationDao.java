package com.jeleren.dao;

import com.jeleren.bean.UserRelation;
import com.jeleren.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserRelationDao {

    //获取粉丝列表
    List<UserInfo> getFansList(@Param("user_id") int user_id);

    //关注
    boolean follow(@Param("follow_id") int follow_id, @Param("fan_id") int fan_id);

    //取消关注
    boolean cancelFollow(@Param("follow_id") int follow_id, @Param("fan_id") int fan_id);

    //获取关注列表
    List<UserInfo> getFollowList(int user_id);

    //查询是否已关注
    UserRelation checkFollowed(@Param("follow_id") int follow_id, @Param("fan_id") int fan_id);



}
