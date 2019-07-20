package com.jeleren.controller;

import com.jeleren.bean.ImageInfo;
import com.jeleren.dao.IUserInfoDao;
import com.jeleren.service.IGroupService;
import com.jeleren.bean.ImageLike;
import com.jeleren.service.IImageInfoService;
import com.jeleren.service.IUserInfoService;
import com.jeleren.service.IImageLikeService;
import com.jeleren.utils.ResponseData;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ImageInfoController <br/>
 * Description: <br/>
 * date: 2019/7/17 22:07<br/>
 *
 * @author a8243<br />
 * @since JDK 1.8
 */
@RestController
@RequestMapping("image")
public class ImageInfoController {

    @Autowired
    private IImageInfoService iImageInfoService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IUserInfoService userInfoService;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData uploadPic(ImageInfo imageInfo, HttpServletRequest request) throws IOException {
        MultipartFile imageFile = imageInfo.getImageFile();
        System.out.println(imageInfo.toString());
        String imgPath = null;//装配后的图片地址
        if (imageFile != null && !imageFile.isEmpty()) {
            //使用时间戳给图片重命名
//            String url = request.getSession().getServletContext().getRealPath("/").split("\\\\target")[0] + "/src/main/webapp/images/";
            String url = request.getSession().getServletContext().getRealPath("/images/");
            File file = new File(url);
            if(!file.isDirectory()){
                file.mkdir();
            }

            long nowTime = System.currentTimeMillis();
            String name = nowTime + "";
            //得到图片后缀
            String ext = FilenameUtils.getExtension(imageFile.getOriginalFilename());
            imgPath = name + "." + ext;

            String absoPath = url + imgPath;

            imageInfo.setImage(imgPath);
            imageInfo.setUser_id(Integer.parseInt(request.getParameter("user_id")));
            String image_cate = request.getParameter("cates");
            imageInfo.setCates(image_cate);
            imageInfo.setDescription(request.getParameter("description"));
            imageInfo.setName(request.getParameter("name") + "|" + name);
            imageInfo.setIf_active(imageInfo.getIf_active());
            imageInfo.setAdd_time(new Date(System.currentTimeMillis()));
            imageInfo.setPattern(ext);
            // 以绝对路径保存重名命后的图片
            imageFile.transferTo(new File(absoPath));
            //将信息保存到数据库中
            iImageInfoService.add(imageInfo);
            int image_id = imageInfo.getId();
            groupService.addIGR(image_cate, image_id);
            String keywords[] = imageInfo.getKeywords().split(" ");
            for(String keyword: keywords){
                groupService.addIKR(keyword, image_id);
            }
            userInfoService.updateUploadNum(imageInfo.getUser_id());
        }
        ResponseData res = ResponseData.ok();
        res.putDataValue("image", imgPath);
        return res;
    }

    @GetMapping("/group/{group_id}")
    public Map getMainImage(HttpServletRequest request, @PathVariable(name = "group_id", required = true) int group_id, @RequestParam("page") int page, @RequestParam("num") int num) {
//        iImageInfoService.getMainImage(Integer.parseInt((String)request.getAttribute("user_id")), group_id, page, num);
        Map<String, Object> m = new HashMap<>();
        int user_id = Integer.parseInt(request.getAttribute("user_id").toString());
        m.put("result", iImageInfoService.getMainImage(user_id, group_id, page, num));
        return m;
    }

    @GetMapping("{image_id}")
    public Map getImageInfo(HttpServletRequest request, @PathVariable(name = "image_id", required = true) int image_id){
        Map<String, Object> m = new HashMap<>();
        m.put("result", iImageInfoService.getImageInfo(image_id, Integer.parseInt(request.getAttribute("user_id").toString())));
        return m;
    }


}