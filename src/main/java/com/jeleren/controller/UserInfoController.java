package com.jeleren.controller;

import com.github.pagehelper.PageInfo;
import com.jeleren.bean.Role;
import com.jeleren.bean.UserInfo;
import com.jeleren.service.IRoleService;
import com.jeleren.service.IUserInfoService;
import com.jeleren.utils.JWT;
import com.jeleren.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.registry.infomodel.User;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(UserInfo userInfo) {
        UserInfo user = userInfoService.login(userInfo);
        ResponseData responseData = ResponseData.ok();
        if(user != null) {
            //给用户jwt加密生成token
            String token = JWT.sign(user, 60L* 1000L* 30L);
            System.out.println("token"+token);
            responseData.putDataValue("token", token);
        }
        else{
            responseData =  ResponseData.customerError();
        }
        return responseData.getData();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(UserInfo userInfo) {
        UserInfo user = userInfoService.findUserByUsername(userInfo.getUsername());
        if (user != null) {
            return null;
        }
        return null;
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public UserInfo getUserInfo(@PathVariable(name = "user_id", required = true)int user_id){
        System.out.println(user_id);
        return userInfoService.selectUserById(user_id);
    }
}
