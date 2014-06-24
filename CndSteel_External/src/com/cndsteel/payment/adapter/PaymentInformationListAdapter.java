package com.cndsteel.payment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.payment.bean.Payment;

public class PaymentInformationListAdapter extends AbsBaseAdapter<Payment> {
	
	private Context context;

	public PaymentInformationListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.payment_information_list_item , null);
			_holder.payment_informationItem_BillNum = (TextView) view.findViewById(R.id.payment_informationItem_BillNum);
			_holder.payment_informationItem_payDate = (TextView) view.findViewById(R.id.payment_informationItem_payDate);
			_holder.payment_informationItem_paymentMethod = (TextView) view.findViewById(R.id.payment_informationItem_paymentMethod);
			_holder.payment_informationItem_money = (TextView) view.findViewById(R.id.payment_informationItem_money);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		Payment _payment = (Payment) getItem(position);
		_holder.payment_informationItem_BillNum.setText(_payment.billNum);
		_holder.payment_informationItem_payDate.setText(_payment.paymentDate);
		_holder.payment_informationItem_paymentMethod.setText(_payment.paymentMethod);
		_holder.payment_informationItem_money.setText(_payment.money);
		
		return view;
	}
	
	private class ViewHolder {
		public TextView payment_informationItem_BillNum;
		public TextView payment_informationItem_payDate;
		public TextView payment_informationItem_paymentMethod;
		public TextView payment_informationItem_money;
	}

}
