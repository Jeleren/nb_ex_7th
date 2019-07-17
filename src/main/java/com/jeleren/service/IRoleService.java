package com.jeleren.service;

import com.jeleren.bean.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll();
    void addRole(int user_id, int role_id);
}
