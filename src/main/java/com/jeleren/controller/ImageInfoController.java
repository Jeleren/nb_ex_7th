package com.jeleren.controller;

import com.github.pagehelper.PageHelper;
import com.jeleren.bean.ImageInfo;
import com.jeleren.bean.SearchList;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
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


    @Autowired
    private IImageLikeService iImageLikeService;


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

    @RequestMapping(value = "/searchImage", method = RequestMethod.POST)
    @ResponseBody
    public List searchImage(ImageInfo imageInfo, HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        String keyword = httpServletRequest.getParameter("keyword");
        String cates = httpServletRequest.getParameter("-cates");
        String seq = httpServletRequest.getParameter("order");
        int page = Integer.parseInt(httpServletRequest.getParameter("page"));
        int size = Integer.parseInt(httpServletRequest.getParameter("size"));
//        PageHelper.startPage(page, num);
        /*
        这个地方，逻辑处理需要严谨
         */
        String[] tmp2 = null;
        List<String> cateList = new ArrayList<>();
        if (cates != null) {
            tmp2 = cates.split(" ");
            for (int i = 0; i < tmp2.length; i++) {
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
        if (seq.equals("-")) {
            searchList.setSeq("DESC");
        } else {
            searchList.setSeq("ASC");
        }
        List<ImageInfo> imageInfo1 = iImageInfoService.searchImage(searchList);

        return imageInfo1;
    }

    //获得不同状态的图片
    @RequestMapping(value = "/getImagesByActive", method = RequestMethod.POST)
    @ResponseBody
    public List<ImageInfo> getImagesByActive(HttpServletRequest request, HttpServletResponse response) {
        //假设用户id 为 1，这个地方的id可以通过token 获得
        int uid = 1;
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        int if_active = Integer.parseInt(request.getParameter("if_active"));
        List<ImageInfo> imageInfos = iImageInfoService.getImagesByActive(uid,page,size,if_active);

        return  imageInfos;
    }

    //获得用户上传的图片
    @RequestMapping(value = "/getUserImages", method = RequestMethod.POST)
    @ResponseBody
    public List<ImageInfo> getUserImages(HttpServletRequest request) {
        //假设用户id 为 1，这个地方的id可以通过token 获得
        int uid = 1;
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        List<ImageInfo> imageInfos = iImageInfoService.getUserImages(uid,page,size);
        return imageInfos;
    }

    //图片点赞或取消点赞
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseData like(ImageLike imageLike) {
        int image = imageLike.getImage();
        int user = imageLike.getUser();
        boolean tag = iImageLikeService.checkImageLiked(image, user);
        if (tag) {
            boolean cancel = iImageLikeService.cancelImageLike(image, user);
            if (cancel)
                return ResponseData.ok();
            else
                return ResponseData.badRequest("取消点赞成功");
        } else {
            boolean like = iImageLikeService.imageLike(image, user);
            if (like)
                return ResponseData.ok();
            else
                return ResponseData.badRequest("点赞成功");
        }

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