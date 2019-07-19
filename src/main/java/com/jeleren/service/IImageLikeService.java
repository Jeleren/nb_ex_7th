package com.jeleren.service;

public interface IImageLikeService {
    boolean checkImageLiked(int image, int user);

    boolean imageLike(int image, int user);

    boolean cancelImageLike(int image, int user);
}
