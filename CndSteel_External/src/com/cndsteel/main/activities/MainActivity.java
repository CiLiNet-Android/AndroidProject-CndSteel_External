package com.cndsteel.main.activities;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.cndsteel.R;
import com.cndsteel.bill.activity.BillQueryActivity;
import com.cndsteel.contract.activities.ContractQueryActivity;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.animation.Rotate3dAnimation;
import com.cndsteel.framework.utils.UnitTransformUtils;
import com.cndsteel.info.activity.InfoQueryResultListActivity;
import com.cndsteel.main.adapters.APPModulesGridVAdapter;
import com.cndsteel.main.adapters.APPModulesVPagerAdapter;
import com.cndsteel.main.beans.APPModuleBean;
import com.cndsteel.payment.activity.PaymentQueryActivity;
import com.cndsteel.plan.activities.PlanQueryActivity;
import com.cndsteel.settings.activities.SettingsActivity;
import com.cndsteel.shipment.activity.ShipmentQueryActivity;
import com.cndsteel.stock.activity.StockQueryActivity;
import com.cndsteel.sysinform.activity.SysInformQueryResultListActivity;

/**
 * 主页
 */
public class MainActivity extends FrameActivity implements ViewPager.OnPageChangeListener,View.OnClickListener,OnItemClickListener {
	
	private static final int HANDLER_MSG_PROGRAM_EXIT = 0x999;

	/** 页面显示项目数 **/
	private static final int PAGE_ITEMS_COUNT = 6;
	
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
		_adapter.initViewPages(initModuleViewPages());
		vPager_mainAPPModulesGrid.setAdapter(_adapter);
		vPager_mainAPPModulesGrid.setOnPageChangeListener(this);
		
		imgV_mainModulesGridPageNum = (ImageView)findViewById(R.id.imgV_mainModulesGridPageNum);
		imgV_mainModulesGridPageNum.setImageResource(R.drawable.main_modules_grid_page_num_one);
		
		imgBtn_mainSettings = (ImageButton)findViewById(R.id.imgBtn_mainSettings);
		imgBtn_mainSettings.setOnClickListener(this);
	}
	
	private ArrayList<View> initModuleViewPages(){
		ArrayList<View> _viewPages = new ArrayList<View>();
		
		int _appModuleBeansTotal = mAppModuleBeans.size();
		
		//计算页数
		int _pageCount = (_appModuleBeansTotal % PAGE_ITEMS_COUNT == 0)? (_appModuleBeansTotal / PAGE_ITEMS_COUNT) : (_appModuleBeansTotal / PAGE_ITEMS_COUNT + 1);
		
		//创建每一个页面
		for(int pageIndex = 1; pageIndex <= _pageCount; pageIndex ++){
			int _pageItemBeginIndex = (pageIndex - 1) * PAGE_ITEMS_COUNT;
			int _pageItemEndIndex = (_appModuleBeansTotal -1) < ((pageIndex * PAGE_ITEMS_COUNT) - 1)? (_appModuleBeansTotal - 1) : ((pageIndex * PAGE_ITEMS_COUNT) - 1);
			if(_pageItemBeginIndex <= _pageItemEndIndex){
				//一个页面的内容
				ArrayList<APPModuleBean> _pageItems = new ArrayList<APPModuleBean>();
				for(int pageItemIndex = _pageItemBeginIndex; pageItemIndex <= _pageItemEndIndex; pageItemIndex ++){
					_pageItems.add(mAppModuleBeans.get(pageItemIndex));
				}
				
				//获得一个页面显示的内容后，创建这个页面，并添加到容器中
				View _viewPage = createViewPage(_pageItems);
				_viewPages.add(_viewPage);
			}
			
		}
		
		return _viewPages;
	}
	
	/**
	 * 创建ViewPage,appModuleBeans：一页要显示的内容
	 */
	private View createViewPage(ArrayList<APPModuleBean> appModuleBeans){
		GridView _mainAppModulesGridV = new GridView(getApplicationContext());
		_mainAppModulesGridV.setGravity(Gravity.CENTER);
		_mainAppModulesGridV.setNumColumns(2);
		_mainAppModulesGridV.setCacheColorHint(0x00000000);
		_mainAppModulesGridV.setVerticalSpacing(UnitTransformUtils.dip2px(getApplicationContext(), 20));
		_mainAppModulesGridV.setOnItemClickListener(this);
		
		ViewPager.LayoutParams _layoutParams = new ViewPager.LayoutParams();
		_layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
		_layoutParams.height = ViewPager.LayoutParams.MATCH_PARENT;
		_mainAppModulesGridV.setLayoutParams(_layoutParams);
		
		APPModulesGridVAdapter _appModulesGridVAdapter = new APPModulesGridVAdapter(getApplicationContext());
		_appModulesGridVAdapter.initDatas(appModuleBeans);
		
		_mainAppModulesGridV.setAdapter(_appModulesGridVAdapter);
		
		return _mainAppModulesGridV;
	}
	
	/** App Module **/
	private static final int MODULE_SETTINGS = 0x1000;
	private static final int MODULE_PLAN = 0x1001;
	private static final int MODULE_PAYMENT = 0x1002;
	private static final int MODULE_CONTRACT = 0x1003;
	private static final int MODULE_STOCK = 0x1004;
	private static final int MODULE_SHIPMENTINTENT = 0x1005;
	private static final int MODULE_SHIPMENT = 0x1006;
	private static final int MODULE_BILL = 0x1007;
	private static final int MODULE_INFO = 0x1008;
	private static final int MODULE_SYSINFORM = 0x1009;
	private static final int MODULE_SETTLE = 0x1010;
	
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
				case MODULE_PLAN: {
					_mainActivity.startActivity(PlanQueryActivity.class);
					break;
				}
				case MODULE_PAYMENT: {
					_mainActivity.startActivity(PaymentQueryActivity.class);
					break;
				}
				case MODULE_CONTRACT: {
					_mainActivity.startActivity(ContractQueryActivity.class);
					break;
				}
				case MODULE_STOCK: {
					_mainActivity.startActivity(StockQueryActivity.class);
					break;
				}
				case MODULE_SHIPMENTINTENT: {
					_mainActivity.showAlertDialog(R.string.TipInfo, "该功能暂未开放.", null);
					break;
				}
				case MODULE_SHIPMENT: {
					_mainActivity.startActivity(ShipmentQueryActivity.class);
					break;
				}
				case MODULE_BILL: {
					_mainActivity.startActivity(BillQueryActivity.class);
					break;
				}
				case MODULE_INFO: {
					_mainActivity.startActivity(InfoQueryResultListActivity.class);
					break;
				}
				case MODULE_SYSINFORM: {
					_mainActivity.startActivity(SysInformQueryResultListActivity.class);
					break;
				}
				case MODULE_SETTLE: {
					_mainActivity.showAlertDialog(R.string.TipInfo, "该功能暂未开放.", null);
					break;
				}
				case MODULE_SETTINGS: {
					_mainActivity.startActivity(SettingsActivity.class);
					break;
				}
				case HANDLER_MSG_PROGRAM_EXIT: {
					_mainActivity.isProgramExit = false;
					break;
				}
				default: {
					break;
				}
				}
			}
		}
		
	}
	
	private TheHandler mTheHandler = new TheHandler(this);

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
		int _viewId = view.getId();
		switch (_viewId) {
		case R.id.imgBtn_mainSettings:
			mTheHandler.sendEmptyMessage(MODULE_SETTINGS);
			break;
		default:
			break;
		}
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


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		APPModuleBean _appModuleBean = (APPModuleBean)parent.getAdapter().getItem(position);
		
		switch (_appModuleBean.nameResId) {
		case R.string.appModule_plan: {
			mTheHandler.sendEmptyMessage(MODULE_PLAN);
			break;
		}
		case R.string.appModule_payment: {
			mTheHandler.sendEmptyMessage(MODULE_PAYMENT);
			break;
		}
		case R.string.appModule_contract: {
			mTheHandler.sendEmptyMessage(MODULE_CONTRACT);
			break;
		}
		case R.string.appModule_stock: {
			mTheHandler.sendEmptyMessage(MODULE_STOCK);
			break;
		}
		case R.string.appModule_shipmentIntent: {
			mTheHandler.sendEmptyMessage(MODULE_SHIPMENTINTENT);
			break;
		}
		case R.string.appModule_shipment: {
			mTheHandler.sendEmptyMessage(MODULE_SHIPMENT);
			break;
		}
		case R.string.appModule_bill: {
			mTheHandler.sendEmptyMessage(MODULE_BILL);
			break;
		}
		case R.string.appModule_info: {
			mTheHandler.sendEmptyMessage(MODULE_INFO);
			break;
		}
		case R.string.appModule_sysInform: {
			mTheHandler.sendEmptyMessage(MODULE_SYSINFORM);
			break;
		}
		case R.string.appModule_settle: {
			mTheHandler.sendEmptyMessage(MODULE_SETTLE);
			break;
		}
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {  
            exit();  
            return false;  
        } else {  
            return super.onKeyDown(keyCode, event);  
        }  
	}
	
	public boolean isProgramExit = false;
	
    public void exit(){  
        if (!isProgramExit) {  
        	isProgramExit = true;  
            showToast("再按一次退出程序");
            mTheHandler.sendEmptyMessageDelayed(HANDLER_MSG_PROGRAM_EXIT, 2000);  
        } else {  
            Intent intent = new Intent(Intent.ACTION_MAIN);  
            intent.addCategory(Intent.CATEGORY_HOME);  
            startActivity(intent);  
            System.exit(0);  
        }  
    }  
	
}
