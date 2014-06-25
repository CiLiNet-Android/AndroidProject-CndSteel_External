package com.cndsteel.main.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.main.beans.APPModuleBean;

public class APPModulesGridVAdapter extends AbsBaseAdapter<APPModuleBean> {

	public APPModulesGridVAdapter(Context context) {
		super(context);
	}
	
	private class ViewHolder {
		public ImageView imgV_appModuleIcon;
		public TextView txtV_appModuleName;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _viewHolder = null;
		if(null == convertView){
			convertView = getLayoutInflater().inflate(R.layout.layout_main_app_grid_item, parent, false);
			
			_viewHolder = new ViewHolder();
			_viewHolder.imgV_appModuleIcon = (ImageView)convertView.findViewById(R.id.imgV_appModuleIcon);
			_viewHolder.txtV_appModuleName = (TextView)convertView.findViewById(R.id.txtV_appModuleName);
			
			convertView.setTag(_viewHolder);
		}else {
			_viewHolder = (ViewHolder)convertView.getTag();
		}
		
		APPModuleBean _appModuleBean = (APPModuleBean)getItem(position);
		
		_viewHolder.imgV_appModuleIcon.setImageResource(_appModuleBean.iconResId);
		_viewHolder.txtV_appModuleName.setText(_appModuleBean.nameResId);
		
		return convertView;
	}

}
