package com.cndsteel.bill.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.bean.Bill;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class BillContractTrackingQueryResultAdapter extends AbsBaseAdapter<Bill> {
	
	public BillContractTrackingQueryResultAdapter(Context context,ArrayList<Bill> itemList) {
		super(context);
		initDatas(itemList);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.bill_contract_tracking_query_result, null);
			_holder.title_contract_Num = (TextView) view.findViewById(R.id.title_contract_Num);
			_holder.JianFaGetState = (TextView) view.findViewById(R.id.JianFaGetState);
			_holder.OurCompanyGetState = (TextView) view.findViewById(R.id.ourCompanyGetState);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		Bill _bill = (Bill) getItem(position);
		_holder.title_contract_Num.setText(_bill.title_contract_Num);
		_holder.JianFaGetState.setText(_bill.JianFaGetState);
		_holder.OurCompanyGetState.setText(_bill.OurCompanyGetState);
		
		return view;
	}
	
	private class ViewHolder {
		
		/** 合同号 **/
		public TextView title_contract_Num;
		
		/** 建发收取状态 **/
		public TextView JianFaGetState;
		
		/** 我司收取状态 **/
		public TextView OurCompanyGetState;
		
	}

}
