package com.jeleren.dao;

import com.jeleren.bean.ImageAndUserResult;
import com.jeleren.bean.ImageInfo;
import com.jeleren.bean.ImageResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import com.jeleren.bean.SearchList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: IImageInfoDao <br/>
 * Description: <br/>
 * date: 2019/7/17 22:08<br/>
 *
 * @author a8243<br />
 * @since JDK 1.8
 */
public interface IImageInfoDao {
    void add(ImageInfo imageInfo);

    List<ImageResult> getMainImage(@Param("user_id") int user_id, @Param("group_id") int group_id);

    ImageResult getImageInfo(@Param("image_id") int image_id, @Param("user_id") int user_id);

    List<ImageResult> searchImage(@Param("sList") SearchList searchList);


    //修改点赞数目
    boolean editImageLikeNum(@Param("image") int image, @Param("record") String record);
    //获得用户上传的所有图片
     List<ImageResult> getUserImages(int uid);
     // 根据状态获得图片
     List<ImageAndUserResult>  getImagesByActive(@Param("uid") int uid, @Param("if_active") int if_active);

     void updateImage(@Param("imageInfo") ImageInfo imageInfo);

     int getActiveNum(int active_id);
}
