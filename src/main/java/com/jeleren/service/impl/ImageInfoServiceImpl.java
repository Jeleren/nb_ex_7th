package com.jeleren.service.impl;

import com.github.pagehelper.PageHelper;
import com.jeleren.bean.ImageInfo;
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
 * @author a8243<br   />
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
    public List<ImageInfo> searchImage(SearchList searchList) {
        PageHelper.startPage(searchList.getPage(), searchList.getSize());
        return iImageInfoDao.searchImage(searchList);
    }

    @Override
    public List<ImageInfo> getUserImages(int uid,int page,int size) {
        PageHelper.startPage(page,size);
        return iImageInfoDao.getUserImages(uid);
    }

    @Override
    public List<ImageInfo> getImagesByActive(int uid, int page, int size, int if_active) {
//        star
        List<ImageInfo> imageInfos = iImageInfoDao.getImagesByActive(uid, if_active);
        PageHelper.startPage(page,size);
        return  imageInfos;
    }
}
