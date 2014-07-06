package com.cndsteel.bill.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.bean.BillBean;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class BillContractInvoiceQueryResulltDetailAdapter extends AbsBaseAdapter<BillBean> {

	public BillContractInvoiceQueryResulltDetailAdapter(Context context) {
		super(context);
	}
	
	private class ViewHolder {
		public TextView txtV_contractInvoiceDetailInvNO;
		public TextView txtV_contractInvoiceDetailInvDate;
		public TextView txtV_contractInvoiceDetailAmount;
		public TextView txtV_contractInvoiceDetailSignStatus;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder _viewHolder = null;
		
		if(null == convertView){
			convertView = getLayoutInflater().inflate(R.layout.bill_contract_invoice_query_result_detail_item,null);
			
			_viewHolder = new ViewHolder();
			_viewHolder.txtV_contractInvoiceDetailInvNO = (TextView) convertView.findViewById(R.id.txtV_contractInvoiceDetailInvNO);
			_viewHolder.txtV_contractInvoiceDetailInvDate = (TextView) convertView.findViewById(R.id.txtV_contractInvoiceDetailInvDate);
			_viewHolder.txtV_contractInvoiceDetailAmount = (TextView) convertView.findViewById(R.id.txtV_contractInvoiceDetailAmount);
			_viewHolder.txtV_contractInvoiceDetailSignStatus = (TextView) convertView.findViewById(R.id.txtV_contractInvoiceDetailSignStatus);
			
			convertView.setTag(_viewHolder);
		}else {
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		BillBean _billBean = (BillBean) getItem(position);
		_viewHolder.txtV_contractInvoiceDetailInvNO.setText(_billBean.invNO);
		_viewHolder.txtV_contractInvoiceDetailInvDate.setText(_billBean.invDate);
		_viewHolder.txtV_contractInvoiceDetailAmount.setText(_billBean.amount);
		_viewHolder.txtV_contractInvoiceDetailSignStatus.setText(_billBean.signStatus);
		
		return convertView;
	}

}
