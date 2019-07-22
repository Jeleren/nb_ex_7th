package com.jeleren.dao;

import com.jeleren.bean.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    int add(ImageInfo imageInfo);

    List<ImageResult> getMainImage(@Param("user_id") int user_id, @Param("group_id") int group_id);

    ImageResult getImageInfo(@Param("image_id") int image_id, @Param("user_id") int user_id);

    //    List<ImageInfo> searchImage(@Param("sList") SearchList searchList);

    List<ImageResult> searchImage(@Param("sList") SearchList searchList);


    //修改点赞数目
    boolean editImageLikeNum(@Param("image") int image, @Param("record") String record);
    //获得用户上传的所有图片
    List<ImageResult> getUserImages(int uid);

    // 根据状态获得图片
    List<ImageAndUserResult> getImagesByActive(@Param("uid") int uid, @Param("if_active") int if_active);

    // 获得不同状态图片的数目
    int getActiveNum(int active_id);

    // 获得集合的所有信息
    List<CollectionInfo> getCollectionInfo(int uid);

    //根据collect_id查询所有图片
    CollectionInfo getCollectionImageById(@Param("collect_id") int collect_id,@Param("uid") int uid);

     void updateImage(@Param("imageInfo") ImageInfo imageInfo);

    void deleteCollection(@Param("collect_id") int collect_id, @Param("uid") int uid);

    void createCollection(@Param("uid") int uid, @Param("collectName") String collectName,
                          @Param("create_date") Date date);

    void markImage_insert(@Param("uid") int uid, @Param("collect_id") int collect_id, @Param("image_id") int image_id);
    void markImage_update(@Param("uid") int uid, @Param("collect_id") int collect_id, @Param("image_id") int image_id);
}
