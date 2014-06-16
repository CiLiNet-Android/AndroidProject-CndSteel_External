package com.cndsteel.framework.views.pullToRefreshListView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.cndsteel.R;

public class PullToRefreshListView extends ListView implements OnScrollListener {
	
	private PullToRefreshListViewHeader mHeaderView = null;
	private RelativeLayout mHeaderContent = null;
	private int iHeaderHeight = 0;

	private PullToRefreshListViewFooter mFooterView = null;
	private RelativeLayout mFooterContent = null;
	private int iFooterHeight = 0;
	
	private final static int SCROLL_HEADER = 0;
	private final static int SCROLL_FOOTER = 1;
	private int iScrollWhich = SCROLL_HEADER;
	
	private Scroller mScroller = null;
	private final static float OFFSET_Y = 0.7f;
	private float iLastY = 0;
	private int mTotalNumber = 0;
	
	private static final int SCROLLING_RATIO = 3;
	
	public PullToRefreshListView(Context context) {
		this(context, null, 0);
	}
	public PullToRefreshListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public PullToRefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	private void initView(Context context){
		/*
		 * mScroller用来回弹下拉刷新/上拉更多
		 * 配合computerScroll来使用
		 */
		mScroller = new Scroller(context, new DecelerateInterpolator());
		super.setOnScrollListener(this);
		
		initHeaderView(context);
		initFooterView(context);
	}
	
	@Override
	public void setAdapter(ListAdapter adapter) {
		if(getFooterViewsCount() == 0){
			addFooterView(mFooterView);
		}
		super.setAdapter(adapter);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			iLastY = event.getY();
			break;
			
		case MotionEvent.ACTION_MOVE:
			float deltaY = event.getY() - iLastY;
			iLastY = event.getY();
			if(canHeaderPull() && getFirstVisiblePosition() == 0 &&
			   (deltaY > 0 || mHeaderView.getHeaderHeight() > 0)){
				updateHeaderState(deltaY * OFFSET_Y);
			}else if(canFooterPull() && getLastVisiblePosition() == mTotalNumber - 1
					&& (deltaY < 0 || mFooterView.getFooterHeight() > 0)){
				updateFooterState(-deltaY * OFFSET_Y);
			}
			break;
			
		case MotionEvent.ACTION_UP:
			if(getFirstVisiblePosition() == 0){
				if(mHeaderView.getHeaderHeight() > iHeaderHeight){
				   mHeaderView.setHeaderState(PullToRefreshListViewHeader.STATE_REFRESHING);
				   if(mFooterView.getFooterViewOptions() == PullToRefreshListViewFooter.FOOTER_OPTIONS_CLICK){
						mFooterView.hide();
					}
				}
				resetHeader();
			}else if(getLastVisiblePosition() == mTotalNumber - 1){
				if(mFooterView.getFooterHeight() > iFooterHeight){
					mFooterView.setFooterState(PullToRefreshListViewFooter.STATE_LOADING);
				}
				resetFooter();
			}
			break;
		
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void computeScroll() {
		if(mScroller.computeScrollOffset()){
			if(iScrollWhich == SCROLL_HEADER){
				mHeaderView.setHeaderHeight(mScroller.getCurrY());
			}else if(iScrollWhich == SCROLL_FOOTER){
				mFooterView.setFooterHeight(mScroller.getCurrY());
			}
		}
		super.computeScroll();
	}
	
	/*
	 * 获取ListView有多少个item:
	 * 1. 在init中，需要设置super.setOnScrollListener;
	 * 2. 重载以下两个函数;
	 * 3. 在onScroll中取得totalItemCount即可;
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		mTotalNumber = totalItemCount;
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}
	/////////////////////////////////////////////////////////////////////////////
	private boolean canHeaderPull(){
		if(mFooterView.getCurrentState() == PullToRefreshListViewFooter.STATE_NORMAL){
			return true;
		}
		return false;
	}
	
	private boolean canFooterPull(){
		if(mHeaderView.getCurrentState() == PullToRefreshListViewHeader.STATE_NORMAL){
			return true;
		}
		return false;
	}
	///////////////////////////////////// Header ////////////////////////////////
	public void stopRefresh(){
		if(mHeaderView.getCurrentState() == PullToRefreshListViewHeader.STATE_REFRESHING){
			mHeaderView.setHeaderState(PullToRefreshListViewHeader.STATE_NORMAL);
			resetHeader();
			if(mFooterView.getFooterViewOptions() == PullToRefreshListViewFooter.FOOTER_OPTIONS_CLICK){
				mFooterView.show();
			}
		}
	}
	
	private void initHeaderView(Context context){
		mHeaderView = new PullToRefreshListViewHeader(context);
		mHeaderContent = (RelativeLayout) mHeaderView.findViewById(R.id.header_content);
		addHeaderView(mHeaderView);
		
		//获得实际HeaderView高度
		mHeaderView.getViewTreeObserver()
				   .addOnGlobalLayoutListener(new OnGlobalLayoutListener(){
			@Override
			public void onGlobalLayout() {
				iHeaderHeight = mHeaderContent.getHeight();
				getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
	}
	
	private void updateHeaderState(float delta){
		mHeaderView.setHeaderHeight((int)((delta / SCROLLING_RATIO) + mHeaderView.getHeaderHeight()));
		if(mHeaderView.getCurrentState() != PullToRefreshListViewHeader.STATE_REFRESHING){
			if(mHeaderView.getHeaderHeight() > iHeaderHeight){
				mHeaderView.setHeaderState(PullToRefreshListViewHeader.STATE_WILL_RELEASE);
			}else{
				mHeaderView.setHeaderState(PullToRefreshListViewHeader.STATE_NORMAL);
			}
		}
		setSelection(0);
	}
	
	private void resetHeader(){
		int height = mHeaderView.getHeaderHeight();
		if(height == 0){
			return;
		}

		int finalHeight = 0;
		if(height > iHeaderHeight){
			/*
			 * 如果超过HeaderView高度，则回滚到HeaderView高度即可
			 */
			finalHeight = iHeaderHeight;
		}else if(mHeaderView.getCurrentState() == PullToRefreshListViewHeader.STATE_REFRESHING){
			/*
			 * 如果HeaderView未完全显示
			 * 1. 处于正在刷新中，则不管;
			 * 2. 回滚HeaderView当前可视高度
			 */
			return;
		}
		
		iScrollWhich = SCROLL_HEADER;
		mScroller.startScroll(0, height, 0, finalHeight - height, 250);
		invalidate();
	}
	/////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////// Footer ////////////////////////////////
	public void setFooterMode(int options){
		mFooterView.setFooterViewOptions(options);
	}
	
	/**
	 * 停止加载
	 */
	public void stopLoad(){
		if(mFooterView.getCurrentState() == PullToRefreshListViewFooter.STATE_LOADING){
			mFooterView.setFooterState(PullToRefreshListViewFooter.STATE_NORMAL);
			resetFooter();
		}
	}
	
	private void initFooterView(Context context){
		mFooterView = new PullToRefreshListViewFooter(context);
		mFooterContent = (RelativeLayout) mFooterView.findViewById(R.id.footer_content);
		mFooterContent.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(mFooterView.getFooterViewOptions() == PullToRefreshListViewFooter.FOOTER_OPTIONS_CLICK
						&& mFooterView.getCurrentState() == PullToRefreshListViewFooter.STATE_NORMAL){
					mFooterView.setFooterState(PullToRefreshListViewFooter.STATE_LOADING);
				}
			}
		});
		mFooterView.getViewTreeObserver()
				   .addOnGlobalLayoutListener(new OnGlobalLayoutListener(){
			@Override
			public void onGlobalLayout() {
				iFooterHeight = mFooterContent.getHeight();
				getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
	}
	
	private void updateFooterState(float delta){
		if(mFooterView.getFooterViewOptions() == PullToRefreshListViewFooter.FOOTER_OPTIONS_CLICK){
			return;
		}
		
		mFooterView.setFooterHeight((int)(delta + mFooterView.getFooterHeight()));
		if(mFooterView.getCurrentState() != PullToRefreshListViewFooter.STATE_LOADING){
			if(mFooterView.getFooterHeight() > iFooterHeight){
				mFooterView.setFooterState(PullToRefreshListViewFooter.STATE_WILL_RELEASE);
			}else{
				mFooterView.setFooterState(PullToRefreshListViewFooter.STATE_NORMAL);
			}
		}
	}
	
	private void resetFooter(){
		int height = mFooterView.getFooterHeight();
		if(height == 0){
			return;
		}
		
		if(mFooterView.getFooterViewOptions() == PullToRefreshListViewFooter.FOOTER_OPTIONS_CLICK){
			return;
		}
		
		int finalHeight = 0;
		if(height > iFooterHeight){
			finalHeight = iFooterHeight;
		}else if(mFooterView.getCurrentState() == PullToRefreshListViewFooter.STATE_LOADING){
			return;
		}

		iScrollWhich = SCROLL_FOOTER;
		mScroller.startScroll(0, height, 0, finalHeight - height, 250);
		invalidate();
	}
	
}
