package com.cndsteel.shipment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.shipment.bean.QueryResultItem;

public class AsContractNumQueryResultAdapter extends AbsBaseAdapter<QueryResultItem> {

	public AsContractNumQueryResultAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.shipment_as_contract_number_query_result_item, null);
			_holder.number = (TextView) view.findViewById(R.id.shipment_AsContractNum_value);
			_holder.tonnage = (TextView) view.findViewById(R.id.shipment_AsContractNum_tonnage);
			_holder.numberOfPackages = (TextView) view.findViewById(R.id.shipment_AsContractNum_numberOfPackages);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		QueryResultItem _item = (QueryResultItem) getItem(position);
		_holder.number.setText(_item.number);
		_holder.tonnage.setText(_item.tonnage);
		_holder.numberOfPackages.setText(_item.numberOfPackages);
		
		return view;
	}
	
	private class ViewHolder {
		public TextView number;
		public TextView tonnage;
		public TextView numberOfPackages;
	}

}
