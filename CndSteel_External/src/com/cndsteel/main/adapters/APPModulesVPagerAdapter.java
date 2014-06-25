package com.cndsteel.main.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.utils.UnitTransformUtils;
import com.cndsteel.main.beans.APPModuleBean;

/**
 * 准备首页Modules Item
 * @author zhxl
 */
public class APPModulesVPagerAdapter extends PagerAdapter {
	
	/** 页面显示项目数 **/
	private static final int PAGE_ITEMS_COUNT = 6;
	
	private Context mContext;
	
	private ArrayList<View> mViewPages;

	public APPModulesVPagerAdapter(Context context){
		mContext = context;
	}
	
	public void initViewPages(ArrayList<APPModuleBean> appModuleBeans){
		int _appModuleBeansTotal = appModuleBeans.size();
		
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
					_pageItems.add(appModuleBeans.get(pageItemIndex));
				}
				
				//获得一个页面显示的内容后，创建这个页面，并添加到容器中
				View _viewPage = createViewPage(_pageItems);
				getViewPages().add(_viewPage);
			}
			
		}
	}
	
	/**
	 * 创建ViewPage,appModuleBeans：一页要显示的内容
	 */
	private View createViewPage(ArrayList<APPModuleBean> appModuleBeans){
		GridView _mainAppModulesGridV = new GridView(mContext);
		_mainAppModulesGridV.setGravity(Gravity.CENTER);
		_mainAppModulesGridV.setNumColumns(2);
		_mainAppModulesGridV.setCacheColorHint(0x00000000);
		_mainAppModulesGridV.setVerticalSpacing(UnitTransformUtils.dip2px(mContext, 20));
		
		ViewPager.LayoutParams _layoutParams = new ViewPager.LayoutParams();
		_layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
		_layoutParams.height = ViewPager.LayoutParams.MATCH_PARENT;
		_mainAppModulesGridV.setLayoutParams(_layoutParams);
		
		APPModulesGridVAdapter _appModulesGridVAdapter = new APPModulesGridVAdapter(mContext);
		_appModulesGridVAdapter.initDatas(appModuleBeans);
		
		_mainAppModulesGridV.setAdapter(_appModulesGridVAdapter);
		
		return _mainAppModulesGridV;
	}
	
	@Override
	public int getCount() {
		return getViewPages().size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View _viewPage = getViewPages().get(position);
		
		container.addView(_viewPage);
		
		return _viewPage;
	}

	@Override
	public boolean isViewFromObject(View view, Object Object) {
		return view == Object;
	}

	private ArrayList<View> getViewPages(){
		if(null == mViewPages){
			mViewPages = new ArrayList<View>();
		}
		return mViewPages;
	}
}
