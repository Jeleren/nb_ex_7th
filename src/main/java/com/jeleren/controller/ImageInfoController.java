package com.jeleren.controller;

import com.jeleren.bean.*;
import com.jeleren.service.IGroupService;
import com.jeleren.service.IImageInfoService;
import com.jeleren.service.IImageLikeService;
import com.jeleren.service.IUserInfoService;
import com.jeleren.utils.ResponseData;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * ClassName: ImageInfoController <br/>
 * Description: <br/>
 * date: 2019/7/17 22:07<br/>
 *
 * @author a8243<br                                                               />
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
            String url = request.getSession().getServletContext().getRealPath("/images/");
            File file = new File(url);
            if (!file.isDirectory()) {
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
            imageInfo.setName(request.getParameter("name"));
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
            for (String keyword : keywords) {
                groupService.addIKR(keyword, image_id);
            }
            // 更新用户上传数量
            userInfoService.updateUploadNum(imageInfo.getUser_id());
        }
        ResponseData res = ResponseData.ok();
        res.putDataValue("image", imgPath);
        return res;
    }

    @RequestMapping(value = "{image_id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateImage(@PathVariable("image_id") int image_id, HttpServletRequest request) {
        ImageInfo imageInfo = new ImageInfo();
        String cates = request.getParameter("cates");
        String keywords = request.getParameter("keywords");
        String decription = request.getParameter("description");
        String if_active = request.getParameter("if_active");
        imageInfo.setId(image_id);
        if (cates != null)
            imageInfo.setCates(cates);
        if (keywords != null)
            imageInfo.setKeywords(keywords);
        if (decription != null)
            imageInfo.setDescription(decription);
        imageInfo.setIf_active(Integer.parseInt(if_active));
        iImageInfoService.updateImage(imageInfo);
        return ResponseData.ok();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> searchImage(HttpServletRequest request) throws UnsupportedEncodingException {
        String keyword = request.getParameter("search");
        String cates = request.getParameter("-cates");
        String seq = request.getParameter("order");
        String temp = request.getParameter("page");
        int page;
        if (temp != null) {
            page = Integer.parseInt(temp);
        } else page = 1;
        int size = 16;
        String pattern = request.getParameter("pattern");

        SearchList searchList = new SearchList();
        searchList.setKeyword(keyword);
        if(cates != null && !cates.equals(""))
            searchList.setCate(cates);
        if(pattern != null && !pattern.equals(""))
            searchList.setPattern(pattern);
        searchList.setPage(page);
        searchList.setSize(size);
        searchList.setUser_id((Integer) request.getAttribute("user_id"));
        if (seq != null && seq.equals("-add_time")) {
            searchList.setSeq("DESC");
        } else {
            searchList.setSeq("ASC");
        }
        return iImageInfoService.searchImage(searchList);
    }

    //获得不同状态的图片,测试完毕ok
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<ImageAndUserResult> getImagesByActive(HttpServletRequest request, HttpServletResponse response) {
        //假设用户id 为 1，这个地方的id可以通过token 获得
        int uid = Integer.parseInt(request.getAttribute("user_id").toString());
        int page = Integer.parseInt(request.getParameter("page"));
        if(!(page > 0)) page = 1;
        int size = Integer.parseInt(request.getParameter("num"));
        int if_active = Integer.parseInt(request.getParameter("if_active"));
        return iImageInfoService.getImagesByActive(uid, page, size, if_active);
    }

    //获得用户上传的图片
    @RequestMapping(value = "/user2", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserImages(HttpServletRequest request) {
        int uid = Integer.parseInt(request.getAttribute("user_id").toString());
        int page = Integer.parseInt(request.getParameter("page"));
        int size = 16;
        return iImageInfoService.getUserImages(uid, page, size);
    }

    //得到各种状态图片的个数
    @RequestMapping(value = "image_num", method = RequestMethod.GET)
    public List getActiveNum() {
        List<Integer> activeNumList = iImageInfoService.getActiveNum();
        return activeNumList;
    }

    //图片点赞或取消点赞
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseData like(ImageLike imageLike) {
        int image = imageLike.getImage();
        int user = imageLike.getUser();
        boolean tag = iImageLikeService.checkImageLiked(image, user);
        if (tag) {
            iImageLikeService.cancelImageLike(image, user);
            return ResponseData.ok();
        } else {
            iImageLikeService.imageLike(image, user);
            return ResponseData.ok();
        }

    }

    @GetMapping("/group/{group_id}")
    public Map getMainImage(HttpServletRequest request,
                            @PathVariable(name = "group_id", required = true) int group_id,
                            @RequestParam("page") int page, @RequestParam("num") int num) {
        Map<String, Object> m = new HashMap<>();
        int user_id = Integer.parseInt(request.getAttribute("user_id").toString());
        m.put("result", iImageInfoService.getMainImage(user_id, group_id, page, num));
        return m;
    }

    @GetMapping("{image_id}")
    public Map getImageInfo(HttpServletRequest request,
                            @PathVariable(name = "image_id", required = true) int image_id) {
        Map<String, Object> m = new HashMap<>();
        m.put("result", iImageInfoService.getImageInfo(image_id,
                Integer.parseInt(request.getAttribute("user_id").toString())));
        return m;
    }


}