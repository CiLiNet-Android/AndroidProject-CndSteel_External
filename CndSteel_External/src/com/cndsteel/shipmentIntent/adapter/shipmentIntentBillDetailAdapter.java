package com.cndsteel.shipmentIntent.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.shipmentIntent.bean.ShipmentIntent;

public class shipmentIntentBillDetailAdapter extends AbsBaseAdapter<ShipmentIntent> {

	public shipmentIntentBillDetailAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.shipment_intent_bill_detail_item, null);
			_holder.goodsName = (TextView) view.findViewById(R.id.shipmentIntent_GoodsName);
			_holder.material = (TextView) view.findViewById(R.id.shipmentIntent_material);
			_holder.standard = (TextView) view.findViewById(R.id.shipmentIntent_standard);
			_holder.packageNum = (TextView) view.findViewById(R.id.shipmentIntent_packageNum);
			_holder.tonnage = (TextView) view.findViewById(R.id.shipmentIntent_tonnage);
			_holder.numberOfPackages = (TextView) view.findViewById(R.id.shipmentIntent_numberOfPackages);

			view.setTag(_holder);
		}else{
			_holder = (ViewHolder) view.getTag();
		}
		
		ShipmentIntent _item = (ShipmentIntent) getItem(position);		
		_holder.goodsName.setText(_item.goodsName);
		_holder.material.setText(_item.material);
		_holder.standard.setText(_item.standard);
		_holder.packageNum.setText(_item.packageNum);
		_holder.tonnage.setText(_item.tonnage);
		_holder.numberOfPackages.setText(_item.numberOfPackages);
		
		return view;
	}
	
	private class ViewHolder {
		public TextView goodsName;
		public TextView material;
		public TextView standard;
		public TextView packageNum;
		public TextView tonnage;
		public TextView numberOfPackages;
		
	}

}
