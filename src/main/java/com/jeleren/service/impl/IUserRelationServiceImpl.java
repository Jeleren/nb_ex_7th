package com.jeleren.service.impl;

import com.jeleren.bean.UserRelation;
import com.jeleren.bean.UserInfo;
import com.jeleren.dao.IUserRelationDao;
import com.jeleren.dao.IUserInfoDao;
import com.jeleren.service.IUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserRelationServiceImpl implements IUserRelationService {

    @Autowired
    private IUserRelationDao userRelationDao;

    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public List<UserInfo> getFansList(int user_id) {
        return userRelationDao.getFansList(user_id);
    }

    @Override
    public boolean follow(int follow_id, int fan_id) {
        boolean tag1 = userRelationDao.follow(follow_id, fan_id);
        boolean tag2 = userInfoDao.editUserFollow(fan_id, "add");
        boolean tag3 = userInfoDao.editUserFans(follow_id, "add");
        return tag1&&tag2&&tag3;
    }

    @Override
    public boolean cancelFollow(int follow_id, int fan_id) {

        boolean tag = userRelationDao.cancelFollow(follow_id, fan_id) && userInfoDao.editUserFollow(fan_id, "delete")
                && userInfoDao.editUserFans(follow_id, "delete");
        return tag;
    }

    @Override
    public List<UserInfo> getFollowList(int user_id) {
        return userRelationDao.getFollowList(user_id);
    }

    @Override
    public boolean checkFollowed(int follow_id, int fan_id) {
        UserRelation focus = userRelationDao.checkFollowed(follow_id, fan_id);
        if(focus != null)
            return true;
        else
            return false;
    }

}
