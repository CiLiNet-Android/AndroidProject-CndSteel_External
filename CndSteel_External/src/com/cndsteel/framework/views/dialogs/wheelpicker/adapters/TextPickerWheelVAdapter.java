package com.cndsteel.framework.views.dialogs.wheelpicker.adapters;

import java.util.ArrayList;

import com.cndsteel.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextPickerWheelVAdapter extends AbstractWheelTextAdapter {

	private ArrayList<String> mDatas;
	
	public TextPickerWheelVAdapter(Context context) {
		super(context, R.layout.dialog_text_picker_item);
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

	@Override
	public int getItemsCount() {
		return mDatas.size();
	}

	@Override
	public CharSequence getItemText(int index) {
		return mDatas.get(index);
	}

	@Override
	public View getItem(int index, View cachedView, ViewGroup parent) {
		View view = super.getItem(index, cachedView, parent);

		TextView txtV_dialogDatePickerItemText = (TextView) view.findViewById(R.id.txtV_dialogTextPickerItemText);
		txtV_dialogDatePickerItemText.setText(mDatas.get(index));
		
		return view;
	}

}
