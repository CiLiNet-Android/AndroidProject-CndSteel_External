package com.cndsteel.sysinform.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.sysinform.bean.SysInform;

public class SysInformAdapter extends AbsBaseAdapter<SysInform> {

	public SysInformAdapter(Context context,ArrayList<SysInform> viewList) {
		super(context);
		initDatas(viewList);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
//			view = getLayoutInflater().inflate(R.layout.info_list_item, null);
//			_holder.title = (TextView) view.findViewById(R.id.title);
//			_holder.date = (TextView) view.findViewById(R.id.date);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		
		SysInform _sysInform = (SysInform) getItem(position);
		_holder.title.setText(_sysInform.title);
		_holder.date.setText(_sysInform.date);
		
		return view;
	}
	
	private class ViewHolder {
		public TextView title;
		public TextView date;
	}
}
