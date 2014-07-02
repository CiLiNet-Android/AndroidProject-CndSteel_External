package com.cndsteel.stock.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.stock.bean.StockAccordingGoodsQueryResultBean;
import com.cndsteel.stock.bean.StockBean;

public class StockAccordingGoodsQueryResultListAdapter extends BaseExpandableListAdapter {

	private Context mContext;

	private ArrayList<StockAccordingGoodsQueryResultBean> mStockAccordingGoodsQueryResultBeans;
	
	public StockAccordingGoodsQueryResultListAdapter(Context context){
		mContext = context;
	}
	
	public void initDatas(ArrayList<StockAccordingGoodsQueryResultBean> stockAccordingGoodsQueryResultBeans){
		mStockAccordingGoodsQueryResultBeans = stockAccordingGoodsQueryResultBeans;
	}
	
	@Override
	public int getGroupCount() {
		return mStockAccordingGoodsQueryResultBeans.size();
	}
	
	@Override
	public Object getGroup(int groupPosition) {
		return mStockAccordingGoodsQueryResultBeans.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	private class GroupViewHolder {
		public TextView txtV_stockAccordingGoodsQueryResultListItemGroupPName;
	} 
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupViewHolder _groupViewHolder = null;
		if(null == convertView){
			_groupViewHolder = new GroupViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_stock_according_goods_query_result_group,parent,false);
			_groupViewHolder.txtV_stockAccordingGoodsQueryResultListItemGroupPName = (TextView)convertView.findViewById(R.id.txtV_stockAccordingGoodsQueryResultListItemGroupPName);
			
			convertView.setTag(_groupViewHolder);
		}else {
			_groupViewHolder = (GroupViewHolder)convertView.getTag();
		}
		
		StockAccordingGoodsQueryResultBean _stockAccordingGoodsQueryResultBean = (StockAccordingGoodsQueryResultBean)getGroup(groupPosition);
		_groupViewHolder.txtV_stockAccordingGoodsQueryResultListItemGroupPName.setText(_stockAccordingGoodsQueryResultBean.pName);
		
		return convertView;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return mStockAccordingGoodsQueryResultBeans.get(groupPosition).stockBeans.size();
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mStockAccordingGoodsQueryResultBeans.get(groupPosition).stockBeans.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	private class ChildViewHolder {
		public TextView txtV_accordingGoodsQueryResultListChildWareHouse;
		public TextView txtV_accordingGoodsQueryResultListChildMaterial;
		public TextView txtV_accordingGoodsQueryResultListChildWeight;
		public TextView txtV_accordingGoodsQueryResultListChildPiece;
		public TextView txtV_accordingGoodsQueryResultListChildSpec;
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		ChildViewHolder _childViewHolder = null;
		if(null == convertView){
			_childViewHolder = new ChildViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_stock_according_goods_query_result_child, parent, false);
			_childViewHolder.txtV_accordingGoodsQueryResultListChildWareHouse = (TextView)convertView.findViewById(R.id.txtV_accordingGoodsQueryResultListChildWareHouse);
			_childViewHolder.txtV_accordingGoodsQueryResultListChildMaterial = (TextView)convertView.findViewById(R.id.txtV_accordingGoodsQueryResultListChildMaterial);
			_childViewHolder.txtV_accordingGoodsQueryResultListChildWeight = (TextView)convertView.findViewById(R.id.txtV_accordingGoodsQueryResultListChildWeight);
			_childViewHolder.txtV_accordingGoodsQueryResultListChildPiece = (TextView)convertView.findViewById(R.id.txtV_accordingGoodsQueryResultListChildPiece);
			_childViewHolder.txtV_accordingGoodsQueryResultListChildSpec = (TextView)convertView.findViewById(R.id.txtV_accordingGoodsQueryResultListChildSpec);
			
			convertView.setTag(_childViewHolder);
		}else {
			_childViewHolder = (ChildViewHolder)convertView.getTag();
		}
		
		StockBean _stockBean = (StockBean)getChild(groupPosition, childPosition);
		_childViewHolder.txtV_accordingGoodsQueryResultListChildWareHouse.setText(_stockBean.wareName);
		_childViewHolder.txtV_accordingGoodsQueryResultListChildMaterial.setText(_stockBean.material);
		_childViewHolder.txtV_accordingGoodsQueryResultListChildWeight.setText(_stockBean.weight);
		_childViewHolder.txtV_accordingGoodsQueryResultListChildPiece.setText(_stockBean.piece);
		_childViewHolder.txtV_accordingGoodsQueryResultListChildSpec.setText(_stockBean.spec);
		
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
	
	public void refreshDatas(ArrayList<StockAccordingGoodsQueryResultBean> stockAccordingGoodsQueryResultBeans){
		mStockAccordingGoodsQueryResultBeans = stockAccordingGoodsQueryResultBeans;
		notifyDataSetInvalidated();
	}
	
	public void appendDatas(ArrayList<StockAccordingGoodsQueryResultBean> stockAccordingGoodsQueryResultBeans){
		mStockAccordingGoodsQueryResultBeans.addAll(mStockAccordingGoodsQueryResultBeans.size(), stockAccordingGoodsQueryResultBeans);
		notifyDataSetChanged();
	}

}
