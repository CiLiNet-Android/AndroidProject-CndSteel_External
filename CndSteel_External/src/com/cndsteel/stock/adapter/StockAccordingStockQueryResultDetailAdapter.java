package com.cndsteel.stock.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.stock.bean.StockBean;

public class StockAccordingStockQueryResultDetailAdapter extends AbsBaseAdapter<StockBean> {

	public StockAccordingStockQueryResultDetailAdapter(Context context) {
		super(context);
	}

	private class ViewHolder {
		private TextView stock_detail_into_date;
		private TextView stock_detail_tonnage;
		private TextView stock_detail_numberOfPackages;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _viewHolder;
		if(null == convertView){
			_viewHolder = new ViewHolder();
			convertView = getLayoutInflater().inflate(R.layout.layout_stock_query_result_detail_item, null);
			_viewHolder.stock_detail_into_date = (TextView) convertView.findViewById(R.id.stock_detail_into_date);
			_viewHolder.stock_detail_tonnage = (TextView) convertView.findViewById(R.id.stock_detail_tonnage);
			_viewHolder.stock_detail_numberOfPackages = (TextView) convertView.findViewById(R.id.stock_detail_numberOfPackages);
			convertView.setTag(_viewHolder);
		}else{
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		StockBean _stockBean = (StockBean) getItem(position);
		_viewHolder.stock_detail_into_date.setText(_stockBean.storeDate);
		_viewHolder.stock_detail_tonnage.setText(_stockBean.weight);
		_viewHolder.stock_detail_numberOfPackages.setText(_stockBean.piece);
		
		return convertView;
	}

}
