package com.jeleren.dao;

import com.jeleren.bean.Group;

import java.util.List;

public interface IGroupDao {
    List<Group> getGroupByLevel(int level);
    List<Group> getGroupById(int parent_id);
}
