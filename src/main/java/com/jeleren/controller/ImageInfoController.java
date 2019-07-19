package com.jeleren.controller;

import com.github.pagehelper.PageHelper;
import com.jeleren.bean.ImageInfo;
import com.jeleren.bean.SearchList;
import com.jeleren.service.IImageInfoService;
import com.jeleren.utils.ResponseData;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData uploadPic(ImageInfo imageInfo, HttpServletRequest request) throws IOException {
        MultipartFile imageFile = imageInfo.getImageFile();
        System.out.println(imageInfo.toString());
        String imgPath = null;//装配后的图片地址
        if (imageFile != null && !imageFile.isEmpty()) {
            //使用时间戳给图片重命名
            String url = request.getSession().getServletContext().getRealPath("/").split("\\\\target")[0] + "/src/main/webapp/images/";
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
            imageInfo.setCates(request.getParameter("cates"));
            imageInfo.setDescription(request.getParameter("description"));
            imageInfo.setName(request.getParameter("name"));
            imageInfo.setIf_active(imageInfo.getIf_active());
            // 以绝对路径保存重名命后的图片
            imageFile.transferTo(new File(absoPath));
            //将信息保存到数据库中
            iImageInfoService.add(imageInfo);
        }
        ResponseData res = ResponseData.ok();
        res.putDataValue("image", imgPath);
        return res;
    }

    @RequestMapping(value = "/searchImage", method = RequestMethod.POST)
    @ResponseBody
    public List searchImage(ImageInfo imageInfo, HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        String keyword = httpServletRequest.getParameter("keyword");
        String cates = httpServletRequest.getParameter("-cates");
        String seq = httpServletRequest.getParameter("order");
        int page =Integer.parseInt(httpServletRequest.getParameter("page"));
        int size =Integer.parseInt(httpServletRequest.getParameter("size"));
//        PageHelper.startPage(page, num);
        /*
        这个地方，逻辑处理需要严谨
         */
        String[] tmp2 = null;
        List<String> cateList =new ArrayList<>();
        if (cates != null ){
            tmp2 = cates.split(" ");
            for(int i =0;i<tmp2.length;i++){
                cateList.add(tmp2[i]);
            }

        }
        String pattern = httpServletRequest.getParameter("pattern");

        SearchList searchList = new SearchList();
        searchList.setKeyword(keyword);
        searchList.setCateList(cateList);
        searchList.setPattern(pattern);
        searchList.setPage(page);
        searchList.setSize(size);
        if(seq.equals("-")){
            searchList.setSeq("DESC");
        }else{
            searchList.setSeq("ASC");
        }
        List<ImageInfo> imageInfo1=iImageInfoService.searchImage(searchList);

        return imageInfo1;
    }


}