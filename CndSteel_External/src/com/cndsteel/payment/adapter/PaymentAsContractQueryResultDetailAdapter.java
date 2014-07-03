package com.cndsteel.payment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.framework.utils.DateUtils;
import com.cndsteel.payment.bean.PaymentBean;

public class PaymentAsContractQueryResultDetailAdapter extends AbsBaseAdapter<PaymentBean> {

	public PaymentAsContractQueryResultDetailAdapter(Context context) {
		super(context);
	}

	private class ViewHolder {
		public TextView txtV_contractTermsAmountDate;
		public TextView txtV_contractTermspaidAmount;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _viewHolder = null;
		
		if(null == convertView){
			convertView = getLayoutInflater().inflate(R.layout.activity_contract_terms_item, null);
			
			_viewHolder = new ViewHolder();
			_viewHolder.txtV_contractTermsAmountDate = (TextView) convertView.findViewById(R.id.txtV_contractTermsAmountDate);
			_viewHolder.txtV_contractTermspaidAmount = (TextView) convertView.findViewById(R.id.txtV_contractTermspaidAmount);
			
			convertView.setTag(_viewHolder);
		}else{
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		PaymentBean _paymentBean = (PaymentBean)getItem(position);
		
		_viewHolder.txtV_contractTermsAmountDate.setText(DateUtils.getFormatDateTime(DateUtils.getDate(_paymentBean.payDate, "yyyy-MM-dd HH:mm:ss"), "yyyy/MM/dd"));
		_viewHolder.txtV_contractTermspaidAmount.setText(_paymentBean.amount);
		
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
