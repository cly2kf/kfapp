package talex.zsw.baselibrary.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 作用: 自动回收的ImageView
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/7/21 15:02 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RecyclableImageView extends ImageView
{
	public RecyclableImageView(Context context)
	{
		super(context);
	}

	public RecyclableImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public RecyclableImageView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public RecyclableImageView(Context context, AttributeSet attrs, int defStyleAttr,
							   int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		setImageDrawable(null);
	}
}
