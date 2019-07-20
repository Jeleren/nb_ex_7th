package com.jeleren.service.impl;

import com.jeleren.bean.Group;
import com.jeleren.dao.IGroupDao;
import com.jeleren.service.IGroupService;
import org.apache.ibatis.annotations.Param;
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
            List<Group> secondGroup = groupDao.getGroupByPId(group.getId());
            if (secondGroup != null) {
                group.setKids(secondGroup);
                for(Group group1: secondGroup) {
                    List<Group> thirdGroup = groupDao.getGroupByPId(group1.getId());
                    if (thirdGroup != null) {
                        group1.setKids(thirdGroup);
                    }
                }
            }
        }
        System.out.println(firstGroup);
        return firstGroup;
    }

    @Override
    public void addIGR(String name, int image_id) {
        Group baseGroup = groupDao.getGroupByName(name);
        groupDao.addIGRela(image_id, baseGroup.getId());
        groupDao.addIGRela(image_id, baseGroup.getParent_id());
        groupDao.addIGRela(image_id, groupDao.getGroupById(baseGroup.getParent_id()));
    }

    @Override
    public void addIKR(String keyword, int image_id) {
        groupDao.addIKR(keyword, image_id);
    }


}
