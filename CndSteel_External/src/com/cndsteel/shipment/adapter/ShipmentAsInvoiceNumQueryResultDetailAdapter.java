package com.cndsteel.shipment.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.shipment.bean.ShipmentAsInvoiceNumQueryResultDetailListBean;
import com.cndsteel.shipment.bean.ShipmentBean;

public class ShipmentAsInvoiceNumQueryResultDetailAdapter extends BaseExpandableListAdapter {
	
	private Context mContext;
	
	private ArrayList<ShipmentAsInvoiceNumQueryResultDetailListBean> mShipmentAsInvoiceNumQueryResultDetailListBeans;
	
	public ShipmentAsInvoiceNumQueryResultDetailAdapter(Context context){
		mContext = context;
	}
	
	public void initDatas(ArrayList<ShipmentAsInvoiceNumQueryResultDetailListBean> shipmentAsInvoiceNumQueryResultDetailListBeans){
		mShipmentAsInvoiceNumQueryResultDetailListBeans = shipmentAsInvoiceNumQueryResultDetailListBeans;
	}
	
	public void refreshDatas(ArrayList<ShipmentAsInvoiceNumQueryResultDetailListBean> shipmentAsInvoiceNumQueryResultDetailListBeans){
		if(null != mShipmentAsInvoiceNumQueryResultDetailListBeans){
			mShipmentAsInvoiceNumQueryResultDetailListBeans.clear();
			mShipmentAsInvoiceNumQueryResultDetailListBeans = null;
		}
		mShipmentAsInvoiceNumQueryResultDetailListBeans = shipmentAsInvoiceNumQueryResultDetailListBeans;
		
		notifyDataSetInvalidated();
	}
	
	public void appendDatas(ArrayList<ShipmentAsInvoiceNumQueryResultDetailListBean> shipmentAsInvoiceNumQueryResultDetailListBeans){
		if(null == mShipmentAsInvoiceNumQueryResultDetailListBeans){
			mShipmentAsInvoiceNumQueryResultDetailListBeans = new ArrayList<ShipmentAsInvoiceNumQueryResultDetailListBean>();
		}
		mShipmentAsInvoiceNumQueryResultDetailListBeans.addAll(mShipmentAsInvoiceNumQueryResultDetailListBeans.size(), shipmentAsInvoiceNumQueryResultDetailListBeans);
		
		notifyDataSetChanged();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mShipmentAsInvoiceNumQueryResultDetailListBeans.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return mShipmentAsInvoiceNumQueryResultDetailListBeans.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	private class GroupViewHolder {
		public TextView ShipmentDetailAsInvoice_contractNum;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupViewHolder _groupViewHolder = null;
		if(null == convertView){
			_groupViewHolder = new GroupViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_shipment_as_invoice_num_query_detail_list_group,parent,false);
			_groupViewHolder.ShipmentDetailAsInvoice_contractNum = (TextView)convertView.findViewById(R.id.ShipmentDetailAsInvoice_contractNum);
			
			convertView.setTag(_groupViewHolder);
		}else {
			_groupViewHolder = (GroupViewHolder)convertView.getTag();
		}
		
		ShipmentAsInvoiceNumQueryResultDetailListBean _shipmentAsInvoiceNumQueryResultDetailListBean = (ShipmentAsInvoiceNumQueryResultDetailListBean)getGroup(groupPosition);
		_groupViewHolder.ShipmentDetailAsInvoice_contractNum.setText(_shipmentAsInvoiceNumQueryResultDetailListBean.conCode);
		
		return convertView;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return mShipmentAsInvoiceNumQueryResultDetailListBeans.get(groupPosition).shipmentBeans.size();
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mShipmentAsInvoiceNumQueryResultDetailListBeans.get(groupPosition).shipmentBeans.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	
	private class ChildViewHolder {
		public TextView txtV_invoiceNumQueryDetailListChildPName;
		public TextView txtV_invoiceNumQueryDetailListChildMaterial;
		public TextView txtV_invoiceNumQueryDetailListChildWeight;
		public TextView txtV_invoiceNumQueryDetailListChildPiece;
		public TextView txtV_invoiceNumQueryDetailListChildSpec;
		public TextView txtV_invoiceNumQueryDetailListChildAmount;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		ChildViewHolder _childViewHolder = null;
		if(null == convertView){
			_childViewHolder = new ChildViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_shipment_as_invoice_num_query_detail_list_child, parent, false);
			_childViewHolder.txtV_invoiceNumQueryDetailListChildPName = (TextView)convertView.findViewById(R.id.txtV_invoiceNumQueryDetailListChildPName);
			_childViewHolder.txtV_invoiceNumQueryDetailListChildMaterial = (TextView)convertView.findViewById(R.id.txtV_invoiceNumQueryDetailListChildMaterial);
			_childViewHolder.txtV_invoiceNumQueryDetailListChildWeight = (TextView)convertView.findViewById(R.id.txtV_invoiceNumQueryDetailListChildWeight);
			_childViewHolder.txtV_invoiceNumQueryDetailListChildPiece = (TextView)convertView.findViewById(R.id.txtV_invoiceNumQueryDetailListChildPiece);
			_childViewHolder.txtV_invoiceNumQueryDetailListChildSpec = (TextView)convertView.findViewById(R.id.txtV_invoiceNumQueryDetailListChildSpec);
			_childViewHolder.txtV_invoiceNumQueryDetailListChildAmount = (TextView)convertView.findViewById(R.id.txtV_invoiceNumQueryDetailListChildAmount);
			
			convertView.setTag(_childViewHolder);
		}else {
			_childViewHolder = (ChildViewHolder)convertView.getTag();
		}
		
		ShipmentBean _shipmentBean = (ShipmentBean)getChild(groupPosition, childPosition);
		
		_childViewHolder.txtV_invoiceNumQueryDetailListChildPName.setText(_shipmentBean.pname);
		_childViewHolder.txtV_invoiceNumQueryDetailListChildMaterial.setText(_shipmentBean.material);
		_childViewHolder.txtV_invoiceNumQueryDetailListChildWeight.setText(_shipmentBean.weight);
		_childViewHolder.txtV_invoiceNumQueryDetailListChildPiece.setText(_shipmentBean.piece);
		_childViewHolder.txtV_invoiceNumQueryDetailListChildSpec.setText(_shipmentBean.spec);
		_childViewHolder.txtV_invoiceNumQueryDetailListChildAmount.setText(_shipmentBean.amount);
		
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
