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
}
