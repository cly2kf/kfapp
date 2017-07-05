package com.xxl.kfapp.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xxl.kfapp.R;


public class TitleBar extends RelativeLayout {
    private TextView tvLeft;
    private ImageView vIvLeftCode;
    private ImageView vIvLeft;
    private ImageView vIvCenter;
    private ImageView vIvRight2;
    private ImageView vIvRight;
    private TextView vTvLeft;
    private TextView vTvTitle;
    private TextView vTvRight;
    private RelativeLayout layout;

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleBar(Context context) {
        super(context);
        initView(context);
    }

    private void initView(final Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.widget_titlebar, this);

        tvLeft = (TextView) rootView.findViewById(R.id.tvLeft);
        vIvLeftCode = (ImageView) rootView.findViewById(R.id.vIvLeftCode);
        vIvLeft = (ImageView) rootView.findViewById(R.id.vIvLeft);
        vIvCenter = (ImageView) rootView.findViewById(R.id.vIvCenter);
        vIvRight2 = (ImageView) rootView.findViewById(R.id.vIvRight2);
        vIvRight = (ImageView) rootView.findViewById(R.id.vIvRight);
        vTvLeft = (TextView) rootView.findViewById(R.id.vTvLeft);
        vTvTitle = (TextView) rootView.findViewById(R.id.vTvTitle);
        vTvRight = (TextView) rootView.findViewById(R.id.vTvRight);
        layout = (RelativeLayout) rootView.findViewById(R.id.layout);

        tvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
    }

    /**
     * 设置标题
     */
    public void setTitle(String string) {
        vTvTitle.setText(string);
    }

    /**
     * 设置标题
     */
    public void setTitle(int resid) {
        vTvTitle.setText(resid);
    }

    public void setBackOnclickListener(final Activity activity) {
        vIvLeft.setVisibility(VISIBLE);
        vIvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    /**
     * 设置左侧返回按钮点击事件
     */
    public void setBackOnclickListener2(OnClickListener listener) {
        tvLeft.setOnClickListener(listener);
    }


    /**
     * 隐藏左侧返回按钮
     */
    public void hideBackButton() {
        tvLeft.setVisibility(View.GONE);
    }

    /**
     * 设置右边按钮点击事件
     */
    public void setRightIV(int resid, OnClickListener listener) {
        vIvRight.setVisibility(View.VISIBLE);
        vIvRight.setImageResource(resid);
        vIvRight.setOnClickListener(listener);
    }

    public void setRightIV(OnClickListener listener) {
        vIvRight.setVisibility(View.VISIBLE);
        vIvRight.setOnClickListener(listener);
    }

    /**
     * 设置右边按钮点击事件
     */
    public void setRightIV2(int resid, OnClickListener listener) {
        vIvRight2.setVisibility(View.VISIBLE);
        vIvRight2.setImageResource(resid);
        vIvRight2.setOnClickListener(listener);
    }

    /**
     * 设置中间按钮点击事件
     */
    public void setCenterIV(int resid, OnClickListener listener) {
        vIvCenter.setVisibility(View.VISIBLE);
        vIvCenter.setImageResource(resid);
        vIvCenter.setOnClickListener(listener);
    }


    /**
     * 设置右边文字和点击事件
     */
    public void setRightTV(String string, OnClickListener listener) {
        vTvRight.setVisibility(View.VISIBLE);
        vTvRight.setText(string);
        vTvRight.setOnClickListener(listener);
    }

    /**
     * 设置右边文字和点击事件
     */
    public void setRightTV(int string, OnClickListener listener) {
        vTvRight.setVisibility(View.VISIBLE);
        vTvRight.setText(string);
        vTvRight.setOnClickListener(listener);
    }

    /**
     * 设置左边文字和点击事件
     */
    public void setLeftTV(String string, OnClickListener listener) {
        tvLeft.setVisibility(View.GONE);
        vTvLeft.setVisibility(View.VISIBLE);
        vTvLeft.setText(string);
        vTvLeft.setOnClickListener(listener);
    }

    /**
     * 设置左边文字和点击事件
     */
    public void setLeftTV(int string, OnClickListener listener) {
        tvLeft.setVisibility(View.GONE);
        vTvLeft.setVisibility(View.VISIBLE);
        vTvLeft.setText(string);
        vTvLeft.setOnClickListener(listener);
    }

    /**
     * 设置左边文字和点击事件
     */
    public void setLeftCode(OnClickListener listener) {
        tvLeft.setVisibility(View.GONE);
        vIvLeftCode.setVisibility(View.VISIBLE);
        vIvLeftCode.setOnClickListener(listener);
    }

    public void setBackgroundColor(int color) {
        layout.setBackgroundColor(color);
    }

    public TextView getTvLeft() {
        return tvLeft;
    }

    public ImageView getvIvLeftCode() {
        return vIvLeftCode;
    }

    public ImageView getvIvLeft() {
        return vIvLeft;
    }

    public ImageView getvIvCenter() {
        return vIvCenter;
    }

    public ImageView getvIvRight2() {
        return vIvRight2;
    }

    public ImageView getvIvRight() {
        return vIvRight;
    }

    public TextView getvTvLeft() {
        return vTvLeft;
    }

    public TextView getvTvTitle() {
        return vTvTitle;
    }

    public TextView getvTvRight() {
        return vTvRight;
    }
}
