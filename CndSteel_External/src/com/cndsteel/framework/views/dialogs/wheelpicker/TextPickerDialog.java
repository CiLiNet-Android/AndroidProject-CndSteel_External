package com.cndsteel.framework.views.dialogs.wheelpicker;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.views.dialogs.wheelpicker.adapters.DatePickerWheelVAdapter;
import com.cndsteel.framework.views.dialogs.wheelpicker.adapters.TextPickerWheelVAdapter;
import com.cndsteel.framework.views.dialogs.wheelpicker.listeners.OnWheelScrollListener;

/**
 * 文字选择对话框
 * @author zhxl
 *
 */
public class TextPickerDialog extends Dialog implements OnWheelScrollListener,View.OnClickListener{
	
	public static class Builder {
		private Context mContext;
		
		public Builder(Context context){
			mContext = context;
		}
		
		private String mTitle;
		public Builder setTitle(String title) {
			mTitle = title;
			return this;
		}
		
		public Builder setTitle(int titleResId) {
			mTitle = mContext.getResources().getString(titleResId);
			return this;
		}
		
		public Builder setTitle(int titleResId,Object... formatArgs){
			mTitle = mContext.getResources().getString(titleResId, formatArgs);
			return this;
		}
		
		private ArrayList<String> mPickableTexts;
		public Builder setPickableTexts(ArrayList<String> pickableText){
			mPickableTexts = pickableText;
			return this;
		}
		
		private OnTextPickedListener mOnTextPickedListener;
		public Builder setOnTextPickedListener(OnTextPickedListener listener){
			mOnTextPickedListener = listener;
			return this;
		}
		
		private int mPickedPosition = 0;
		private void setPickedPosition(int pickedPosition){
			mPickedPosition = pickedPosition;
		}
		
		public TextPickerDialog create(){
			TextPickerDialog _textPickerDialog = new TextPickerDialog(mContext);
			_textPickerDialog.initViews();
			
			_textPickerDialog.setTitle(mTitle);
			_textPickerDialog.setPickableTexts(mPickableTexts);
			_textPickerDialog.setPickedPosition(mPickedPosition);
			
			_textPickerDialog.setOnTextPickedListener(mOnTextPickedListener);
			
			return _textPickerDialog;
		}
	}
	
	
	/** 完成按钮 **/
	private Button btn_textPickerDialogDone;
	private TextView txtV_textPickerDialogTitle;
	
	private WheelView wheelV_textPicker;
	private TextPickerWheelVAdapter mTextPickerWheelVAdapter;
	private ArrayList<String> mPickableTexts;

	protected TextPickerDialog(Context context) {
		super(context);
	}

	private void initViews(){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_text_picker);
		
		Window _window = getWindow();
		_window.setGravity(Gravity.BOTTOM);
		_window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		_window.setWindowAnimations(R.style.view_animation);
		
		btn_textPickerDialogDone = (Button)findViewById(R.id.btn_textPickerDialogDone);
		btn_textPickerDialogDone.setOnClickListener(this);
		
		wheelV_textPicker = (WheelView)findViewById(R.id.wheelV_textPicker);
		
		mTextPickerWheelVAdapter = new TextPickerWheelVAdapter(getContext());
		mTextPickerWheelVAdapter.initDatas(new ArrayList<String>());
		
		wheelV_textPicker.setViewAdapter(mTextPickerWheelVAdapter);
		wheelV_textPicker.addScrollingListener(this);
	}
	
	private TextView getDialogTitleTxtV(){
		if(null == txtV_textPickerDialogTitle){
			txtV_textPickerDialogTitle = (TextView)findViewById(R.id.txtV_textPickerDialogTitle);
		}
		return txtV_textPickerDialogTitle;
	}
	
	private void setTitle(String title){
		getDialogTitleTxtV().setText(title);
	}
	
	private void setPickedPosition(int pickedPosition){
		mPickedPosition = pickedPosition;
	}
	
	private void setPickableTexts(ArrayList<String> pickableTexts){
		mPickableTexts = pickableTexts;
		
		if(null != mPickableTexts && mPickableTexts.size() > 0){
			mTextPickerWheelVAdapter.refreshDatas(mPickableTexts);
		}
	}
	
	private OnTextPickedListener mOnTextPickedListener;
	private void setOnTextPickedListener(OnTextPickedListener listener){
		mOnTextPickedListener = listener;
	}
	
	public interface OnTextPickedListener {
		public void onTextPicked(TextPickerDialog textPickerDialog,int position);
	}

	private int mPickedPosition;
	
	@Override
	public void onClick(View view) {
		if(null != mOnTextPickedListener){
			mOnTextPickedListener.onTextPicked(this, mPickedPosition);
		}
		
		dismiss();
	}

	@Override
	public void onScrollingFinished(WheelView wheelView) {
		mPickedPosition = wheelView.getCurrentItem();
	}
	
	@Override
	public void onScrollingStarted(WheelView wheelView) {}

	
	public void dismiss(){
		if(isShowing()){
			super.dismiss();
		}
	}
	
}
