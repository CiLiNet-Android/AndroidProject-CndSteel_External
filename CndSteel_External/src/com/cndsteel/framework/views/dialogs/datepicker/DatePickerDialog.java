package com.cndsteel.framework.views.dialogs.datepicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.views.dialogs.datepicker.adapters.DatePickerWheelVAdapter;
import com.cndsteel.framework.views.dialogs.datepicker.listeners.OnWheelScrollListener;

/**
 * 日期选择对话框
 * @author zhxl
 */
public class DatePickerDialog extends Dialog implements OnWheelScrollListener,View.OnClickListener {
	
	/** 显示最小年(默认) **/
	private static final int DEFAULT_MIN_PICKABLE_YEAR = 2010;
	/** 显示最大年(默认) **/
	private static final int DEFAULT_MAX_PICKABLE_YEAR = DEFAULT_MIN_PICKABLE_YEAR + 15;
	
	/** 年选择器 **/
	private WheelView wheelV_yearPicker;
	/** 月选择器 **/
	private WheelView wheelV_monthPicker;
	/** 日选择器 **/
	private WheelView wheelV_dayPicker;
	
	/** 对话框标题 **/
	private TextView txtV_datePickerDialogTitle;
	
	/** 完成按钮 **/
	private Button btn_datePickerDialogDone;
	
	/** 可被选择的日期 **/
//	private ArrayList<String> mPickableYears;
//	private ArrayList<String> mPickableMonths;
//	private ArrayList<String> mPickableDays;
	
	private int mMinYear;
	private int mMaxYear;
	private int mNowYear = Calendar.getInstance().get(Calendar.YEAR);
	
	/** 用户选择的日期 **/
	private int mSelectedYear;
	private int mSelectedMonth;
	//private int mSelectedDay;
	
	
	public static class Builder {
		private Context mContext;
		
		private String mTitle;
		
		private int mMinPickableYear;
		private int mMaxPickableYear;
		
		public Builder(Context context){
			mContext = context;
			
			mMinPickableYear = DEFAULT_MIN_PICKABLE_YEAR;
			mMaxPickableYear = DEFAULT_MAX_PICKABLE_YEAR;
		}
		
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
		
		public Builder setMinPickableYear(int minYear){
			mMinPickableYear = minYear;
			return this;
		}
		
		public Builder setMaxPickableYear(int maxYear){
			mMaxPickableYear = maxYear;
			return this;
		}
		
		private int mSelectedYear = Calendar.getInstance().get(Calendar.YEAR);
		public Builder setSelectedYear(int selectedYear){
			mSelectedYear = selectedYear;
			return this;
		}
		
		private int mSelectedMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		public Builder setSelectedMonth(int selectedMonth){
			mSelectedMonth = selectedMonth;
			return this;
		}
		
		//private int mSelectedDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//		public Builder setSelectedDay(int selectedDay){
//			mSelectedDay = selectedDay;
//			return this;
//		}
		
		private OnDateSelectedListener mOnDateSelectedListener;
		public Builder setOnDateSelectedListener(OnDateSelectedListener listener){
			mOnDateSelectedListener = listener;
			return this;
		}
		
		public DatePickerDialog create(){
			final DatePickerDialog _datePickerDialog = new DatePickerDialog(mContext);
			_datePickerDialog.initViews();
			
			_datePickerDialog.setTitle(mTitle);
			_datePickerDialog.setMinYear(mMinPickableYear);
			_datePickerDialog.setMaxYear(mMaxPickableYear);
			
			_datePickerDialog.setSelectedYear(mSelectedYear);
			_datePickerDialog.setSelectedMonth(mSelectedMonth);
			//_datePickerDialog.setSelectedDay(mSelectedDay);
			
			_datePickerDialog.initYearPickerView();
			_datePickerDialog.initMonthPickerView();
			//_datePickerDialog.initDayPickerView();
			
			if(null != mOnDateSelectedListener){
				_datePickerDialog.setOnDateSelectedListener(mOnDateSelectedListener);
			}
			
			return _datePickerDialog;
		}
	}
	
	//初始化年
	private void initYearPickerView(){
		WheelView _yearPickerView = getYearPickerWheelV();
		
		//适配器+数据
		DatePickerWheelVAdapter _yearPickerVadapter = new DatePickerWheelVAdapter(getContext());
		_yearPickerVadapter.initDatas(getPickableYears());
		_yearPickerView.setViewAdapter(_yearPickerVadapter);
		
		_yearPickerView.setCurrentItem(_yearPickerVadapter.getItemIndexByText(String.valueOf(mSelectedYear)));
		
		//监听器
		_yearPickerView.addScrollingListener(this);
	}
	
	//初始化月
	private void initMonthPickerView(){
		WheelView _monthPickerView = getMonthPickerWheelV();
		
		//适配器+数据
		DatePickerWheelVAdapter _monthPickerVadapter = new DatePickerWheelVAdapter(getContext());
		_monthPickerVadapter.initDatas(getPickableMonths());
		_monthPickerView.setViewAdapter(_monthPickerVadapter);
		
		String _selectedMonth = String.valueOf(mSelectedMonth);
		if(_selectedMonth.length() <= 1){
			_selectedMonth = "0" + _selectedMonth;
		}
		_monthPickerView.setCurrentItem(_monthPickerVadapter.getItemIndexByText(_selectedMonth));
		
		_monthPickerView.addScrollingListener(this);
	}
	
	//初始化日
//	private void initDayPickerView(){
//		WheelView _dayPickerView = getDayPickerWheelV();
//		
//		DatePickerWheelVAdapter _dayPickerAdapter = new DatePickerWheelVAdapter(getContext());
//		_dayPickerAdapter.initDatas(getPickableDays());
//		_dayPickerView.setViewAdapter(_dayPickerAdapter);
//		
//		_dayPickerView.setCurrentItem(_dayPickerAdapter.getItemIndexByText(String.valueOf(mSelectedDay)));
//		
//		_dayPickerView.addScrollingListener(this);
//	}

	protected DatePickerDialog(Context context) {
		super(context);
	}

	protected DatePickerDialog(Context context, int theme) {
		super(context, theme);
	}

	protected void initViews() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_date_picker);
		
		Window _window = getWindow();
		_window.setGravity(Gravity.BOTTOM);
		_window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		_window.setWindowAnimations(R.style.view_animation);
		
		btn_datePickerDialogDone = (Button)findViewById(R.id.btn_datePickerDialogDone);
		btn_datePickerDialogDone.setOnClickListener(this);
	}
	
	private TextView getDialogTitleTxtV(){
		if(null == txtV_datePickerDialogTitle){
			txtV_datePickerDialogTitle = (TextView)findViewById(R.id.txtV_datePickerDialogTitle);
		}
		return txtV_datePickerDialogTitle;
	}
	
	private WheelView getYearPickerWheelV(){
		if(null == wheelV_yearPicker){
			wheelV_yearPicker = (WheelView)findViewById(R.id.wheelV_yearPicker);
		}
		return wheelV_yearPicker;
	} 
	
	private WheelView getMonthPickerWheelV(){
		if(null == wheelV_monthPicker){
			wheelV_monthPicker = (WheelView)findViewById(R.id.wheelV_monthPicker);
		}
		return wheelV_monthPicker;
	}
	
//	private WheelView getDayPickerWheelV(){
//		if(null == wheelV_dayPicker){
//			wheelV_dayPicker = (WheelView)findViewById(R.id.wheelV_dayPicker);
//		}
//		return wheelV_dayPicker;
//	}
	
	private void setMinYear(int minYear){
		mMinYear = minYear;
	}
	
	private void setMaxYear(int maxYear){
		mMaxYear = maxYear;
	}
	
	protected void setTitle(String title){
		getDialogTitleTxtV().setText(title);
	}

	@Override
	public void onScrollingStarted(WheelView wheelView) {}

	@Override
	public void onScrollingFinished(WheelView wheelView) {
		//获得当前高亮的条目
		int _selectedItemIndex = wheelView.getCurrentItem();
		String _selectedItem = (String)((DatePickerWheelVAdapter)wheelView.getViewAdapter()).getItemText(_selectedItemIndex);
		
		int _wheelViewId = wheelView.getId();
		switch(_wheelViewId){
		//年选择
		case R.id.wheelV_yearPicker: {
			setSelectedYear(Integer.valueOf(_selectedItem));
			
			//重新计算天数
//			ArrayList<String> _pickableDays = getPickableDays();
//			DatePickerWheelVAdapter _adapter = (DatePickerWheelVAdapter)wheelV_dayPicker.getViewAdapter();
//			if(_adapter.getItemsCount() != _pickableDays.size()){//
//				_adapter.refreshDatas(_pickableDays);
//			}
//			
//			if(mSelectedDay > _pickableDays.size()){
//				wheelV_dayPicker.setCurrentItem(0);
//				mSelectedDay = 1;
//			}
			break;
		}
		//月选择
		case R.id.wheelV_monthPicker: { 
			if(_selectedItem.startsWith("0")){
				_selectedItem = _selectedItem.substring(1);
			}
			
			setSelectedMonth(Integer.valueOf(_selectedItem));
			
			//重新计算天数
//			ArrayList<String> _pickableDays = getPickableDays();
//			DatePickerWheelVAdapter _adapter = (DatePickerWheelVAdapter)wheelV_dayPicker.getViewAdapter();
//			if(_adapter.getItemsCount() != _pickableDays.size()){//
//				_adapter.refreshDatas(_pickableDays);
//			}
//			
//			if(mSelectedDay > _pickableDays.size()){
//				wheelV_dayPicker.setCurrentItem(0);
//				mSelectedDay = 1;
//			}
			break;
		}
		//日选择
//		case R.id.wheelV_dayPicker:{
//			if(_selectedItem.startsWith("0")){
//				_selectedItem = _selectedItem.substring(1);
//			}
//			
//			setSelectedDay(Integer.valueOf(_selectedItem));
//			
//			break;
//		}
		default:
			break;
		}
	}
	
	private ArrayList<String> getPickableYears(){
		ArrayList<String> _pickableYears = new ArrayList<String>();
		
		//始终包含当前年
		if(mNowYear > mMaxYear){
			mMaxYear = mNowYear;
		}
		
		for(int i = mMinYear; i <= mMaxYear; i ++){
			_pickableYears.add(String.valueOf(i));
		}
		return _pickableYears;
	}
	
	private ArrayList<String> getPickableMonths(){
		ArrayList<String> _pickableMonths = new ArrayList<String>();
		
		String[] _pickableMonthsArray = getContext().getResources().getStringArray(R.array.months);
		for(String _pickableMonth : _pickableMonthsArray){
			_pickableMonths.add(_pickableMonth);
		}
		
		return _pickableMonths;
	}
	
//	private ArrayList<String> getPickableDays(){
//		String[] _pickableDaysArray = null;
//		//如果是31天月份
//		if(mSelectedMonth == 1 || mSelectedMonth == 3 || mSelectedMonth == 5 || mSelectedMonth==7 || mSelectedMonth == 8 || mSelectedMonth == 10 || mSelectedMonth == 12){
//			_pickableDaysArray = getContext().getResources().getStringArray(R.array.days_31);
//		}else if(mSelectedMonth == 2){
//			//如果是闰年
//			if(selectedLeapYear()){
//				_pickableDaysArray = getContext().getResources().getStringArray(R.array.days_29);
//			}else {
//				_pickableDaysArray = getContext().getResources().getStringArray(R.array.days_28);
//			}
//		}else {
//			_pickableDaysArray = getContext().getResources().getStringArray(R.array.days_30);
//		}
//			
//		ArrayList<String> _pickableDays = new ArrayList<String>(_pickableDaysArray.length);
//		for(String _pickableDay : _pickableDaysArray){
//			_pickableDays.add(_pickableDay);
//		}
//			
//		return _pickableDays;
//	}
	
	private void setSelectedYear(int selectedYear) {
		mSelectedYear = selectedYear;
	}

	private void setSelectedMonth(int selectedMonth) {
		mSelectedMonth = selectedMonth;
	}

//	private void setSelectedDay(int selectedDay) {
//		mSelectedDay = selectedDay;
//	}

	/**
	 * 当前选中的年是否是闰年
	 */
//	private boolean selectedLeapYear(){
//		return (mSelectedYear % 4 == 0 && mSelectedYear % 100 != 0) || mSelectedYear % 400 == 0;
//	}

	@Override
	public void onClick(View view) {
		if(null != mOnDateSelectedListener){
			Calendar _selectedCalendar = Calendar.getInstance();
			_selectedCalendar.set(mSelectedYear, mSelectedMonth - 1, 1);
			
			mOnDateSelectedListener.onDateSelected(_selectedCalendar.getTime());
		}
		
		dismiss();
	}
	
	public interface OnDateSelectedListener {
		public void onDateSelected(Date selectedDate);
	}
	
	private OnDateSelectedListener mOnDateSelectedListener;
	
	private void setOnDateSelectedListener(OnDateSelectedListener listener){
		mOnDateSelectedListener = listener;
	}

	public void dismiss(){
		if(isShowing()){
			super.dismiss();
		}
	}
}
