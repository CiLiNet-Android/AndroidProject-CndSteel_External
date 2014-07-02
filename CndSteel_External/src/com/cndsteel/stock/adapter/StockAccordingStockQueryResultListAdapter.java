package com.cndsteel.stock.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.stock.bean.StockAccordingStockQueryResultBean;
import com.cndsteel.stock.bean.StockBean;

public class StockAccordingStockQueryResultListAdapter extends BaseExpandableListAdapter {
	
	private Context mContext;

	private ArrayList<StockAccordingStockQueryResultBean> mAccordingStockQueryResultBeans;
	
	public StockAccordingStockQueryResultListAdapter(Context context){
		mContext = context;
	}
	
	public void initDatas(ArrayList<StockAccordingStockQueryResultBean> accordingStockQueryResultBeans){
		mAccordingStockQueryResultBeans = accordingStockQueryResultBeans;
	}
	
	@Override
	public int getGroupCount() {
		return mAccordingStockQueryResultBeans.size();
	}
	
	@Override
	public Object getGroup(int groupPosition) {
		return mAccordingStockQueryResultBeans.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	private class GroupViewHolder {
		public TextView contract_terms_Num;
	} 
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupViewHolder _groupViewHolder = null;
		if(null == convertView){
			_groupViewHolder = new GroupViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_stock_according_stock_query_result_group,parent,false);
			_groupViewHolder.contract_terms_Num = (TextView)convertView.findViewById(R.id.contract_terms_Num);
			
			convertView.setTag(_groupViewHolder);
		}else {
			_groupViewHolder = (GroupViewHolder)convertView.getTag();
		}
		
		StockAccordingStockQueryResultBean _accordingStockQueryResultBean = (StockAccordingStockQueryResultBean)getGroup(groupPosition);
		_groupViewHolder.contract_terms_Num.setText(_accordingStockQueryResultBean.wareName);
		
		return convertView;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return mAccordingStockQueryResultBeans.get(groupPosition).stockBeans.size();
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mAccordingStockQueryResultBeans.get(groupPosition).stockBeans.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	private class ChildViewHolder {
		public TextView stock_goodsName;
		public TextView stock_Material;
		public TextView stock_Standard;
		public TextView stock_Tonnage;
		public TextView stock_NumberOfPackages;
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		ChildViewHolder _childViewHolder = null;
		if(null == convertView){
			_childViewHolder = new ChildViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_stock_according_stock_query_result_child, parent, false);
			_childViewHolder.stock_goodsName = (TextView)convertView.findViewById(R.id.stock_goodsName);
			_childViewHolder.stock_Material = (TextView)convertView.findViewById(R.id.stock_Material);
			_childViewHolder.stock_Standard = (TextView)convertView.findViewById(R.id.stock_Standard);
			_childViewHolder.stock_Tonnage = (TextView)convertView.findViewById(R.id.stock_Tonnage);
			_childViewHolder.stock_NumberOfPackages = (TextView)convertView.findViewById(R.id.stock_NumberOfPackages);
			
			convertView.setTag(_childViewHolder);
		}else {
			_childViewHolder = (ChildViewHolder)convertView.getTag();
		}
		
		StockBean _stockBean = (StockBean)getChild(groupPosition, childPosition);
		_childViewHolder.stock_goodsName.setText(_stockBean.pname);
		_childViewHolder.stock_Material.setText(_stockBean.material);
		_childViewHolder.stock_Standard.setText(_stockBean.spec);
		_childViewHolder.stock_Tonnage.setText(_stockBean.weight);
		_childViewHolder.stock_NumberOfPackages.setText(_stockBean.piece);
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	public void refreshDatas(ArrayList<StockAccordingStockQueryResultBean> accordingStockQueryResultBeans){
		mAccordingStockQueryResultBeans = accordingStockQueryResultBeans;
		notifyDataSetInvalidated();
	}
	
	public void appendDatas(ArrayList<StockAccordingStockQueryResultBean> accordingStockQueryResultBeans){
		mAccordingStockQueryResultBeans.addAll(mAccordingStockQueryResultBeans.size(), mAccordingStockQueryResultBeans);
		notifyDataSetChanged();
	}

}
