package com.cndsteel.info.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.info.bean.Info;

public class InfoAdapter extends AbsBaseAdapter<Info> {

	public InfoAdapter(Context context,ArrayList<Info> list) {
		super(context);
		initDatas(list);
	}
 
	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder _holder;
		if(null == view){
			_holder = new ViewHolder();
			view = getLayoutInflater().inflate(R.layout.info_list_item, null);
			_holder.title = (TextView) view.findViewById(R.id.title);
			_holder.date = (TextView) view.findViewById(R.id.date);
			_holder.name = (TextView) view.findViewById(R.id.name);
			_holder.comments = (TextView) view.findViewById(R.id.comments);
			view.setTag(_holder);
		}else {
			_holder = (ViewHolder) view.getTag();
		}
		
		Info _info = (Info) getItem(position);
		_holder.title.setText(_info.title);
		_holder.date.setText(_info.date);
		_holder.name.setText(_info.name);
		_holder.comments.setText(_info.comments);
		
		return view;
	}
	
	private class ViewHolder {
		public TextView title;
		public TextView date;
		public TextView name;
		public TextView comments;
	}

}
