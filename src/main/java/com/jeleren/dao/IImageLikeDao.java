package com.jeleren.dao;

import com.jeleren.bean.ImageLike;
import org.apache.ibatis.annotations.Param;

public interface IImageLikeDao {
    boolean imageLike(@Param("image") int image, @Param("user") int user);

    boolean cancelImageLike(@Param("image") int image, @Param("user") int user);

    ImageLike checkImageLiked(@Param("image") int image, @Param("user") int user);
}
