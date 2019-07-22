package com.jeleren.service;

import com.jeleren.bean.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import java.util.Map;

/**
 * ClassName: IImageInfoService <br/>
 * Description: <br/>
 * date: 2019/7/17 22:11<br/>
 *
 * @author a8243<br />
 * @since JDK 1.8
 */
public interface IImageInfoService {
    void add(ImageInfo imageInfo);
    List<ImageResult> getMainImage(int user_id, int group_id, int page, int num);

    ImageResult getImageInfo(int image_id, int user_id);
    Map<String, Object> searchImage(SearchList searchList);
    Map<String, Object> getUserImages(int uid, int page, int size);
    List<ImageAndUserResult> getImagesByActive(int uid,int page,int size,int if_active);

    void updateImage(ImageInfo imageInfo);
    List<Integer> getActiveNum();
    List<CollectionInfo> getCollectionInfo(int uid);
    void deleteCollection(int collect_id,int uid);
    void createCollection(int uid, String collectName, Date date);
    void markImage(int uid ,int collect_id,int image_id);
}
