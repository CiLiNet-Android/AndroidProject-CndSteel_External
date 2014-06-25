package com.cndsteel.main.activities;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.animation.Rotate3dAnimation;
import com.cndsteel.main.adapters.APPModulesVPagerAdapter;
import com.cndsteel.main.beans.APPModuleBean;
import com.cndsteel.settings.activities.SettingsActivity;

/**
 * 主页
 */
public class MainActivity extends FrameActivity implements ViewPager.OnPageChangeListener,View.OnClickListener {
	
	/** 模块Grid **/
	private ViewPager vPager_mainAPPModulesGrid;
	
	/** 页码 **/
	private ImageView imgV_mainModulesGridPageNum;
	
	/** 配置按钮 **/
	private ImageButton imgBtn_mainSettings;
	
	/** 翻页动画 **/
	private Rotate3dAnimation mModulesGridPageNumRotateAnimation;
	private int mSelectedPageIndex = 0;
	
	/** 模块名和图标 **/
	private static ArrayList<APPModuleBean> mAppModuleBeans;
	static {
		mAppModuleBeans = new ArrayList<APPModuleBean>();
		mAppModuleBeans.add(new APPModuleBean(R.string.appModule_plan, R.drawable.app_module_plan_icon));
		mAppModuleBeans.add(new APPModuleBean(R.string.appModule_payment, R.drawable.app_module_payment_icon));
		mAppModuleBeans.add(new APPModuleBean(R.string.appModule_contract, R.drawable.app_module_contract_icon));
		mAppModuleBeans.add(new APPModuleBean(R.string.appModule_stock, R.drawable.app_module_stock_icon));
		mAppModuleBeans.add(new APPModuleBean(R.string.appModule_shipmentIntent, R.drawable.app_module_shipment_intent_icon));
		mAppModuleBeans.add(new APPModuleBean(R.string.appModule_shipment, R.drawable.app_module_shipment_icon));
		mAppModuleBeans.add(new APPModuleBean(R.string.appModule_bill, R.drawable.app_module_bill_icon));
		mAppModuleBeans.add(new APPModuleBean(R.string.appModule_info, R.drawable.app_module_info_icon));
		mAppModuleBeans.add(new APPModuleBean(R.string.appModule_sysInform, R.drawable.app_module_sys_inform_icon));
		mAppModuleBeans.add(new APPModuleBean(R.string.appModule_settle, R.drawable.app_module_settle_icon));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_main);
		hideFrameworkTop();
		
		initViews();
	}
	

	private void initViews(){
		vPager_mainAPPModulesGrid = (ViewPager)findViewById(R.id.vPager_mainAPPModulesGrid);
		APPModulesVPagerAdapter _adapter = new APPModulesVPagerAdapter(getApplicationContext());
		_adapter.initViewPages(mAppModuleBeans);
		vPager_mainAPPModulesGrid.setAdapter(_adapter);
		vPager_mainAPPModulesGrid.setOnPageChangeListener(this);
		
		imgV_mainModulesGridPageNum = (ImageView)findViewById(R.id.imgV_mainModulesGridPageNum);
		imgV_mainModulesGridPageNum.setImageResource(R.drawable.main_modules_grid_page_num_one);
		
		imgBtn_mainSettings = (ImageButton)findViewById(R.id.imgBtn_mainSettings);
		imgBtn_mainSettings.setOnClickListener(this);
	}
	
	/** App Module **/
	private static final int MODULE_SETTINGS = 0x1000;
	private static final int MODULE_PLAN = 0x1001;
	private static final int MODULE_PAYMENT = 0x1002;
	private static final int MODULE_CONTRACT = 0x1003;
	private static final int MODULE_STOCK = 0x1004;
	private static final int MODULE_SHIPMENTINTENT = 0x1005;
	private static final int MODULE_SHIPMENT = 0x1006;
	
	private static class TheHandler extends Handler {
		private WeakReference<MainActivity> mWeakReference;
		
		public TheHandler(MainActivity mainActivity){
			mWeakReference = new WeakReference<MainActivity>(mainActivity);
		}
		
		@Override
		public void handleMessage(Message msg) {
			MainActivity _mainActivity = mWeakReference.get();
			if(null != _mainActivity){
				switch(msg.what){
					//设置
					case MODULE_SETTINGS: {
						_mainActivity.startActivity(SettingsActivity.class);
						break;
					}
					default: {
						break;
					}
				}
			}
		}
		
	}
	
	private TheHandler theHandler = new TheHandler(this);

	/**
	 * 页面滑动结束后，调用
	 */
	@Override
	public void onPageSelected(int position) {
		mSelectedPageIndex = position;
		imgV_mainModulesGridPageNum.startAnimation(getModulesGridPageNumRotateAnimation());
	}
	
	/**
	 * 页面状态改变时，调用
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {}

	/**
	 * 页面滑动时，调用
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}

	@Override
	public void onClick(View view) {
		theHandler.sendEmptyMessage(MODULE_SETTINGS);
	}

	public Rotate3dAnimation getModulesGridPageNumRotateAnimation() {
		if(null == mModulesGridPageNumRotateAnimation){
			mModulesGridPageNumRotateAnimation = new Rotate3dAnimation(0, 180, imgV_mainModulesGridPageNum.getMeasuredWidth() / 2.0f, imgV_mainModulesGridPageNum.getMeasuredHeight() / 2.0f, 0.0f, false);
			mModulesGridPageNumRotateAnimation.setDuration(500);
			mModulesGridPageNumRotateAnimation.setFillAfter(false);
	        mModulesGridPageNumRotateAnimation.setInterpolator(new AccelerateInterpolator());
	        mModulesGridPageNumRotateAnimation.setAnimationListener(new Animation.AnimationListener(){
				@Override
				public void onAnimationEnd(Animation animation) {
					animation.reset();
					imgV_mainModulesGridPageNum.clearAnimation();
					
					switch(mSelectedPageIndex){
					case 0:
						imgV_mainModulesGridPageNum.setImageResource(R.drawable.main_modules_grid_page_num_one);
						break;
					case 1:
						imgV_mainModulesGridPageNum.setImageResource(R.drawable.main_modules_grid_page_num_two);
						break;
					}
				}

				@Override
				public void onAnimationRepeat(Animation animation) {}

				@Override
				public void onAnimationStart(Animation animation) {}
	        	
	        });
		}
		
		return mModulesGridPageNumRotateAnimation;
	}
	
	
}
