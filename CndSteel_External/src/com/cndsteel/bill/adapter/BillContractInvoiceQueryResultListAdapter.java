package com.cndsteel.bill.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.bean.BillBean;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class BillContractInvoiceQueryResultListAdapter extends AbsBaseAdapter<BillBean> {

	public BillContractInvoiceQueryResultListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder = null;
		
		if(null == view){
			view = getLayoutInflater().inflate(R.layout.bill_contract_invoice_query_result, null);
			
			_holder = new ViewHolder();	
			_holder.txtV_contractInvoiceConCode = (TextView) view.findViewById(R.id.txtV_contractInvoiceConCode);
			_holder.txtV_contractInvoiceConAmt = (TextView) view.findViewById(R.id.txtV_contractInvoiceConAmt);
			_holder.txtV_contractInvoiceInvedAmt = (TextView) view.findViewById(R.id.txtV_contractInvoiceInvedAmt);
			_holder.txtV_contractInvoiceUninvAmt = (TextView) view.findViewById(R.id.txtV_contractInvoiceUninvAmt);
			
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		
		BillBean _bill = (BillBean) getItem(position);
		_holder.txtV_contractInvoiceConCode.setText(_bill.conCode);
		_holder.txtV_contractInvoiceConAmt.setText(_bill.conAmt);
		_holder.txtV_contractInvoiceInvedAmt.setText(_bill.invedAmt);
		_holder.txtV_contractInvoiceUninvAmt.setText(_bill.uninvAmt);
		
		return view;
	}
	
	private class ViewHolder {
		
		/** 合同号 **/
		public TextView txtV_contractInvoiceConCode;
		
		/** 合同金额 **/
		public TextView txtV_contractInvoiceConAmt;
		
		/** 已开票金额 **/
		public TextView txtV_contractInvoiceInvedAmt;
		
		/** 未开票金额 **/
		public TextView txtV_contractInvoiceUninvAmt;
		
	}

}
