package com.jeleren.service;

import com.jeleren.bean.Group;

import java.util.List;

public interface IGroupService {
    List<Group> getGroup();
    void addIGR(String name, int image_id);
    void addIKR(String keyword, int image_id);
}
