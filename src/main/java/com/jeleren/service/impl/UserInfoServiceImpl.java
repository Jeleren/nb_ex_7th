package com.jeleren.service.impl;

import com.github.pagehelper.PageHelper;
import com.jeleren.bean.UserInfo;
import com.jeleren.dao.IUserInfoDao;
import com.jeleren.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public List<UserInfo> findAll(int page, int num) {
        PageHelper.startPage(page, num);
        return userInfoDao.findAll();
    }

    @Override
    public UserInfo login(UserInfo userInfo) {
        return userInfoDao.login(userInfo);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        userInfoDao.addUser(userInfo);
    }

    @Override
    public UserInfo selectUserById (int id) {
        return userInfoDao.selectUserById(id);
    }
    @Override
    public void updateUser(UserInfo userInfo) {
        userInfoDao.updateUser(userInfo);
    }

    @Override
    public int register(UserInfo userInfo) {
        return userInfoDao.register(userInfo);
    }

    @Override
    public UserInfo findUserByUsername(String username) {
        return userInfoDao.findUserByUsername(username);
    }

    @Override
    public void updateUploadNum(int user_id) {
        userInfoDao.updateUploadNum(user_id);
    }
}
