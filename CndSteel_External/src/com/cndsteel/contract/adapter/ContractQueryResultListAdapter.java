package com.cndsteel.contract.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.contract.bean.Contract_item;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class ContractQueryResultListAdapter extends AbsBaseAdapter<Contract_item> {

	public ContractQueryResultListAdapter(Context context,ArrayList<Contract_item> datas) {
		super(context);
		
		initDatas(datas);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.activity_contract_query_result_listview_item, null);
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
		Contract_item _item = (Contract_item) getItem(position);
		_holder.contract_item_Num.setText(_item.num);
		_holder.contract_item_thEnd.setText(_item.isEnd);
		_holder.contract_item_date.setText(_item.date);
		_holder.contract_item_pipe.setText(_item.goodsName);
		_holder.contract_item_tonnage.setText(_item.tonnage);
		_holder.contract_item_PickUpGoods.setText(_item.PickUpGoods);
		
		view.setBackgroundResource(R.color.grey);
		
		return view;
	}
	
	private class ViewHolder{
		
		public TextView contract_item_Num,contract_item_thEnd,contract_item_date,contract_item_pipe,contract_item_tonnage,contract_item_PickUpGoods;
		
	}

}
