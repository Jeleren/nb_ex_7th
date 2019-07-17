package com.jeleren.service.impl;

import com.jeleren.bean.Role;
import com.jeleren.dao.IRoleDao;
import com.jeleren.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleDao roleDao;

    @Override
    public List<Role> findAll(){
        return roleDao.findAll();
    }

    @Override
    public void addRole(int user_id, int role_id) {
        System.out.println("user_id"+user_id);
        roleDao.addRole(user_id, role_id);
    }
}
