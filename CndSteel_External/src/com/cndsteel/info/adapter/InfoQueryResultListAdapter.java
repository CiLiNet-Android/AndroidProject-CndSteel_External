package com.cndsteel.info.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.info.bean.InfoBean;

public class InfoQueryResultListAdapter extends AbsBaseAdapter<InfoBean> {

	public InfoQueryResultListAdapter(Context context) {
		super(context);
	}
 
	private class ViewHolder {
		public TextView txtV_infoQueryResultListItemTitle;
		public TextView txtV_infoQueryResultListItemDate;
		public TextView txtV_infoQueryResultListItemName;
		public TextView txtV_infoQueryResultListItemComments;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _viewHolder;
		if(null == convertView){
			convertView = getLayoutInflater().inflate(R.layout.info_list_item, parent,false);
			
			_viewHolder = new ViewHolder();
			_viewHolder.txtV_infoQueryResultListItemTitle = (TextView) convertView.findViewById(R.id.txtV_infoQueryResultListItemTitle);
			_viewHolder.txtV_infoQueryResultListItemDate = (TextView) convertView.findViewById(R.id.txtV_infoQueryResultListItemDate);
			_viewHolder.txtV_infoQueryResultListItemName = (TextView) convertView.findViewById(R.id.txtV_infoQueryResultListItemName);
			_viewHolder.txtV_infoQueryResultListItemComments = (TextView) convertView.findViewById(R.id.txtV_infoQueryResultListItemComments);
			
			convertView.setTag(_viewHolder);
		}else {
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		InfoBean _infoBean = (InfoBean) getItem(position);
		
		_viewHolder.txtV_infoQueryResultListItemTitle.setText(_infoBean.title);
		_viewHolder.txtV_infoQueryResultListItemDate.setText(_infoBean.date);
		_viewHolder.txtV_infoQueryResultListItemName.setText(_infoBean.author);
		_viewHolder.txtV_infoQueryResultListItemComments.setText(_infoBean.replyCount);
		
		return convertView;
	}

}
