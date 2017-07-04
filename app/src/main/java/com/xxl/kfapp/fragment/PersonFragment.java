package com.xxl.kfapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.xxl.kfapp.R;
import com.xxl.kfapp.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.view.PullZoomView.PullToZoomScrollViewEx;

/**
 * 作者：XNN
 * 日期：2017/6/1
 * 作用：我的
 */

public class PersonFragment extends BaseFragment {
    @Bind(R.id.mScrollView)
    PullToZoomScrollViewEx mScrollView;

    @Override
    protected void initArgs(Bundle bundle) {

    }

    @Override
    protected void initView(Bundle bundle) {
        setContentView(R.layout.fragment_person);
        ButterKnife.bind(this, mView);

        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.personal_head_view, null, false);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.personal_zoom_view, null, false);
        View contentView =
                LayoutInflater.from(getActivity()).inflate(R.layout.personal_content_view, null, false);
        mScrollView.setHeaderView(headView);
        mScrollView.setZoomView(zoomView);
        mScrollView.setScrollContentView(contentView);

    }

    @Override
    protected void initData() {

    }
}
