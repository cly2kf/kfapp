package talex.zsw.baselibrary.view.SwipeToLoadLayout.header;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import talex.zsw.baselibrary.R;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeRefreshTrigger;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeTrigger;
import talex.zsw.baselibrary.widget.GifView;


/**
 * Created by aspsine on 15/11/7.
 */
public class GifRefreshHeaderView extends FrameLayout
	implements SwipeTrigger, SwipeRefreshTrigger
{
	private GifView gifView;

	private int mTriggerOffset;

	private int mFinalOffset;

	public GifRefreshHeaderView(Context context)
	{
		this( context, null );
	}

	public GifRefreshHeaderView(Context context, AttributeSet attrs)
	{
		this( context, attrs, 0 );
	}

	public GifRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super( context, attrs, defStyleAttr );
		mTriggerOffset =
			context.getResources().getDimensionPixelOffset( R.dimen.refresh_trigger_offset_google );
		mFinalOffset =
			context.getResources().getDimensionPixelOffset( R.dimen.refresh_final_offset_google );
	}


	@Override protected void onFinishInflate()
	{
		super.onFinishInflate();
		gifView = (GifView) findViewById(R.id.gif);
		gifView.setPaused(true);
	}

	@Override public void onRefresh()
	{
		gifView.setPaused(false);
	}

	@Override public void onPrepare()
	{
		gifView.setPaused(true);
	}

	@Override public void onSwipe(int y, boolean isComplete)
	{
	}

	@Override public void onRelease()
	{

	}

	@Override public void complete()
	{
	}

	@Override public void onReset()
	{
	}
}
