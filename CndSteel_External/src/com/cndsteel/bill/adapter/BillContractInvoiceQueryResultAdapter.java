package com.cndsteel.bill.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.bean.Bill;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class BillContractInvoiceQueryResultAdapter extends AbsBaseAdapter<Bill> {

	public BillContractInvoiceQueryResultAdapter(Context context,ArrayList<Bill> itemList) {
		super(context);
		initDatas(itemList);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.bill_contract_invoice_query_result, null);
			_holder.title_contract_Num = (TextView) view.findViewById(R.id.title_contract_Num);
			_holder.contract_amountOfMoney = (TextView) view.findViewById(R.id.contract_amountOfMoney);
			_holder.alreadyMakeOutAnInvoiceAmount = (TextView) view.findViewById(R.id.alreadyMakeOutAnInvoiceAmount);
			_holder.HaveNotMakeOutAnInvoiceAmount = (TextView) view.findViewById(R.id.HaveNotMakeOutAnInvoiceAmount);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		Bill _bill = (Bill) getItem(position);
		_holder.title_contract_Num.setText(_bill.title_contract_Num);
		_holder.contract_amountOfMoney.setText(_bill.contract_amountOfMoney);
		_holder.alreadyMakeOutAnInvoiceAmount.setText(_bill.alreadyMakeOutAnInvoiceAmount);
		_holder.HaveNotMakeOutAnInvoiceAmount.setText(_bill.HaveNotMakeOutAnInvoiceAmount);
		
		return view;
	}
	
	private class ViewHolder {
		
		/** 合同号 **/
		public TextView title_contract_Num;
		
		/** 合同金额 **/
		public TextView contract_amountOfMoney;
		
		/** 已开票金额 **/
		public TextView alreadyMakeOutAnInvoiceAmount;
		
		/** 未开票金额 **/
		public TextView HaveNotMakeOutAnInvoiceAmount;
		
	}

}
