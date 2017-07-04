package com.xxl.kfapp.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.xxl.kfapp.R;

import talex.zsw.baselibrary.view.BasePopupWindow.BasePopupWindow;


/**
 * 从底部滑上来的popup
 */
public class SlideFromBottomPopup extends BasePopupWindow implements View.OnClickListener {

    private View popupView;
    private TextView tx1, tx2, tx3;


    public SlideFromBottomPopup(Activity context) {
        super(context);
        bindEvent();
    }

    @Override
    public Animation getShowAnimation() {
        return getTranslateAnimation(250 * 2, 0, 300);
    }

    @Override
    protected View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View getPopupView() {
        popupView = LayoutInflater.from(mContext).inflate(R.layout.popup_slide_from_bottom, null);
        return popupView;
    }

    @Override
    public View getAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView != null) {
            tx1 = (TextView) popupView.findViewById(R.id.tx_1);
            tx2 = (TextView) popupView.findViewById(R.id.tx_2);
            tx3 = (TextView) popupView.findViewById(R.id.tx_3);
            tx1.setOnClickListener(this);
            tx2.setOnClickListener(this);
            tx3.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(v);
    }

    public void setTexts(String[] strings) {
        tx1.setText(strings[0]);
        tx2.setText(strings[1]);
        tx3.setText(strings[2]);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View v);
    }
}
