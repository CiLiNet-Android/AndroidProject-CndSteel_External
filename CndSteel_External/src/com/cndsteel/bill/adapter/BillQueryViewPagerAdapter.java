package com.cndsteel.bill.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class BillQueryViewPagerAdapter extends PagerAdapter {
	
	private Context mContext;
	private ArrayList<View> mViewPages;
	
	public BillQueryViewPagerAdapter(Context context) {
		mContext = context;
	}
	
	public void initViewPages(ArrayList<View> viewPages){
		mViewPages = viewPages;
	}

	@Override
	public int getCount() {
		return mViewPages.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mViewPages.get(position));
		return mViewPages.get(position);
	}
	
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mViewPages.get(position));
	}

}
