package com.jeleren.service.impl;

import com.jeleren.bean.Group;
import com.jeleren.dao.IGroupDao;
import com.jeleren.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private IGroupDao groupDao;

    @Override
    public List<Group> getGroup() {
        List<Group> firstGroup = groupDao.getGroupByLevel(1);
        for(Group group: firstGroup){
            List<Group> secondGroup = groupDao.getGroupById(group.getId());
            if (secondGroup != null) {
                group.setKids(secondGroup);
                for(Group group1: secondGroup) {
                    List<Group> thirdGroup = groupDao.getGroupById(group1.getId());
                    if (thirdGroup != null) {
                        group1.setKids(thirdGroup);
                    }
                }
            }
        }
        System.out.println(firstGroup);
        return firstGroup;
    }
}
