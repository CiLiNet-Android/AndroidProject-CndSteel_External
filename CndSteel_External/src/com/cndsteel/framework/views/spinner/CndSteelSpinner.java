package com.cndsteel.framework.views.spinner;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

@SuppressLint("ViewConstructor")
public class CndSteelSpinner extends AbsSpinner<String> {

	public CndSteelSpinner(Context context, ArrayList<String> datas,int spinnerWidth) {
		super(context,datas);
		setWidth(spinnerWidth);
	}

	@Override
	protected AbsBaseAdapter<String> onCreateSpinnerAdapter(Context context,ArrayList<String> datas) {
		
		AbsBaseAdapter<String> _adapter = new AbsBaseAdapter<String>(context) {
			class ViewHolder {
				public TextView txtV_cndSpinnerItem; 
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder _viewHolder = null;
				if(null == convertView){
					convertView = getLayoutInflater().inflate(R.layout.spinner_cndsteel_item, parent,false);
					
					_viewHolder = new ViewHolder();
					_viewHolder.txtV_cndSpinnerItem = (TextView)convertView.findViewById(R.id.txtV_cndSpinnerItem);
					
					convertView.setTag(_viewHolder);
				}else {
					_viewHolder = (ViewHolder)convertView.getTag();
				}
				
				String _cndSpinnerItemContent = (String)getItem(position);
				_viewHolder.txtV_cndSpinnerItem.setText(_cndSpinnerItemContent);
				
				return convertView;
			}
		};
		
		_adapter.initDatas(datas);
		
		return _adapter;
	}

}
