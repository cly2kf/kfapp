package com.xxl.kfapp.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xxl.kfapp.R;
import com.xxl.kfapp.base.BaseActivity;
import com.xxl.kfapp.fragment.ForumFragment;
import com.xxl.kfapp.fragment.HomeFragment;
import com.xxl.kfapp.fragment.PersonFragment;
import com.xxl.kfapp.fragment.SPcartFragment;
import com.xxl.kfapp.fragment.StoreFragment;
import com.xxl.kfapp.widget.IconText;
import com.xxl.kfapp.widget.TitleBar;
import com.xxl.kfapp.zxing.CaptureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.mContent)
    FrameLayout mContent;
    @Bind(R.id.mIconText1)
    IconText mIconText1;
    @Bind(R.id.mIconText2)
    IconText mIconText2;
    @Bind(R.id.mIconText3)
    IconText mIconText3;
    @Bind(R.id.mIconText4)
    IconText mIconText4;
    @Bind(R.id.mIconText5)
    IconText mIconText5;
    @Bind(R.id.mLLBottom)
    LinearLayout mLLBottom;
    @Bind(R.id.mTitleBar)
    TitleBar mTitleBar;

    private int selectId = R.id.mIconText1;

    private FragmentManager mFragmentManager;
    private HomeFragment homeFragment;
    private StoreFragment storeFragment;
    private SPcartFragment sPcartFragment;
    private ForumFragment forumFragment;
    private PersonFragment personFragment;


    @Override
    protected void initArgs(Intent intent) {

    }

    @Override
    protected void initView(Bundle bundle) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mIconText1.setOnClickListener(this);
        mIconText2.setOnClickListener(this);
        mIconText3.setOnClickListener(this);
        mIconText4.setOnClickListener(this);
        mIconText5.setOnClickListener(this);
        mTitleBar.setTitle("首页");
        mTitleBar.setLeftCode(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CaptureActivity.class));

            }
        });
        mTitleBar.setRightIV(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void initData() {
        setIndexFragment(1);//设置首页

    }

    @Override
    public void onClick(View v) {

        if (isFastClick()) {
            return;
        }
        if (selectId == v.getId()) {
            return;
        }
//        if (v.getId() == R.id.mIconText4 && StringUtils.isBlank(SpUtil.getUserId()))
//        {
//            showToast("请先登录");
//            start(LoginActivity.class);
//            return;
//        }
        selectId = v.getId();
        cleanSelect();
        ((IconText) v).setSelected(true);

        switch (v.getId()) {
            case R.id.mIconText1:
                mTitleBar.setTitle("首页");
                setIndexFragment(1);
                break;
            case R.id.mIconText2:
                mTitleBar.setTitle("商城");
                setIndexFragment(2);
                break;
            case R.id.mIconText3:
                mTitleBar.setTitle("购物车");
                setIndexFragment(3);
                break;
            case R.id.mIconText4:
                mTitleBar.setTitle("论坛");
                setIndexFragment(4);
                break;
            case R.id.mIconText5:
                mTitleBar.setTitle("我的");
                setIndexFragment(5);
                break;

        }
    }

    /**
     * 设置显示第几个fragment
     */
    private void setIndexFragment(int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();//开启事务
        hideFragments(transaction);
        switch (position) {
            case 1:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.mContent, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 2:
                if (storeFragment == null) {
                    storeFragment = new StoreFragment();
                    transaction.add(R.id.mContent, storeFragment);
                } else {
                    transaction.show(storeFragment);
                }
                break;
            case 3:
                if (sPcartFragment == null) {
                    sPcartFragment = new SPcartFragment();
                    transaction.add(R.id.mContent, sPcartFragment);
                } else {
                    transaction.show(sPcartFragment);
                }
                break;
            case 4:
                if (forumFragment == null) {
                    forumFragment = new ForumFragment();
                    transaction.add(R.id.mContent, forumFragment);
                } else {
                    transaction.show(forumFragment);
                }
                break;
            case 5:
                if (personFragment == null) {
                    personFragment = new PersonFragment();
                    transaction.add(R.id.mContent, personFragment);
                } else {
                    transaction.show(personFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void cleanSelect() {
        mIconText1.setSelected(false);
        mIconText2.setSelected(false);
        mIconText3.setSelected(false);
        mIconText4.setSelected(false);
        mIconText5.setSelected(false);
    }

    /**
     * 隐藏所有的fragment
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (storeFragment != null) {
            transaction.hide(storeFragment);
        }
        if (sPcartFragment != null) {
            transaction.hide(sPcartFragment);
        }
        if (forumFragment != null) {
            transaction.hide(forumFragment);
        }
        if (personFragment != null) {
            transaction.hide(personFragment);
        }
    }

    /**
     * 点两次返回退出程序
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                getAppApplication().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
