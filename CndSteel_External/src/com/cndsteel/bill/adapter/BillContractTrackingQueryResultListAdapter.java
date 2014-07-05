package com.cndsteel.bill.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.bean.BillBean;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class BillContractTrackingQueryResultListAdapter extends AbsBaseAdapter<BillBean> {
	
	public BillContractTrackingQueryResultListAdapter(Context context) {
		super(context);
	}
	
	private class ViewHolder {
		/** 合同号 **/
		public TextView txtV_contractTrackingConCode;
		/** 建发收取状态 **/
		public TextView txtV_contractTrackingWzStatus;
		/** 我司收取状态 **/
		public TextView txtV_contractTrackingCustomerStatus;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _viewHolder = null;
		if(null == convertView){
			convertView = getLayoutInflater().inflate(R.layout.bill_contract_tracking_query_result, null);
			
			_viewHolder = new ViewHolder();
			_viewHolder.txtV_contractTrackingConCode = (TextView) convertView.findViewById(R.id.txtV_contractTrackingConCode);
			_viewHolder.txtV_contractTrackingWzStatus = (TextView) convertView.findViewById(R.id.txtV_contractTrackingWzStatus);
			_viewHolder.txtV_contractTrackingCustomerStatus = (TextView) convertView.findViewById(R.id.txtV_contractTrackingCustomerStatus);
			
			convertView.setTag(_viewHolder);
		}else {
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		BillBean _billBean = (BillBean) getItem(position);
		
		_viewHolder.txtV_contractTrackingConCode.setText(_billBean.conCode);
		_viewHolder.txtV_contractTrackingWzStatus.setText(_billBean.wzStatus);
		_viewHolder.txtV_contractTrackingCustomerStatus.setText(_billBean.customerStatus);
		
		return convertView;
	}
	
	

}
