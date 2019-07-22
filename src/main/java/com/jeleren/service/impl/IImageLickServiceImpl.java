package com.jeleren.service.impl;

import com.jeleren.bean.ImageLike;
import com.jeleren.dao.IImageInfoDao;
import com.jeleren.dao.IImageLikeDao;
import com.jeleren.service.IImageLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IImageLickServiceImpl implements IImageLikeService {

    @Autowired
    private IImageLikeDao iImageLikeDao;

    @Autowired
    private IImageInfoDao iImageInfoDao;

    @Override
    public boolean checkImageLiked(int image, int user) {

        ImageLike like = iImageLikeDao.checkImageLiked(image, user);
        System.out.println(like);
        if(like != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean imageLike(int image, int user) {
        return iImageLikeDao.imageLike(image, user) && iImageInfoDao.editImageLikeNum(image, "add");

    }

    @Override
    public boolean cancelImageLike(int image, int user) {

        return iImageLikeDao.cancelImageLike(image, user) && iImageInfoDao.editImageLikeNum(image, "delete");
    }
}
