package com.cndsteel.shipment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.shipment.bean.ShipmentBean;

public class ShipmentAsInvoiceNumQueryResultListAdapter extends AbsBaseAdapter<ShipmentBean> {

	public ShipmentAsInvoiceNumQueryResultListAdapter(Context context) {
		super(context);
	}

	private class ViewHolder {
		public TextView number;
		public TextView tonnage;
		public TextView numberOfPackages;
		public TextView warehouse;
		public TextView date;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _viewHolder;
		if(null == convertView){
			convertView = getLayoutInflater().inflate(R.layout.shipment_as_invoice_number_query_result_item, null);
			
			_viewHolder = new ViewHolder();
			_viewHolder.number = (TextView) convertView.findViewById(R.id.shipment_AsInvoiceNum_value);
			_viewHolder.tonnage = (TextView) convertView.findViewById(R.id.shipment_AsInvoiceNum_tonnage);
			_viewHolder.numberOfPackages = (TextView) convertView.findViewById(R.id.shipment_AsInvoiceNum_numberOfPackages);
			_viewHolder.warehouse = (TextView) convertView.findViewById(R.id.shipment_AsInvoiceNum_warehouse);
			_viewHolder.date = (TextView) convertView.findViewById(R.id.shipment_AsInvoiceNum_date);
			
			convertView.setTag(_viewHolder);
		}else {
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		ShipmentBean _shipmentBean = (ShipmentBean) getItem(position);
		
		_viewHolder.number.setText(_shipmentBean.shipNO);
		_viewHolder.tonnage.setText(_shipmentBean.weight);
		_viewHolder.numberOfPackages.setText(_shipmentBean.piece);
		_viewHolder.warehouse.setText(_shipmentBean.storeName);
		_viewHolder.date.setText(_shipmentBean.shipDate);
		
		return convertView;
	}

}
