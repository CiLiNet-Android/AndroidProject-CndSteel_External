package com.cndsteel.plan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.plan.bean.PlanBean;

public class PlanQueryResultListAdapter extends AbsBaseAdapter<PlanBean> {

	public PlanQueryResultListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		ViewHolder _holder;
		
		if(null == view){
			
			_holder = new ViewHolder();
			
			view = getLayoutInflater().inflate(R.layout.activity_plan_query_result_list_item, null);
			_holder.plan_year_month = (TextView) view.findViewById(R.id.plan_year_month);
			_holder.plan_reserve_reality = (TextView) view.findViewById(R.id.plan_reserve_reality);
			_holder.plan_must_get_pledge_money = (TextView) view.findViewById(R.id.plan_must_get_pledge_money);
			_holder.plan_must_get_pledge_date = (TextView) view.findViewById(R.id.plan_must_get_pledge_date);
			_holder.plan_isEnd = (TextView) view.findViewById(R.id.plan_item_thEnd);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		
		PlanBean _plan = (PlanBean) getItem(position);
		
//		_holder.plan_year_month.setText(_plan.plan_year_month);
//		_holder.plan_reserve_reality.setText(_plan.plan_reserve_reality);
//		_holder.plan_must_get_pledge_money.setText(_plan.plan_must_get_pledge_money);
//		_holder.plan_must_get_pledge_date.setText(_plan.plan_must_get_pledge_date);
//		_holder.plan_isEnd.setText(_plan.plan_isEnd);
		
		return view;
	}
	
	private class ViewHolder {
		public TextView plan_year_month;
		public TextView plan_reserve_reality;
		public TextView plan_must_get_pledge_money;
		public TextView plan_must_get_pledge_date;
		public TextView plan_isEnd;
	}

}
