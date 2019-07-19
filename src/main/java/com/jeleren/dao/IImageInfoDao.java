package com.jeleren.dao;

import com.jeleren.bean.ImageInfo;
import com.jeleren.bean.SearchList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

}
