package com.jeleren.dao;

import com.jeleren.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserInfoDao {
    public List<UserInfo> findAll();

    UserInfo login(UserInfo userInfo);

    int register(UserInfo userInfo);

    void addUser(UserInfo userInfo);

    UserInfo selectUserById(int id);

    void updateUser(UserInfo userInfo);

    UserInfo findUserByUsername(String username);

    void updateUploadNum (int user_id);

    //修改关注记录
    boolean editUserFollow(@Param("user_id") int user_id, @Param("record") String record);

    //修改粉丝记录
    boolean editUserFans(@Param("user_id") int user_id, @Param("record") String record);

    //更新个人用户信息
    void updateUserInfo(@Param("userinfo") UserInfo userinfo,@Param("uid") int id);
}
