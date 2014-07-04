package com.cndsteel.shipment.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ShipmentQueryViewPagerAdapter extends PagerAdapter {
	
	private ArrayList<View> mViewPages;
	
	
	//构造方法
	public ShipmentQueryViewPagerAdapter(){
	}
	
	public void initViewPages(ArrayList<View> viewPages){
		mViewPages = viewPages;
	}
	
	@Override
	public int getCount() {
		return mViewPages.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mViewPages.get(position), position);
		return mViewPages.get(position);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mViewPages.get(position));
	}
	

}
