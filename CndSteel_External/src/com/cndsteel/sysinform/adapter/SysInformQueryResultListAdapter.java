package com.cndsteel.sysinform.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.sysinform.bean.SysInformBean;

public class SysInformQueryResultListAdapter extends AbsBaseAdapter<SysInformBean> {

	public SysInformQueryResultListAdapter(Context context) {
		super(context);
	}
	
	private class ViewHolder {
		public TextView txtV_sysInfoQueryResultListItemTitle;
		public TextView txtV_sysInfoQueryResultListItemDate;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder _viewHolder;
		if(null == view){		
			
			view = getLayoutInflater().inflate(R.layout.sysinform_list_item, parent,false);
			
			_viewHolder = new ViewHolder();
			_viewHolder.txtV_sysInfoQueryResultListItemTitle = (TextView) view.findViewById(R.id.txtV_sysInfoQueryResultListItemTitle);
			_viewHolder.txtV_sysInfoQueryResultListItemDate = (TextView) view.findViewById(R.id.txtV_sysInfoQueryResultListItemDate);
			
			view.setTag(_viewHolder);
			
		}else {
			_viewHolder = (ViewHolder) view.getTag();
		}
		
		SysInformBean _sysInform = (SysInformBean) getItem(position);
		_viewHolder.txtV_sysInfoQueryResultListItemTitle.setText(_sysInform.title);
		_viewHolder.txtV_sysInfoQueryResultListItemDate.setText(_sysInform.date);
		
		return view;
	}
	
}
