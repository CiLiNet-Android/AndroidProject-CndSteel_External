package com.cndsteel.plan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class PlanQueryResultListAdapter extends AbsBaseAdapter<String> {

	public PlanQueryResultListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		TextView _txtV = new TextView(getContext());
		_txtV.setText((String)getItem(position));
		
		return _txtV;
	}

}
