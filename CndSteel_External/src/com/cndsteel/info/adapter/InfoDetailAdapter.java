package com.cndsteel.info.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.info.bean.Info;

public class InfoDetailAdapter extends AbsBaseAdapter<Info> {

	public InfoDetailAdapter(Context context,ArrayList<Info> viewList) {
		super(context);
		initDatas(viewList);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.info_detail_item, null);
			_holder.userName = (TextView) view.findViewById(R.id.commentsUserName);
			_holder.date = (TextView) view.findViewById(R.id.commentsTime);
			_holder.content = (TextView) view.findViewById(R.id.commentsContent);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		
		Info _info = (Info) getItem(position);
		_holder.userName.setText(_info.name);
		_holder.date.setText(_info.date);
		_holder.content.setText(_info.comments);
		return view;
	}
	
	private class ViewHolder {
		public TextView userName;
		public TextView date;
		public TextView content;
	}
	
}
