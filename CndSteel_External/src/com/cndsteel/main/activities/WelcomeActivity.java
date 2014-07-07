package com.cndsteel.main.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.views.layout.HorizontalScrollLayout;
import com.cndsteel.framework.views.layout.HorizontalScrollLayout.OnViewChangeListener;
import com.cndsteel.settings.beans.SettingsBean;

public class WelcomeActivity extends FrameActivity implements OnViewChangeListener,OnClickListener {
	
	private int mHorizontalScrollLayoutChildCount;
	private int mCurrentDisplayedViewIndex;
	
	private HorizontalScrollLayout lyot_horizontalScroll;
	
	private LinearLayout lyot_pageIndicator;
	private ImageView[] mPageIndicators;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendFrameworkCenter(R.layout.activity_welcome);
		hideFrameworkTop();
		
		init();
	}
	
	private void init(){
		initVariables();
		initViews();
	}
	
	private void initVariables(){
		mCurrentDisplayedViewIndex = 0;
	}
	
	private void initViews(){
		lyot_horizontalScroll = (HorizontalScrollLayout)findViewById(R.id.lyot_horizontalScroll);
		lyot_horizontalScroll.setOnViewChangeListener(this);
		mHorizontalScrollLayoutChildCount = lyot_horizontalScroll.getChildCount();
		
		lyot_pageIndicator = (LinearLayout)findViewById(R.id.lyot_pageIndicator);
		
		mPageIndicators = new ImageView[mHorizontalScrollLayoutChildCount];
		for(int i = 0; i < mHorizontalScrollLayoutChildCount; i ++){
			mPageIndicators[i] = (ImageView)lyot_pageIndicator.getChildAt(i);
			mPageIndicators[i].setEnabled(false);
			mPageIndicators[i].setTag(i);
		}
		mPageIndicators[mCurrentDisplayedViewIndex].setEnabled(true);
		
		((Button)findViewById(R.id.btn_startPlay)).setOnClickListener(this);
	}

	@Override
	public void OnViewChange(int viewIndex) {
		if(viewIndex == (mHorizontalScrollLayoutChildCount - 1)){
			lyot_pageIndicator.setVisibility(View.INVISIBLE);
		}else {
			lyot_pageIndicator.setVisibility(View.VISIBLE);
			
			mCurrentDisplayedViewIndex = viewIndex;
			setEnablePageIndicator();
		}
	}
	
	private void setEnablePageIndicator(){
		for(int i = 0; i < mPageIndicators.length; i ++){
			mPageIndicators[i].setEnabled(false);
		}
		
		mPageIndicators[mCurrentDisplayedViewIndex].setEnabled(true);
	}

	@Override
	public void onClick(View view) {
		SettingsBean.getInstance().putSettingValue(Constants.SETTINGS_PARAM_FIRSTLOADING, Constants.FALSE);
		SettingsBean.save();
		
		startActivity(LoginActivity.class);
		finish();
	}
}
