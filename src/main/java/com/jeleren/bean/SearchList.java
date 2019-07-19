package com.jeleren.bean;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: SearchList <br/>
 * Description: <br/>
 * date: 2019/7/19 14:59<br/>
 *
 * @author a8243<br />
 * @since JDK 1.8
 */
public class SearchList {
    private String keyword;
    private List<String> cateList;
    private String pattern;
    private String seq;
    private int page;
    private int size;

    public SearchList(){
        keyword="";
        pattern="";
        seq="ASC";
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<String> getCateList() {
        return cateList;
    }

    public void setCateList(List<String> cateList) {
        this.cateList = cateList;
    }


}
