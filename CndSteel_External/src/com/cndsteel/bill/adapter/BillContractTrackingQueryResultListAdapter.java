package com.cndsteel.bill.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.bean.BillBean;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class BillContractTrackingQueryResultListAdapter extends AbsBaseAdapter<BillBean> {
	
	public BillContractTrackingQueryResultListAdapter(Context context,ArrayList<BillBean> itemList) {
		super(context);
		initDatas(itemList);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.bill_contract_tracking_query_result, null);
			_holder.txtV_contractTrackingConCode = (TextView) view.findViewById(R.id.txtV_contractTrackingConCode);
			_holder.txtV_contractTrackingWzStatus = (TextView) view.findViewById(R.id.txtV_contractTrackingWzStatus);
			_holder.txtV_contractTrackingCustomerStatus = (TextView) view.findViewById(R.id.txtV_contractTrackingCustomerStatus);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		BillBean _bill = (BillBean) getItem(position);
		_holder.txtV_contractTrackingConCode.setText(_bill.txtV_contractInvoiceConCode);
		_holder.txtV_contractTrackingWzStatus.setText(_bill.txtV_contractTrackingWzStatus);
		_holder.txtV_contractTrackingCustomerStatus.setText(_bill.txtV_contractTrackingCustomerStatus);
		
		return view;
	}
	
	private class ViewHolder {
		
		/** 合同号 **/
		public TextView txtV_contractTrackingConCode;
		
		/** 建发收取状态 **/
		public TextView txtV_contractTrackingWzStatus;
		
		/** 我司收取状态 **/
		public TextView txtV_contractTrackingCustomerStatus;
		
	}

}
