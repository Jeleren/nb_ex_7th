package com.jeleren.dao;

import com.jeleren.bean.ImageInfo;
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

    //修改点赞数目
    boolean editImageLikeNum(@Param("image") int image, @Param("record") String record);
}
