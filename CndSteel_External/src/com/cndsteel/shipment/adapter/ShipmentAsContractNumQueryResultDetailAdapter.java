package com.cndsteel.shipment.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.shipment.bean.ShipmentAsContractNumQueryResultDetailListBean;
import com.cndsteel.shipment.bean.ShipmentBean;

public class ShipmentAsContractNumQueryResultDetailAdapter extends
		BaseExpandableListAdapter {

	private Context mContext;
	
	private ArrayList<ShipmentAsContractNumQueryResultDetailListBean> mShipmentAsContractNumQueryResultDetailListBeans;
	
	public ShipmentAsContractNumQueryResultDetailAdapter(Context context){
		mContext = context;
	}
	
	public void initDatas(ArrayList<ShipmentAsContractNumQueryResultDetailListBean> shipmentAsContractNumQueryResultDetailListBeans){
		mShipmentAsContractNumQueryResultDetailListBeans = shipmentAsContractNumQueryResultDetailListBeans;
	}
	
	public void refreshDatas(ArrayList<ShipmentAsContractNumQueryResultDetailListBean> shipmentAsContractNumQueryResultDetailListBeans){
		if(null != mShipmentAsContractNumQueryResultDetailListBeans){
			mShipmentAsContractNumQueryResultDetailListBeans.clear();
			mShipmentAsContractNumQueryResultDetailListBeans = null;
		}
		mShipmentAsContractNumQueryResultDetailListBeans = shipmentAsContractNumQueryResultDetailListBeans;
		
		notifyDataSetInvalidated();
	}
	
	public void appendDatas(ArrayList<ShipmentAsContractNumQueryResultDetailListBean> shipmentAsContractNumQueryResultDetailListBeans){
		if(null == mShipmentAsContractNumQueryResultDetailListBeans){
			mShipmentAsContractNumQueryResultDetailListBeans = new ArrayList<ShipmentAsContractNumQueryResultDetailListBean>();
		}
		mShipmentAsContractNumQueryResultDetailListBeans.addAll(mShipmentAsContractNumQueryResultDetailListBeans.size(), shipmentAsContractNumQueryResultDetailListBeans);
		
		notifyDataSetChanged();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mShipmentAsContractNumQueryResultDetailListBeans.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return mShipmentAsContractNumQueryResultDetailListBeans.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	private class GroupViewHolder {
		public TextView txtV_shipmentAsContractNumQueryDetailListGroupShipNO;
		public TextView txtV_shipmentAsContractNumQueryDetailListGroupStoreName;
		public TextView txtV_shipmentAsContractNumQueryDetailListGroupShipDate;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupViewHolder _groupViewHolder = null;
		if(null == convertView){
			_groupViewHolder = new GroupViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_shipment_as_contract_num_query_detail_list_group,parent,false);
			_groupViewHolder.txtV_shipmentAsContractNumQueryDetailListGroupShipNO = (TextView)convertView.findViewById(R.id.txtV_shipmentAsContractNumQueryDetailListGroupShipNO);
			_groupViewHolder.txtV_shipmentAsContractNumQueryDetailListGroupStoreName = (TextView)convertView.findViewById(R.id.txtV_shipmentAsContractNumQueryDetailListGroupStoreName);
			_groupViewHolder.txtV_shipmentAsContractNumQueryDetailListGroupShipDate = (TextView)convertView.findViewById(R.id.txtV_shipmentAsContractNumQueryDetailListGroupShipDate);
			
			convertView.setTag(_groupViewHolder);
		}else {
			_groupViewHolder = (GroupViewHolder)convertView.getTag();
		}
		
		ShipmentAsContractNumQueryResultDetailListBean _shipmentAsContractNumQueryResultDetailListBean = (ShipmentAsContractNumQueryResultDetailListBean)getGroup(groupPosition);
		_groupViewHolder.txtV_shipmentAsContractNumQueryDetailListGroupShipNO.setText(_shipmentAsContractNumQueryResultDetailListBean.shipNO);
		_groupViewHolder.txtV_shipmentAsContractNumQueryDetailListGroupStoreName.setText(_shipmentAsContractNumQueryResultDetailListBean.storeName);
		_groupViewHolder.txtV_shipmentAsContractNumQueryDetailListGroupShipDate.setText(_shipmentAsContractNumQueryResultDetailListBean.shipDate);
		
		return convertView;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return mShipmentAsContractNumQueryResultDetailListBeans.get(groupPosition).shipmentBeans.size();
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mShipmentAsContractNumQueryResultDetailListBeans.get(groupPosition).shipmentBeans.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	
	private class ChildViewHolder {
		public TextView txtV_contractNumQueryDetailListChildPName;
		public TextView txtV_contractNumQueryDetailListChildMaterial;
		public TextView txtV_contractNumQueryDetailListChildWeight;
		public TextView txtV_contractNumQueryDetailListChildPiece;
		public TextView txtV_contractNumQueryDetailListChildSpec;
		public TextView txtV_contractNumQueryDetailListChildAmount;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		ChildViewHolder _childViewHolder = null;
		if(null == convertView){
			_childViewHolder = new ChildViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_shipment_as_contract_num_query_detail_list_child, parent, false);
			_childViewHolder.txtV_contractNumQueryDetailListChildPName = (TextView)convertView.findViewById(R.id.txtV_contractNumQueryDetailListChildPName);
			_childViewHolder.txtV_contractNumQueryDetailListChildMaterial = (TextView)convertView.findViewById(R.id.txtV_contractNumQueryDetailListChildMaterial);
			_childViewHolder.txtV_contractNumQueryDetailListChildWeight = (TextView)convertView.findViewById(R.id.txtV_contractNumQueryDetailListChildWeight);
			_childViewHolder.txtV_contractNumQueryDetailListChildPiece = (TextView)convertView.findViewById(R.id.txtV_contractNumQueryDetailListChildPiece);
			_childViewHolder.txtV_contractNumQueryDetailListChildSpec = (TextView)convertView.findViewById(R.id.txtV_contractNumQueryDetailListChildSpec);
			_childViewHolder.txtV_contractNumQueryDetailListChildAmount = (TextView)convertView.findViewById(R.id.txtV_contractNumQueryDetailListChildAmount);
			
			convertView.setTag(_childViewHolder);
		}else {
			_childViewHolder = (ChildViewHolder)convertView.getTag();
		}
		
		ShipmentBean _shipmentBean = (ShipmentBean)getChild(groupPosition, childPosition);
		
		_childViewHolder.txtV_contractNumQueryDetailListChildPName.setText(_shipmentBean.pname);
		_childViewHolder.txtV_contractNumQueryDetailListChildMaterial.setText(_shipmentBean.material);
		_childViewHolder.txtV_contractNumQueryDetailListChildWeight.setText(_shipmentBean.weight);
		_childViewHolder.txtV_contractNumQueryDetailListChildPiece.setText(_shipmentBean.piece);
		_childViewHolder.txtV_contractNumQueryDetailListChildSpec.setText(_shipmentBean.spec);
		_childViewHolder.txtV_contractNumQueryDetailListChildAmount.setText(_shipmentBean.amount);
		
		return convertView;
	}

	

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

}
