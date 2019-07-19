package com.jeleren.service;

import com.jeleren.bean.ImageInfo;
import com.jeleren.bean.SearchList;

import java.util.List;

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
    List<ImageInfo> searchImage(SearchList searchList);
}
