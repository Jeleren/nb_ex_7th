package com.jeleren.service.impl;

import com.github.pagehelper.PageHelper;
import com.jeleren.bean.*;
import com.jeleren.dao.IImageInfoDao;
import com.jeleren.service.IImageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ClassName: IImageInfoImpl <br/>
 * Description: <br/>
 * date: 2019/7/17 22:11<br/>
 *
 * @author a8243<br       />
 * @since JDK 1.8
 */
@Service
public class ImageInfoServiceImpl implements IImageInfoService {

    @Autowired
    private IImageInfoDao iImageInfoDao;

    @Override
    public void add(ImageInfo imageInfo) {
        iImageInfoDao.add(imageInfo);
    }

    @Override
    public List<ImageResult> getMainImage(int user_id, int group_id, int page, int num) {
        PageHelper.startPage(page, num);
        return iImageInfoDao.getMainImage(user_id, group_id);
    }

    @Override
    public ImageResult getImageInfo(int image_id, int user_id) {
        return iImageInfoDao.getImageInfo(image_id, user_id);
    }

    @Override
    public Map<String, Object> searchImage(SearchList searchList) {
        List<ImageResult> imageResults = iImageInfoDao.searchImage(searchList);
        int count = imageResults.size();
        Map<String, Object> m = new HashMap<>();
        m.put("result", imageResults);
        m.put("count", count);
        PageHelper.startPage(searchList.getPage(), searchList.getSize());
        return m;
    }

    @Override
    public Map<String, Object> getUserImages(int uid, int page, int size) {
        System.out.println(uid);
        List<ImageResult> imageResults = iImageInfoDao.getUserImages(uid);
        int count = imageResults.size();
        Map<String, Object> m = new HashMap<>();
        m.put("result", imageResults);
        m.put("count", count);
        PageHelper.startPage(page,size);
        return m;
    }

    @Override
    public List<ImageAndUserResult> getImagesByActive(int uid, int page, int size, int if_active) {
//        star
        List<ImageAndUserResult> imageInfos = iImageInfoDao.getImagesByActive(uid, if_active);
        // 将图片数目放到结构中去，图片数目每个结果都保存一份
        int count;
        if (imageInfos.size() > 0) {
            count = imageInfos.get(0).getImageInfos().size();
            for (int i = 0; i < count; i++) {
                imageInfos.get(i).setCount(count);
            }
        }

        PageHelper.startPage(page, size);
        return imageInfos;
    }

    @Override
    public void updateImage(ImageInfo imageInfo) {
        iImageInfoDao.updateImage(imageInfo);
    }



    @Override
    public List<Integer> getActiveNum() {
        int tmp1 = iImageInfoDao.getActiveNum(1);
        int tmp2 = iImageInfoDao.getActiveNum(2);
        int tmp3 = iImageInfoDao.getActiveNum(3);
        int tmp4 = iImageInfoDao.getActiveNum(4);
        List<Integer> listResult = new ArrayList<>();
        listResult.add(tmp1);
        listResult.add(tmp2);
        listResult.add(tmp3);
        listResult.add(tmp4);


        return listResult;
    }

    @Override
    public List<CollectionInfo> getCollectionInfo(int uid) {
        List<CollectionInfo> collectionInfos = iImageInfoDao.getCollectionInfo(uid);
        for (int i = 0; i < collectionInfos.size(); i++) {
            int num = collectionInfos.get(i).getImageInfos().size();
            List<ImageInfo> imageInfos = collectionInfos.get(i).getImageInfos();
            if (num > 6) {
                imageInfos = imageInfos.subList(0,6);
            }
            collectionInfos.get(i).setNum(num);
        }
        return collectionInfos;
    }

    @Override
    public void deleteCollection(int collect_id,int uid) {
        iImageInfoDao.deleteCollection(collect_id,uid);
    }

    @Override
    public void createCollection(int uid, String collectName, Date date) {
        iImageInfoDao.createCollection(uid,collectName,date);
    }

    @Override
    public void markImage(int uid, int collect_id, int image_id) {
        iImageInfoDao.markImage_insert(uid,collect_id,image_id);
        iImageInfoDao.markImage_update(uid,collect_id,image_id);
    }

    @Override
    public CollectionInfo getCollectionImageById(int uid, int collect_id, int page, int num) {
        CollectionInfo collectionInfo = iImageInfoDao.getCollectionImageById(collect_id,uid);
        PageHelper.startPage(page, num);
        return collectionInfo;
    }


}
