package com.cndsteel.framework.views.dialogs.wheelpicker.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;

/**
 * 年选择器
 * @author wzgs_pub
 *
 */
public class DatePickerWheelVAdapter extends AbstractWheelTextAdapter {

	public ArrayList<String> mDatas;
	
	public DatePickerWheelVAdapter(Context context) {
		super(context, R.layout.dialog_date_picker_item, NO_RESOURCE);
	}

	@Override
	public View getItem(int index, View cachedView, ViewGroup parent) {
		View view = super.getItem(index, cachedView, parent);

		TextView txtV_dialogDatePickerItemText = (TextView) view.findViewById(R.id.txtV_dialogDatePickerItemText);
		txtV_dialogDatePickerItemText.setText(mDatas.get(index));
		
		return view;
	}

	public int getItemsCount() {
		return mDatas.size();
	}

	@Override
	public CharSequence getItemText(int index) {
		return mDatas.get(index);
	}
	
	public void initDatas(ArrayList<String> datas){
		mDatas = datas;
	}
	
	public void refreshDatas(ArrayList<String> datas){
		mDatas = datas;
		notifyDataInvalidatedEvent();
	}
	
	private ArrayList<String> getDatas(){
		if(null == mDatas){
			mDatas = new ArrayList<String>();
		}
		return mDatas;
	}
	
	public int getItemIndexByText(String text){
		int _itemIndex = -1;
		
		for(int i = 0; i < mDatas.size(); i ++){
			if(mDatas.get(i).equals(text)){
				_itemIndex = i;
				break;
			}
		}
		
		return _itemIndex;
	}

}
