package com.xxl.kfapp.model.response;

import java.io.Serializable;

/**
 * 作者：XNN
 * 日期：2017/6/13
 * 作用：用于接收进度条数据
 */

public class ProgressVo implements Serializable {
    private String name;
    private int tag = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
