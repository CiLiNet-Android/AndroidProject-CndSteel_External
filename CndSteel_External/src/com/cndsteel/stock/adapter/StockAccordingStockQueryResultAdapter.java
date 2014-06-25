package com.cndsteel.stock.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.stock.bean.Stock;

public class StockAccordingStockQueryResultAdapter extends AbsBaseAdapter<Stock> {

	public StockAccordingStockQueryResultAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.activity_stock_according_stock_query_result_item, null);
			_holder.stock_goodsName = (TextView) view.findViewById(R.id.stock_goodsName);
			_holder.stock_Material = (TextView) view.findViewById(R.id.stock_Material);
			_holder.stock_Standard = (TextView) view.findViewById(R.id.stock_Standard);
			_holder.stock_Tonnage = (TextView) view.findViewById(R.id.stock_Tonnage);
			_holder.stock_NumberOfPackages = (TextView) view.findViewById(R.id.stock_NumberOfPackages);
			view.setTag(_holder);
		}else{
			_holder = (ViewHolder) view.getTag();
		}
		
		Stock _stock = (Stock) getItem(position);
		_holder.stock_goodsName.setText(_stock.goodsName);
		_holder.stock_Material.setText(_stock.material);
		_holder.stock_Standard.setText(_stock.standard);
		_holder.stock_Tonnage.setText(_stock.tonnage);
		_holder.stock_NumberOfPackages.setText(_stock.numberOfPackages);
		
		return view;
	}
	
	private class ViewHolder {
		
		public TextView stock_goodsName;
		public TextView stock_Material;
		public TextView stock_Standard;
		public TextView stock_Tonnage;
		public TextView stock_NumberOfPackages;
		
	}

}
