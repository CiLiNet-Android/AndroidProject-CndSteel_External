package com.cndsteel.payment.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.views.spinner.AbsSpinner.OnItemClickListener;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;
import com.cndsteel.payment.activity.PaymentAsContractQueryResultListActivity;
import com.cndsteel.payment.activity.PaymentAsInformationQueryResultListActivity;

public class PaymentQueryViewPagerAdapter extends PagerAdapter {

	private Context mContext;
	
	private ArrayList<View> mViewPages;
	
	//构造方法
	public PaymentQueryViewPagerAdapter(Context context){
		mContext = context;
	}
	
	public void initDatas(ArrayList<View> viewPages){
		mViewPages = viewPages;
	}

	
	@Override
	public int getCount() {
		return mViewPages.size();
	}

	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mViewPages.get(position), null);
		return mViewPages.get(position);
	}
	
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	/** 删除当前位置上的页面 **/
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mViewPages.get(position));
	}
}
