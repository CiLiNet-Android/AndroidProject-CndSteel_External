package com.cndsteel.shipment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.shipment.bean.ShipmentBean;

public class ShipmentAsContractNumQueryResultListAdapter extends AbsBaseAdapter<ShipmentBean> {

	public ShipmentAsContractNumQueryResultListAdapter(Context context) {
		super(context);
	}
	
	private class ViewHolder {
		public TextView number;
		public TextView tonnage;
		public TextView numberOfPackages;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder _viewHolder = null;
		
		if(null == convertView){
			convertView = getLayoutInflater().inflate(R.layout.shipment_as_contract_number_query_result_item, null);
			
			_viewHolder = new ViewHolder();
			_viewHolder.number = (TextView) convertView.findViewById(R.id.shipment_AsContractNum_value);
			_viewHolder.tonnage = (TextView) convertView.findViewById(R.id.shipment_AsContractNum_tonnage);
			_viewHolder.numberOfPackages = (TextView) convertView.findViewById(R.id.shipment_AsContractNum_numberOfPackages);
			
			convertView.setTag(_viewHolder);
		}else {
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		ShipmentBean _shipmentBean = (ShipmentBean)getItem(position);
		
		_viewHolder.number.setText(_shipmentBean.conCode);
		_viewHolder.tonnage.setText(_shipmentBean.weight);
		_viewHolder.numberOfPackages.setText(_shipmentBean.piece);
		
		return convertView;
	}

}
