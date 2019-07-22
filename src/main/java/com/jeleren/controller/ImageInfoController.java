package com.jeleren.controller;

import com.jeleren.bean.*;
import com.jeleren.service.IGroupService;
import com.jeleren.service.IImageInfoService;
import com.jeleren.service.IImageLikeService;
import com.jeleren.service.IUserInfoService;
import com.jeleren.utils.ResponseData;
import org.apache.commons.io.FilenameUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    //用户上传图片
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

    //更新图片信息
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

    //用户搜索图片
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
        } else
            page = 1;
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

    // 获得集合所有的信息 测试完成
    @RequestMapping(value = "/collect", method =RequestMethod.GET)
//    @ResponseBody
    public ResponseData getCollectionInfo(HttpServletRequest request){
        int uid;
        try {
            uid = Integer.parseInt(request.getAttribute("id").toString());
        } catch (Exception e){
            e.printStackTrace();
            uid = 5;  //设置为 5 便于测试，以后要改
        }

        List<CollectionInfo> collectionInfos = iImageInfoService.getCollectionInfo(uid);
        ResponseData result =  ResponseData.ok();
        result.putDataValue("collect",collectionInfos);
        return result;
    }

    // 删除收藏夹
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    @ResponseBody
    public ResponseData deleteCollectionInfo(HttpServletRequest request) {
        int uid, collect_id;
        try {
            uid = Integer.parseInt(request.getAttribute("id").toString());
        } catch (Exception e) {
            e.printStackTrace();
            uid = 5;  //设置为 5 便于测试，以后要改
        }
        try {
            collect_id = Integer.parseInt(request.getParameter("collect_id"));
            iImageInfoService.deleteCollection(collect_id, uid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("未输入正确的collect_id");
            return ResponseData.badRequest("未输入正确的collect_id");
        }
        return ResponseData.ok();
    }

    // 创建收藏夹
    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @ResponseBody
    public ResponseData createCollectionInfo(HttpServletRequest request) {
        int uid, collect_id;
        try {
            uid = Integer.parseInt(request.getAttribute("id").toString());
        } catch (Exception e) {
            e.printStackTrace();
            uid = 5;  //设置为 5 便于测试，以后要改
        }
        try {
            String collectName = request.getParameter("name");
            Date date = new Date();
            SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = temp.format(date);
            Date date3 = temp.parse(date2);
            iImageInfoService.createCollection(uid, collectName, date3);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData.customerError();
        }
        return ResponseData.ok();
    }

    // 收藏图片
    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    @ResponseBody
    public ResponseData markImage(HttpServletRequest request) {
        int uid, collect_id, image_id;
        try {
            uid = Integer.parseInt(request.getAttribute("id").toString());
        } catch (Exception e) {
            e.printStackTrace();
            uid = 5;  //设置为 5 便于测试，以后要改
        }
        try {
            collect_id = Integer.parseInt(request.getParameter("collect_id"));
            image_id = Integer.parseInt(request.getParameter("image_id"));
            iImageInfoService.markImage(uid,collect_id,image_id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseData.ok();
    }


    //根据收藏夹id获得收藏夹中所有图片
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public ResponseData getCollectionImageById(HttpServletRequest request) {
        //get方法得到page和num
        int collect_id = Integer.parseInt(request.getParameter("collect_id").toString());
        int page = Integer.parseInt(request.getParameter("page").toString());
        int num = Integer.parseInt(request.getParameter("num").toString());
        int uid;
        try {
            uid = Integer.parseInt(request.getAttribute("id").toString());
        } catch (Exception e){
            e.printStackTrace();
            uid = 5;  //设置为 5 便于测试，以后要改
        }
        CollectionInfo collectionInfos = iImageInfoService.getCollectionImageById(uid,collect_id,page,num);
        //获取图片数目
        int count = collectionInfos.getImageInfos().size();
        collectionInfos.setNum(count);
        //转换时间格式

        ResponseData result = ResponseData.ok();
        result.putDataValue("collectionInfo",collectionInfos);

        return result;
    }

//    取消收藏图片
    @RequestMapping(value = "/collect/delete_image", method = RequestMethod.GET)
    public ResponseData deleteImage(HttpServletRequest request){
        int collect_id = Integer.parseInt(request.getParameter("collect_id").toString());
        int image_id = Integer.parseInt(request.getParameter("image_id").toString());
        iImageInfoService.updateImageCollectNumDeleteOneById(image_id);
        iImageInfoService.deleteImageCollectById(collect_id,image_id);
        ResponseData result = ResponseData.ok();
        return result;
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