package com.jeleren.service;

import com.jeleren.bean.UserInfo;

import java.util.List;

public interface IUserInfoService {
    public List<UserInfo> findAll(int page, int num);
    public UserInfo login(UserInfo userInfo);
    void addUser(UserInfo userInfo);
    UserInfo selectUserById(int id);
    void updateUser(UserInfo userInfo);

    int register (UserInfo userInfo);

    UserInfo findUserByUsername (String username);

    void updateUploadNum(int user_id);
}
