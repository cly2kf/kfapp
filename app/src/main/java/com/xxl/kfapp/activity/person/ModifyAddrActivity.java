package com.xxl.kfapp.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xxl.kfapp.R;
import com.xxl.kfapp.base.BaseActivity;
import com.xxl.kfapp.utils.AddressPickTask;
import com.xxl.kfapp.widget.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;

public class ModifyAddrActivity extends BaseActivity {
    @Bind(R.id.mTitleBar)
    TitleBar mTitleBar;
    @Bind(R.id.lyt_addAddr)
    LinearLayout lytAdd;
    @Bind(R.id.RadioGroup)
    RadioGroup radioGroup;

    @Override
    protected void initArgs(Intent intent) {

    }

    @Override
    protected void initView(Bundle bundle) {
        setContentView(R.layout.activity_modify_addr);
        ButterKnife.bind(this);
        mTitleBar.setTitle("修改地址");
        mTitleBar.setBackOnclickListener(this);
        mTitleBar.setRightTV("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTitleBar.getvTvRight().setTextColor(getResources().getColor(R.color.white));
        lytAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton r1 = new RadioButton(ModifyAddrActivity.this);
                r1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                r1.setText("浙江省杭州市滨江区江南大道xxx号");
                r1.setTextColor(getResources().getColor(R.color.gray));
                r1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.arrow_right_gray, 0);
                radioGroup.addView(r1);
                onAddressPicker();
            }
        });

    }

    public void onAddressPicker() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showToast("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    showToast(province.getAreaName() + city.getAreaName());
                } else {
                    showToast(province.getAreaName() + city.getAreaName() + county.getAreaName());
                }
            }
        });
        task.execute("浙江", "杭州", "滨江");
    }

    @Override
    protected void initData() {

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
