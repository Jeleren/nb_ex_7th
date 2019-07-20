package com.jeleren.dao;

import com.jeleren.bean.ImageInfo;
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
    List<ImageInfo> searchImage(@Param("sList") SearchList searchList);


    //修改点赞数目
    boolean editImageLikeNum(@Param("image") int image, @Param("record") String record);
    //获得用户上传的所有图片
     List<ImageInfo> getUserImages(int uid);
     // 根据状态获得图片
     List<ImageInfo>  getImagesByActive(@Param("uid") int uid,@Param("if_active") int if_active);
}
