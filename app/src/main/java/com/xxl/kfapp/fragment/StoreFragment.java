package com.xxl.kfapp.fragment;

import android.os.Bundle;

import com.xxl.kfapp.R;
import com.xxl.kfapp.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * 作者：XNN
 * 日期：2017/6/1
 * 作用：商城
 */

public class StoreFragment extends BaseFragment {
    @Override
    protected void initArgs(Bundle bundle) {

    }

    @Override
    protected void initView(Bundle bundle) {
        setContentView(R.layout.fragment_store);
        ButterKnife.bind(this, mView);

    }

    @Override
    protected void initData() {

    }
}
