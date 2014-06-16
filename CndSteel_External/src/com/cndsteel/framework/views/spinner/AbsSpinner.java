package com.cndsteel.framework.views.spinner;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.framework.log.GlobalLog;

/**
 * 自定义下拉菜单
 * @author zhxl
 */
public abstract class AbsSpinner<T> extends PopupWindow implements OnItemClickListener {
	
	private Context mContext;
	
	/** 要显示的数据列表 **/
	private ListView listV_spinner;
	
	/** 要显示的数据 **/
	private ArrayList<T> mDatas;

	
	public AbsSpinner(Context context,ArrayList<T> datas){
		super(context);
		mContext = context;
		mDatas = datas;
		
		initViews();
	}

	private void initViews() {
		View _spinner = LayoutInflater.from(mContext).inflate(R.layout.spinner, null);
		setContentView(_spinner);
		
		//随意初始化一个Spinner的尺寸,在实际应用中，需要再设置
		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		
		setFocusable(true);
		
		listV_spinner = (ListView) _spinner.findViewById(R.id.listV_spinner);
		listV_spinner.setAdapter(onCreateSpinnerAdapter(mContext,getDatas()));
		
		listV_spinner.setOnItemClickListener(this);
	}
	
	protected abstract AbsBaseAdapter<T> onCreateSpinnerAdapter(Context context,ArrayList<T> datas);
	
	public interface OnItemClickListener {
		public void onItemClick(int position);
	}
	
	private OnItemClickListener mOnItemClickListener;
	
	public void setOnItemClickListener(OnItemClickListener listener){
		mOnItemClickListener = listener;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if(null != mOnItemClickListener){
			GlobalLog.i("onItemClick()...");
			mOnItemClickListener.onItemClick(position);
		}
	}
	
	private ArrayList<T> getDatas(){
		if(null == mDatas){
			mDatas = new ArrayList<T>();
		}
		
		return mDatas;
	}
	
}
