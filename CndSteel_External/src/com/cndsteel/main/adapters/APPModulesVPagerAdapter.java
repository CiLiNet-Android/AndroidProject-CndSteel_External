package com.cndsteel.main.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cndsteel.framework.utils.UnitTransformUtils;
import com.cndsteel.main.beans.APPModuleBean;

/**
 * 准备首页Modules Item
 * @author zhxl
 */
public class APPModulesVPagerAdapter extends PagerAdapter {
	
	private Context mContext;
	
	private ArrayList<View> mViewPages;

	public APPModulesVPagerAdapter(Context context){
		mContext = context;
	}
	
	public void initViewPages(ArrayList<View> viewPages){
		mViewPages = viewPages;
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
