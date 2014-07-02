package com.cndsteel.stock.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.stock.bean.StockAccordingContractQueryResultBean;
import com.cndsteel.stock.bean.StockBean;

public class StockAccordingContractQueryResultListAdapter extends BaseExpandableListAdapter {

	private Context mContext;

	private ArrayList<StockAccordingContractQueryResultBean> mAccordingContractQueryResultBeans;
	
	public StockAccordingContractQueryResultListAdapter(Context context){
		mContext = context;
	}
	
	public void initDatas(ArrayList<StockAccordingContractQueryResultBean> accordingContractQueryResultBeans){
		mAccordingContractQueryResultBeans = accordingContractQueryResultBeans;
	}
	
	@Override
	public int getGroupCount() {
		return mAccordingContractQueryResultBeans.size();
	}
	
	@Override
	public Object getGroup(int groupPosition) {
		return mAccordingContractQueryResultBeans.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	private class GroupViewHolder {
		public TextView txtV_contractItemNum;
	} 
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupViewHolder _groupViewHolder = null;
		if(null == convertView){
			_groupViewHolder = new GroupViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_stock_according_contract_query_result_group,parent,false);
			_groupViewHolder.txtV_contractItemNum = (TextView)convertView.findViewById(R.id.txtV_contractItemNum);
			
			convertView.setTag(_groupViewHolder);
		}else {
			_groupViewHolder = (GroupViewHolder)convertView.getTag();
		}
		
		StockAccordingContractQueryResultBean _stockAccordingContractQueryResultBean = (StockAccordingContractQueryResultBean)getGroup(groupPosition);
		_groupViewHolder.txtV_contractItemNum.setText(_stockAccordingContractQueryResultBean.conCode);
		
		return convertView;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return mAccordingContractQueryResultBeans.get(groupPosition).stockBeans.size();
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mAccordingContractQueryResultBeans.get(groupPosition).stockBeans.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	private class ChildViewHolder {
		public TextView txtV_accordingContractQueryResultListItemPName;
		public TextView txtV_accordingContractQueryResultListItemMaterial;
		public TextView txtV_accordingContractQueryResultListItemWeight;
		public TextView txtV_accordingContractQueryResultListItemPiece;
		public TextView txtV_accordingContractQueryResultListItemWareHouse;
		public TextView txtV_accordingContractQueryResultListItemSpec;
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		ChildViewHolder _childViewHolder = null;
		if(null == convertView){
			_childViewHolder = new ChildViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_stock_according_contract_query_result_child, parent, false);
			_childViewHolder.txtV_accordingContractQueryResultListItemPName = (TextView)convertView.findViewById(R.id.txtV_accordingContractQueryResultListItemPName);
			_childViewHolder.txtV_accordingContractQueryResultListItemMaterial = (TextView)convertView.findViewById(R.id.txtV_accordingContractQueryResultListItemMaterial);
			_childViewHolder.txtV_accordingContractQueryResultListItemWeight = (TextView)convertView.findViewById(R.id.txtV_accordingContractQueryResultListItemWeight);
			_childViewHolder.txtV_accordingContractQueryResultListItemPiece = (TextView)convertView.findViewById(R.id.txtV_accordingContractQueryResultListItemPiece);
			_childViewHolder.txtV_accordingContractQueryResultListItemWareHouse = (TextView)convertView.findViewById(R.id.txtV_accordingContractQueryResultListItemWareHouse);
			_childViewHolder.txtV_accordingContractQueryResultListItemSpec = (TextView)convertView.findViewById(R.id.txtV_accordingContractQueryResultListItemSpec);
			
			convertView.setTag(_childViewHolder);
		}else {
			_childViewHolder = (ChildViewHolder)convertView.getTag();
		}
		
		StockBean _stockBean = (StockBean)getChild(groupPosition, childPosition);
		_childViewHolder.txtV_accordingContractQueryResultListItemPName.setText(_stockBean.pname);
		_childViewHolder.txtV_accordingContractQueryResultListItemMaterial.setText(_stockBean.material);
		_childViewHolder.txtV_accordingContractQueryResultListItemWeight.setText(_stockBean.weight);
		_childViewHolder.txtV_accordingContractQueryResultListItemPiece.setText(_stockBean.piece);
		_childViewHolder.txtV_accordingContractQueryResultListItemWareHouse.setText(_stockBean.wareName);
		_childViewHolder.txtV_accordingContractQueryResultListItemSpec.setText(_stockBean.spec);
		
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
	
	public void refreshDatas(ArrayList<StockAccordingContractQueryResultBean> accordingContractQueryResultBeans){
		mAccordingContractQueryResultBeans = accordingContractQueryResultBeans;
		notifyDataSetInvalidated();
	}
	
	public void appendDatas(ArrayList<StockAccordingContractQueryResultBean> accordingContractQueryResultBeans){
		mAccordingContractQueryResultBeans.addAll(mAccordingContractQueryResultBeans.size(), accordingContractQueryResultBeans);
		notifyDataSetChanged();
	}

}
