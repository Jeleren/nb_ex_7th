package com.jeleren.service.impl;

import com.github.pagehelper.PageHelper;
import com.jeleren.bean.ImageInfo;
import com.jeleren.bean.ImageResult;
import com.jeleren.bean.SearchList;
import com.jeleren.dao.IImageInfoDao;
import com.jeleren.service.IImageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: IImageInfoImpl <br/>
 * Description: <br/>
 * date: 2019/7/17 22:11<br/>
 *
 * @author a8243<br />
 * @since JDK 1.8
 */
@Service
public class ImageInfoServiceImpl implements IImageInfoService {

    @Autowired
    private IImageInfoDao iImageInfoDao;

    @Override
    public int add(ImageInfo imageInfo) {
        return iImageInfoDao.add(imageInfo);
    }

    @Override
    public List<ImageResult> getMainImage(int user_id, int group_id, int page, int num) {
        PageHelper.startPage(page, num);
        List<ImageResult> a = iImageInfoDao.getMainImage(user_id, group_id);
        return a;
    }

    @Override
    public ImageResult getImageInfo(int image_id, int user_id) {
        return iImageInfoDao.getImageInfo(image_id, user_id);
    }

    @Override
    public List<ImageInfo> searchImage(SearchList searchList){
        PageHelper.startPage(searchList.getPage(),searchList.getSize());
        return iImageInfoDao.searchImage(searchList);
    }
}
