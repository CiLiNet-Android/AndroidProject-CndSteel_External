package com.cndsteel.contract.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.contract.bean.Contract_item;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class ContractTermsAdapter extends AbsBaseAdapter<Contract_item> {

	public ContractTermsAdapter(Context context,ArrayList<Contract_item> datas) {
		super(context);
		initDatas(datas);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.activity_contract_terms_item, null);
			_holder.num = (TextView) view.findViewById(R.id.contract_terms_Num);
			_holder.amountPaid = (TextView) view.findViewById(R.id.contract_terms_amountPaid);
			_holder.unpaidAmount = (TextView) view.findViewById(R.id.contract_terms_unpaidAmount);
			view.setTag(_holder);
		}else{
			_holder = (ViewHolder) view.getTag();
		}
		Contract_item _item = (Contract_item) getItem(position);
		_holder.num.setText(_item.num);
		_holder.amountPaid.setText(_item.amountPaid);
		_holder.unpaidAmount.setText(_item.unpaidAmount);
		
		view.setBackgroundResource(R.color.grey);
		
		return view;
	}
	
	private class ViewHolder{
		public TextView num;
		public TextView amountPaid;
		public TextView unpaidAmount;
	}

}
