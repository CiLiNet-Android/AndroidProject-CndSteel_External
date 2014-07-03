package com.cndsteel.payment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.payment.bean.PaymentBean;

public class PaymentAsInformetionQueryResultDetailAdapter extends AbsBaseAdapter<PaymentBean> {

	public PaymentAsInformetionQueryResultDetailAdapter(Context context) {
		super(context);
	}
	
	private class ViewHolder {
		public TextView payment_asInformation_contractNumber;
		public TextView payment_asInformation_paymentDate;
		public TextView payment_asInformation_money;
		public TextView payment_asInformation_use;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder _viewHolder;
		if(null == convertView){
			convertView = getLayoutInflater().inflate(R.layout.payment_detail_as_information_item , null);
			
			_viewHolder = new ViewHolder();
			_viewHolder.payment_asInformation_contractNumber = (TextView) convertView.findViewById(R.id.payment_asInformation_contractNumber);
			_viewHolder.payment_asInformation_paymentDate = (TextView) convertView.findViewById(R.id.payment_asInformation_paymentDate);
			_viewHolder.payment_asInformation_money = (TextView) convertView.findViewById(R.id.payment_asInformation_money);
			_viewHolder.payment_asInformation_use = (TextView) convertView.findViewById(R.id.payment_asInformation_use);
			
			convertView.setTag(_viewHolder);
		}else {
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		PaymentBean _paymentBean = (PaymentBean) getItem(position);
		
		_viewHolder.payment_asInformation_contractNumber.setText(_paymentBean.conCode);
		_viewHolder.payment_asInformation_paymentDate.setText(_paymentBean.payDate);
		_viewHolder.payment_asInformation_money.setText(_paymentBean.amount);
		_viewHolder.payment_asInformation_use.setText(_paymentBean.expense);
		
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
