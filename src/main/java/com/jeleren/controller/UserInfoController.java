package com.jeleren.controller;

import com.jeleren.bean.CollectionInfo;
import com.jeleren.bean.UserRelation;
import com.jeleren.bean.UserInfo;
import com.jeleren.service.IImageInfoService;
import com.jeleren.service.IUserRelationService;
import com.jeleren.service.IRoleService;
import com.jeleren.service.IUserInfoService;
import com.jeleren.utils.JWT;
import com.jeleren.utils.ResponseData;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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

    //更新用户的头像，用户的用户名和描述
    @RequestMapping(value = "/updateUserInfo", method =RequestMethod.POST)
    @ResponseBody
    public void update(UserInfo userInfo,HttpServletRequest
                       request) {
        MultipartFile userImage = userInfo.getUserImage();
        System.out.println(userInfo.toString());
        //创建用户头像的文件夹user_images
        String url = request.getSession().getServletContext().getRealPath("/user_images/");
        File file = new File(url);
        if (!file.isDirectory()) {
            file.mkdir();
        }
        String imgPath = null;//装配后的图片地址
        if (userImage != null && !userImage.isEmpty()) {
            //使用时间戳给图片重命
            long nowTime = System.currentTimeMillis();
            String name = nowTime + "";
            //得到图片后缀
            String ext = FilenameUtils.getExtension(userImage.getOriginalFilename());
            imgPath = name + "." + ext;
        }
        //图片的完整路径
        String absoPath = url + imgPath;
        //图片的相对路径
        userInfo.setImage(imgPath);
        userInfo.setUsername(userInfo.getUsername());
        userInfo.setDescription(userInfo.getDescription());

        // 以绝对路径保存重名命后的图片
        if(absoPath!=null && imgPath!=null) {
            try {
                userImage.transferTo(new File(absoPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userInfoService.updateUserInfo(userInfo, 5);

    }





}
