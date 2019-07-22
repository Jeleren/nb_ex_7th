package com.jeleren.controller;

import com.jeleren.bean.UserRelation;
import com.jeleren.bean.UserInfo;
import com.jeleren.service.IUserRelationService;
import com.jeleren.service.IRoleService;
import com.jeleren.service.IUserInfoService;
import com.jeleren.utils.JWT;
import com.jeleren.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
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
            String token = JWT.sign(user, 60L* 1000L* 30L*100);
            System.out.println("token"+token);
            responseData.putDataValue("token", token);
        }
        else{
            responseData =  ResponseData.customerError();
        }
        return responseData.getData();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData register(HttpServletRequest request) {
        String username = request.getParameter("username");
        UserInfo user = userInfoService.findUserByUsername(username);
        if (user == null) {
            userInfoService.register(new UserInfo(username, request.getParameter("password")));
            return ResponseData.ok();
        }
        return ResponseData.badRequest("该用户已注册");
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public UserInfo getUserInfo(@PathVariable(name = "user_id", required = true)int user_id){
        System.out.println(user_id);
        return userInfoService.selectUserById(user_id);
    }


}
