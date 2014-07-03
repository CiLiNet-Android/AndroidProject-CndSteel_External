package com.cndsteel.payment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.payment.bean.PaymentBean;

public class PaymentAsContractQueryResultListAdapter extends AbsBaseAdapter<PaymentBean> {

	public PaymentAsContractQueryResultListAdapter(Context context) {
		super(context);
	}
	
	private class ViewHolder{
		public TextView contractNum;
		public TextView amountPaid;
		public TextView unpaidAmount;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _viewHolder;
		
		if(null == convertView){
			convertView = getLayoutInflater().inflate(R.layout.payment_as_contract_query_result_item, null);
			
			_viewHolder = new ViewHolder();
			_viewHolder.contractNum = (TextView) convertView.findViewById(R.id.payment_contractNum);
			_viewHolder.amountPaid = (TextView) convertView.findViewById(R.id.contract_terms_amountPaid);
			_viewHolder.unpaidAmount = (TextView) convertView.findViewById(R.id.contract_terms_unpaidAmount);
			
			convertView.setTag(_viewHolder);
		}else{
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		PaymentBean _paymentBean = (PaymentBean)getItem(position);
		
		_viewHolder.contractNum.setText(_paymentBean.conCode);
		_viewHolder.amountPaid.setText(_paymentBean.paidAmount);
		_viewHolder.unpaidAmount.setText(_paymentBean.needAmount);
		
		convertView.setBackgroundResource(R.color.grey);
		
		return convertView;
	}

}
