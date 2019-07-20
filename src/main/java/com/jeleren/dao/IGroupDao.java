package com.jeleren.dao;

import com.jeleren.bean.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGroupDao {
    List<Group> getGroupByLevel(int level);
    List<Group> getGroupByPId(int parent_id);
    Group getGroupByName(String name);
    int getGroupById(int id);
    void addIGRela(@Param("image_id") int image_id, @Param("group_id") int group_id);
    void addIKR(@Param("keyword") String keyword, @Param("image_id") int image_id);
}
