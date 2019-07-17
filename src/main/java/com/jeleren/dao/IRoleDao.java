package com.jeleren.dao;

import com.jeleren.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleDao {
    public List<Role> findAll();
    void addRole(@Param("user_id") int user_id, @Param("role_id") int role_id);
}
