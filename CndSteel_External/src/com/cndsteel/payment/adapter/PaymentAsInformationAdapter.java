package com.cndsteel.payment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.payment.bean.Payment;

public class PaymentAsInformationAdapter extends AbsBaseAdapter<Payment> {

	public PaymentAsInformationAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.payment_detail_as_information_item , null);
			_holder.payment_asInformation_contractNumber = (TextView) view.findViewById(R.id.payment_asInformation_contractNumber);
			_holder.payment_asInformation_paymentDate = (TextView) view.findViewById(R.id.payment_asInformation_paymentDate);
			_holder.payment_asInformation_money = (TextView) view.findViewById(R.id.payment_asInformation_money);
			_holder.payment_asInformation_use = (TextView) view.findViewById(R.id.payment_asInformation_use);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		Payment _payment = (Payment) getItem(position);
		_holder.payment_asInformation_contractNumber.setText(_payment.contractNum);
		_holder.payment_asInformation_paymentDate.setText(_payment.paymentDate);
		_holder.payment_asInformation_money.setText(_payment.money);
		_holder.payment_asInformation_use.setText(_payment.use);
		
		return view;
	}
	
	private class ViewHolder {
		public TextView payment_asInformation_contractNumber;
		public TextView payment_asInformation_paymentDate;
		public TextView payment_asInformation_money;
		public TextView payment_asInformation_use;
	}

}
