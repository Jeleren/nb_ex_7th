package com.jeleren.controller;

import com.jeleren.bean.ImageInfo;
import com.jeleren.service.IImageInfoService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

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


    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public String uploadPic(ImageInfo imageInfo, HttpServletRequest request) throws IOException {
        MultipartFile imageFile = imageInfo.getImageFile();
        String imgPath = null;//装配后的图片地址
        if (imageFile != null && !imageFile.isEmpty()) {
            //使用时间戳给图片重命名
            String url = request.getSession().getServletContext().getRealPath("images");
            File file = new File(url);
            if(!file.isDirectory()){
                file.mkdir();
            }

            long nowTime = System.currentTimeMillis();
            String name = nowTime + "";
            //得到图片后缀
            String ext = FilenameUtils.getExtension(imageFile
                    .getOriginalFilename());
            imgPath = "images/" + name + "." + ext;

            imageInfo.setImage(imgPath);
            //将信息保存到数据库中
            iImageInfoService.add(imageInfo);
            // 以绝对路径保存重名命后的图片
            imageFile.transferTo(new File(imgPath));
        }


        return imgPath;
    }


}