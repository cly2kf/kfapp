package com.xxl.kfapp.model.response;

import java.io.Serializable;

/**
 * 作者：XNN
 * 日期：2017/6/13
 * 作用：考试题目的内容
 */

public class KSNRVo implements Serializable {
    private String info;
    private boolean xz = false;

    public boolean isXz() {
        return xz;
    }

    public void setXz(boolean xz) {
        this.xz = xz;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}
