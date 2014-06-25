package com.cndsteel.contract.adapters;

import java.util.Date;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.contract.beans.ContractBean;
import com.cndsteel.contract.enums.ContractStatus;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.framework.utils.DateUtils;

public class ContractQueryResultListAdapter extends AbsBaseAdapter<ContractBean> {
	
	public ContractQueryResultListAdapter(Context context){
		super(context);
	}

	
	private class ViewHolder{
		public TextView contract_item_Num;
		public TextView contract_item_thEnd;
		public TextView contract_item_date;
		public TextView contract_item_pipe;
		public TextView contract_item_tonnage;
		public TextView contract_item_PickUpGoods;	
	}
	
	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.activity_contract_query_result_list_item, null);
			_holder.contract_item_Num = (TextView) view.findViewById(R.id.contract_item_Num);
			_holder.contract_item_thEnd = (TextView) view.findViewById(R.id.contract_item_thEnd);
			_holder.contract_item_date = (TextView) view.findViewById(R.id.contract_item_date);
			_holder.contract_item_pipe = (TextView) view.findViewById(R.id.contract_item_pipe);
			_holder.contract_item_tonnage = (TextView) view.findViewById(R.id.contract_item_tonnage);
			_holder.contract_item_PickUpGoods = (TextView) view.findViewById(R.id.contract_item_PickUpGoods);
			
			view.setTag(_holder);
		}else{
			_holder = (ViewHolder) view.getTag();
		}
		ContractBean _item = (ContractBean) getItem(position);
		_holder.contract_item_Num.setText(_item.conCode);
		
		ContractStatus _contractStatus = ContractStatus.valueOf(_item.status);
		_holder.contract_item_thEnd.setText(_contractStatus.value());
		
		Date _conDate = DateUtils.getDate(_item.conDate, "yyyy-MM-dd HH:mm:ss");
		_holder.contract_item_date.setText(DateUtils.getFormatDateTime(_conDate, "yyyy/MM/dd"));
		
		_holder.contract_item_pipe.setText(_item.pname);
		_holder.contract_item_tonnage.setText(_item.weight);
		_holder.contract_item_PickUpGoods.setText(_item.shipWeight + "/" + _item.unshipWeight);
		
		return view;
	}

}
