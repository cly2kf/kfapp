package com.xxl.kfapp.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：XNN
 * 日期：2017/6/13
 * 作用：考试题目的item
 */

public class KSTMVo implements Serializable {
    private int num;
    private boolean wc = false;

    private String title;
    private List<KSNRVo> info;



    public List<KSNRVo> getInfo() {
        return info;
    }

    public void setInfo(List<KSNRVo> info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isWc() {
        return wc;
    }

    public void setWc(boolean wc) {
        this.wc = wc;
    }
}
