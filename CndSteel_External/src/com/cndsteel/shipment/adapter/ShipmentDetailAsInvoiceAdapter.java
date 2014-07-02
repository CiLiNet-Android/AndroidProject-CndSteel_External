package com.cndsteel.shipment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.shipment.bean.ShipmentAsInvoice;

public class ShipmentDetailAsInvoiceAdapter extends AbsBaseAdapter<ShipmentAsInvoice> {

	public ShipmentDetailAsInvoiceAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.layout_stock_according_stock_query_result_child, null);
			_holder.stock_goodsName = (TextView) view.findViewById(R.id.stock_goodsName);
			_holder.stock_Material = (TextView) view.findViewById(R.id.stock_Material);
			_holder.stock_Standard = (TextView) view.findViewById(R.id.stock_Standard);
			_holder.stock_Tonnage = (TextView) view.findViewById(R.id.stock_Tonnage);
			_holder.stock_NumberOfPackages = (TextView) view.findViewById(R.id.stock_NumberOfPackages);
			_holder.stock_Value = (TextView) view.findViewById(R.id.stock_Value);
			
			_holder.title_Hide = (TextView) view.findViewById(R.id.title_Hide);
			
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		ShipmentAsInvoice _item = (ShipmentAsInvoice) getItem(position);
		_holder.stock_goodsName.setText(_item.goodsName);
		_holder.stock_Material.setText(_item.material);
		_holder.stock_Standard.setText(_item.standard);
		_holder.stock_Tonnage.setText(_item.tonnage);
		_holder.stock_NumberOfPackages.setText(_item.numberOfPackages);
		_holder.stock_Value.setText(_item.money);
		
		_holder.stock_Value.setVisibility(View.VISIBLE);
		_holder.title_Hide.setText(getContext().getResources().getString(R.string.Money));
		_holder.title_Hide.setVisibility(View.VISIBLE);
		return view;
	}
	
	private class ViewHolder {
		
		public TextView stock_goodsName;
		public TextView stock_Material;
		public TextView stock_Standard;
		public TextView stock_Tonnage;
		public TextView stock_NumberOfPackages;
		public TextView title_Hide;
		public TextView stock_Value;
	}

}
