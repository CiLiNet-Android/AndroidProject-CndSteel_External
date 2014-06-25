package com.cndsteel.shipmentIntent.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.shipmentIntent.bean.ShipmentIntent;

public class ShipmentIntentViewPagerListViewAdapter extends AbsBaseAdapter<ShipmentIntent> {
	
	public ShipmentIntentViewPagerListViewAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.to_pick_up_the_goods_intent_item, null);
			_holder.goodsName = (TextView) view.findViewById(R.id.shipmentIntentGoodsName);
			_holder.material = (TextView) view.findViewById(R.id.shipmentIntentMaterial);
			_holder.standard = (TextView) view.findViewById(R.id.shipmentIntentStandard);
			_holder.tonnage = (TextView) view.findViewById(R.id.shipmentIntentTonnage);
			_holder.numberOfPackages = (TextView) view.findViewById(R.id.shipmentIntentNumberOfPackages);
			_holder.evoke = (ImageView) view.findViewById(R.id.shipmentIntentEvoke);

			view.setTag(_holder);
		}else{
			_holder = (ViewHolder) view.getTag();
		}
		
		ShipmentIntent _item = (ShipmentIntent) getItem(position);		
		_holder.goodsName.setText(_item.goodsName);
		_holder.material.setText(_item.material);
		_holder.standard.setText(_item.standard);
		_holder.tonnage.setText(_item.tonnage);
		_holder.numberOfPackages.setText(_item.numberOfPackages);
		_holder.evoke.setImageResource(_item.imgResId);
		
		return view;
	}
	
	private class ViewHolder {
		public TextView goodsName;
		public TextView material;
		public TextView standard;
		public TextView tonnage;
		public TextView numberOfPackages;
		public ImageView evoke;
		
	}

}
