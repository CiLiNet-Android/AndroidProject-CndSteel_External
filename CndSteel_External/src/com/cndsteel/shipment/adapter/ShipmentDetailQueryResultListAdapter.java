package com.cndsteel.shipment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.shipment.bean.ShipmentBean;
import com.cndsteel.stock.bean.StockBean;

public class ShipmentDetailQueryResultListAdapter extends AbsBaseAdapter<ShipmentBean> {

	public ShipmentDetailQueryResultListAdapter(Context context) {
		super(context);
	}

	private class ViewHolder {
		private TextView stock_detail_list_packages_no;
		private TextView stock_detail_list_tonnage;
		private TextView stock_detail_list_numberOfPackages;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _viewHolder;
		if(null == convertView){
			_viewHolder = new ViewHolder();
			convertView = getLayoutInflater().inflate(R.layout.layout_stock_query_result_detail_list_item, null);
			_viewHolder.stock_detail_list_packages_no = (TextView) convertView.findViewById(R.id.stock_detail_list_packages_no);
			_viewHolder.stock_detail_list_tonnage = (TextView) convertView.findViewById(R.id.stock_detail_list_tonnage);
			_viewHolder.stock_detail_list_numberOfPackages = (TextView) convertView.findViewById(R.id.stock_detail_list_numberOfPackages);
			convertView.setTag(_viewHolder);
		}else{
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		StockBean _stockBean = (StockBean) getItem(position);
		_viewHolder.stock_detail_list_packages_no.setText(_stockBean.pkgNo);
		_viewHolder.stock_detail_list_tonnage.setText(_stockBean.weight);
		_viewHolder.stock_detail_list_numberOfPackages.setText(_stockBean.piece);
		
		return convertView;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}


}
