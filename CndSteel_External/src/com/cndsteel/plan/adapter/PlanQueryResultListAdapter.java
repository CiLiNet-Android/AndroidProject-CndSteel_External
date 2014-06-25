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
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder _viewHolder = null;;
		if(null == convertView){
			convertView = getLayoutInflater().inflate(R.layout.activity_plan_query_result_list_item, null);
			
			_viewHolder = new ViewHolder();
			_viewHolder.plan_year_month = (TextView) convertView.findViewById(R.id.plan_year_month);
			_viewHolder.plan_reserve_reality = (TextView) convertView.findViewById(R.id.plan_reserve_reality);
			_viewHolder.plan_must_get_pledge_money = (TextView) convertView.findViewById(R.id.plan_must_get_pledge_money);
			_viewHolder.plan_must_get_pledge_date = (TextView) convertView.findViewById(R.id.plan_must_get_pledge_date);
			_viewHolder.plan_isEnd = (TextView) convertView.findViewById(R.id.plan_item_thEnd);
			
			convertView.setTag(_viewHolder);
		}else {
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		PlanBean _plan = (PlanBean) getItem(position);
		
		_viewHolder.plan_year_month.setText(_plan.schDate);
		_viewHolder.plan_reserve_reality.setText(_plan.weight + "/" + _plan.actWeight);
		_viewHolder.plan_must_get_pledge_money.setText(_plan.receivedMargin);
		_viewHolder.plan_must_get_pledge_date.setText(_plan.receivedMarginDate);
		_viewHolder.plan_isEnd.setText(_plan.status);
		
		return convertView;
	}
	
	private class ViewHolder {
		public TextView plan_year_month;
		public TextView plan_reserve_reality;
		public TextView plan_must_get_pledge_money;
		public TextView plan_must_get_pledge_date;
		public TextView plan_isEnd;
	}

}
