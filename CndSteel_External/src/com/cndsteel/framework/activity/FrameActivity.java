package com.cndsteel.framework.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;

public class FrameActivity extends BaseActivity {
	
	/** 界面主框架 **/
	private RelativeLayout framework;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//只支持竖屏
		super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		super.setContentView(R.layout.framework);
		
		//初始化界面框架
		initViews();
	}

	private void initViews() {
		framework = (RelativeLayout)findViewById(R.id.framework);
		
		ImageButton imgBtn_topLeft = (ImageButton)framework.findViewById(R.id.imgBtn_topLeft);
		imgBtn_topLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FrameActivity.this.finish();
			}
		});
	}
	
	/**
	 * 隐藏顶部区
	 */
	protected void hideFrameworkTop() {
		FrameLayout framework_top = (FrameLayout)framework.findViewById(R.id.framework_top);
		framework_top.setVisibility(View.GONE);
	}
	
	/**
	 * 设置标题
	 */
	protected void setTopBarTitle(int titleResId){
		TextView txtV_topCenter = (TextView)framework.findViewById(R.id.txtV_topCenter);
		txtV_topCenter.setText(getContext().getResources().getString(titleResId));
	}
	
	
	/**
	 * 显示顶部区域
	 */
	protected void showFrameworkTop(){
		
	}
	
	/**
	 * 为中央区添加内容
	 */
	protected void appendFrameworkCenter(int layoutResId) {
		FrameLayout framework_center = (FrameLayout) framework.findViewById(R.id.framework_center);
		View _view = inflateView(layoutResId);

		FrameLayout.LayoutParams _layoutParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);

		framework_center.addView(_view, _layoutParams);
	}
	
}
