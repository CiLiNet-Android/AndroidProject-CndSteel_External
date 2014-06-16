package com.cndsteel.framework.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class AbsBaseAdapter<T> extends BaseAdapter {

	/** 要显示的数据 **/
	private ArrayList<T> mDatas;
	
	/** 上下文 **/
	private Context mContext;
	
	public AbsBaseAdapter(Context context){
		mContext = context;
	}
	
	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	/** 
	 * 刷新数据 
	 **/
	public void refreshDatas(ArrayList<T> datas){
		mDatas = datas;
		notifyDataSetInvalidated();
	}
	
	/**
	 * 追加数据
	 */
	public void appendDatas(ArrayList<T> datas){
		if(null == mDatas){
			mDatas = new ArrayList<T>();
		}
		
		//addAll方法，datas为空时，会抛出空指针
		if(null != datas){
			mDatas.addAll(datas);
			notifyDataSetChanged();
		}
		
	}
	
	/**
	 * 初始化数据
	 */
	public void initDatas(ArrayList<T> datas){
		mDatas = datas;
	}
	
	protected LayoutInflater getLayoutInflater() {
		return LayoutInflater.from(mContext);
	}
	
	protected Context getContext(){
		return mContext;
	}

}
