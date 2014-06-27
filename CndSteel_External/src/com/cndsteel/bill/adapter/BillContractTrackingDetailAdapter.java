package com.cndsteel.bill.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.bean.BillContractTrackingDetailItem;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class BillContractTrackingDetailAdapter extends AbsBaseAdapter<BillContractTrackingDetailItem> {
	
	public BillContractTrackingDetailAdapter(Context context,ArrayList<BillContractTrackingDetailItem> itemList) {
		super(context);
		initDatas(itemList);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.bill_contract_tracking_query_result_item, null);
			_holder.sendOffCompany = (TextView) view.findViewById(R.id.sendOffCompany);
			_holder.expressCompany = (TextView) view.findViewById(R.id.expressCompany);
			_holder.expressNumber = (TextView) view.findViewById(R.id.expressNumber);
			_holder.expressTime = (TextView) view.findViewById(R.id.expressTime);
			_holder.addressee = (TextView) view.findViewById(R.id.addressee);
			_holder.signForPeople = (TextView) view.findViewById(R.id.signForPeople);
			_holder.signForTime = (TextView) view.findViewById(R.id.signForTime);
			_holder.Comment = (TextView) view.findViewById(R.id.Comment);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		BillContractTrackingDetailItem _bill = (BillContractTrackingDetailItem) getItem(position);
		_holder.sendOffCompany.setText(_bill.sendOffCompany);
		_holder.expressCompany.setText(_bill.expressCompany);
		_holder.expressNumber.setText(_bill.expressNumber);
		_holder.expressTime.setText(_bill.expressTime);
		_holder.addressee.setText(_bill.addressee);
		_holder.signForPeople.setText(_bill.signForPeople);
		_holder.signForTime.setText(_bill.signForTime);
		_holder.Comment.setText(_bill.Comment);
		return view;
	}
	
	private class ViewHolder {
		/** 发送方 **/
		public TextView sendOffCompany;

		/** 快递公司 **/
		public TextView expressCompany;
		
		/** 快递单号 **/
		public TextView expressNumber;
		
		/** 快递时间 **/
		public TextView expressTime;
		
		/** 收件人 **/
		public TextView addressee;
		
		/** 签收人 **/
		public TextView signForPeople;
		
		/** 签收时间 **/
		public TextView signForTime;
		
		/** 备注 **/
		public TextView Comment;
		
	}

}
