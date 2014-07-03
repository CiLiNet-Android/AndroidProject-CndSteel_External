package com.cndsteel.stock.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class StockQueryViewPagerAdapter extends PagerAdapter {

	private ArrayList<View> mViewPages;
	
	public StockQueryViewPagerAdapter(Context context){
	}
	
	public void initViewPages(ArrayList<View> viewPages){
		mViewPages = viewPages;
	}
	
	/** 所要显示的页面数 **/
	@Override
	public int getCount() {
		return mViewPages.size();
	}

	/** 判断是否由对象生成内容 **/
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;

	}

	/** 返回当前位置上item的数据 **/
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mViewPages.get(position), null);
		return mViewPages.get(position);
	}

	/** 删除当前位置上的页面 **/
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mViewPages.get(position));
	}


}
