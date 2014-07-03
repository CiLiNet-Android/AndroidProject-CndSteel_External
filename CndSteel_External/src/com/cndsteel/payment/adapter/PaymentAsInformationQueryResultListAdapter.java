package com.cndsteel.payment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.payment.bean.PaymentBean;

public class PaymentAsInformationQueryResultListAdapter extends AbsBaseAdapter<PaymentBean> {

	public PaymentAsInformationQueryResultListAdapter(Context context) {
		super(context);
	}

	private class ViewHolder {
		public TextView payment_informationItem_BillNum;
		public TextView payment_informationItem_payDate;
		public TextView payment_informationItem_paymentMethod;
		public TextView payment_informationItem_money;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _viewHolder = null;
		if(null == convertView){
			_viewHolder = new ViewHolder();
			
			convertView = getLayoutInflater().inflate(R.layout.payment_information_list_item , parent,false);
			_viewHolder.payment_informationItem_BillNum = (TextView) convertView.findViewById(R.id.payment_informationItem_BillNum);
			_viewHolder.payment_informationItem_payDate = (TextView) convertView.findViewById(R.id.payment_informationItem_payDate);
			_viewHolder.payment_informationItem_paymentMethod = (TextView) convertView.findViewById(R.id.payment_informationItem_paymentMethod);
			_viewHolder.payment_informationItem_money = (TextView) convertView.findViewById(R.id.payment_informationItem_money);
			
			convertView.setTag(_viewHolder);
		}else {
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		PaymentBean _paymentBean = (PaymentBean) getItem(position);
		_viewHolder.payment_informationItem_BillNum.setText(_paymentBean.billNO);
		_viewHolder.payment_informationItem_payDate.setText(_paymentBean.payDate);
		_viewHolder.payment_informationItem_paymentMethod.setText(_paymentBean.payMode);
		_viewHolder.payment_informationItem_money.setText(_paymentBean.amount);
		
		return convertView;
	}

}
