package com.cndsteel.shipment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.shipment.bean.AsInvoiceQueryResultItem;

public class AsInvoiceQueryResultAdapter extends AbsBaseAdapter<AsInvoiceQueryResultItem> {

	public AsInvoiceQueryResultAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.shipment_as_invoice_number_query_result_item, null);
			_holder.number = (TextView) view.findViewById(R.id.shipment_AsInvoiceNum_value);
			_holder.tonnage = (TextView) view.findViewById(R.id.shipment_AsInvoiceNum_tonnage);
			_holder.numberOfPackages = (TextView) view.findViewById(R.id.shipment_AsInvoiceNum_numberOfPackages);
			_holder.warehouse = (TextView) view.findViewById(R.id.shipment_AsInvoiceNum_warehouse);
			_holder.date = (TextView) view.findViewById(R.id.shipment_AsInvoiceNum_date);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		AsInvoiceQueryResultItem _item = (AsInvoiceQueryResultItem) getItem(position);
		_holder.number.setText(_item.number);
		_holder.tonnage.setText(_item.tonnage);
		_holder.numberOfPackages.setText(_item.numberOfPackages);
		_holder.warehouse.setText(_item.warehouse);
		_holder.date.setText(_item.date);
		
		return view;
	}
	
	private class ViewHolder {
		public TextView number;
		public TextView tonnage;
		public TextView numberOfPackages;
		public TextView warehouse;
		public TextView date;
	}

}
