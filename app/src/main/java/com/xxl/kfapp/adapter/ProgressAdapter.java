package com.xxl.kfapp.adapter;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xxl.kfapp.R;
import com.xxl.kfapp.model.response.ProgressVo;

import java.util.List;


/**
 * 作者：XNN
 * 日期：2017/3/31
 * 作用：进度条适配器
 */
public class ProgressAdapter extends BaseQuickAdapter<ProgressVo> {
    private int itemWidth;

    public ProgressAdapter(List<ProgressVo> data, int itemWidth) {
        super(R.layout.item_progress, data);
        this.itemWidth = itemWidth;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProgressVo vo) {
//        baseViewHolder.setOnClickListener(R.id.ly_item, new OnItemChildClickListener());
        ImageView imageView = baseViewHolder.getView(R.id.pImg);
        View line1 = baseViewHolder.getView(R.id.pLine1);
        View line2 = baseViewHolder.getView(R.id.pLine2);
        TextView name = baseViewHolder.getView(R.id.pText);
        name.setText(vo.getName());
        baseViewHolder.itemView.getLayoutParams().width = itemWidth;
        if (vo.getTag() == 1) {
            imageView.setBackgroundResource(R.drawable.bg_cecle_red);
            line1.setBackgroundResource(R.color.progress_green);
            name.setTextColor(Color.parseColor("#EA923A"));
        } else if (vo.getTag() == 2) {
            imageView.setBackgroundResource(R.drawable.bg_cecle_green);
            line1.setBackgroundResource(R.color.progress_green);
            line2.setBackgroundResource(R.color.progress_green);
            name.setTextColor(Color.parseColor("#54A530"));
        }


//        baseViewHolder.setText(R.id.count, vo.getQty() + "");
//        ImageUtil.loadImgFitCenter(imageView, vo.getPic());

    }

}
