package com.cndsteel.plan.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.utils.DateUtil;
import com.cndsteel.framework.views.dialogs.datepicker.DatePickerDialog;
import com.cndsteel.framework.views.spinner.AbsSpinner;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;

/**
 * 订货计划查询
 * 
 * @author zhxl
 * 
 */
public class PlanQueryActivity extends FrameActivity implements View.OnClickListener,DatePickerDialog.OnDateSelectedListener,AbsSpinner.OnItemClickListener {

	
	
	private static final String QUERY_DATE_FORMAT = "yyyy/MM";
	
	private int mMinPickableYear = Calendar.getInstance().get(Calendar.YEAR) - 6;
	private int mMaxPickableYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
	
	private static ArrayList<String> sPlanStatusNames;
	private static ArrayList<String> sPlanStatusCodes;
	static {
		sPlanStatusNames = new ArrayList<String>();
		sPlanStatusNames.add("全部");
		sPlanStatusNames.add("暂存");
		sPlanStatusNames.add("等待");
		sPlanStatusNames.add("已确认");
		sPlanStatusNames.add("未完结");
		sPlanStatusNames.add("已完结");
		sPlanStatusNames.add("作废");
		
		sPlanStatusCodes = new ArrayList<String>();
		sPlanStatusCodes.add("");
		sPlanStatusCodes.add("TEMP");
		sPlanStatusCodes.add("WAIT");
		sPlanStatusCodes.add("CONFIRM");
		sPlanStatusCodes.add("UNDONE");
		sPlanStatusCodes.add("DONE");
		sPlanStatusCodes.add("INVALID");
	}
	
	/** 计划年月 **/
	private RelativeLayout lyot_planDate;
	private TextView txtV_planDate;
	
	/** 计划状态 **/
	private RelativeLayout lyot_planStatue;
	private TextView txtV_planStatus;
	
	/** 提交按钮 **/
	private Button btn_planQuery;
	
	/** 新建计划按钮 **/
	private ImageButton imgBtn_topRight;
	
	/** 选中查询条件 **/
	private String mQueryParamYear;
	private String mQueryParamMonth;
	private String mQueryParamStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appendFrameworkCenter(R.layout.activity_plan_query);

		initViews();
		
		initVariables();
	}

	private void initVariables() {
		mQueryParamYear = DateUtil.getCurrentYear();
		mQueryParamMonth = DateUtil.getCurrentMonth();
		
		mQueryParamStatus = "";
	}

	private void initViews() {
		setTopBarTitle(R.string.topBarTitle_planQuery);
		
//		imgBtn_topRight = (ImageButton) findViewById(R.id.imgBtn_topRight);
//		imgBtn_topRight.setVisibility(View.VISIBLE);
//		imgBtn_topRight.setBackgroundResource(R.drawable.add);

		lyot_planDate = (RelativeLayout) findViewById(R.id.lyot_planDate);
		lyot_planDate.setOnClickListener(this);
		
		txtV_planDate = (TextView)findViewById(R.id.txtV_planDate);
		txtV_planDate.setText(DateUtil.getFormatDate(QUERY_DATE_FORMAT));

		lyot_planStatue = (RelativeLayout) findViewById(R.id.lyot_planStatus);
		lyot_planStatue.setOnClickListener(this);
		
		txtV_planStatus = (TextView)findViewById(R.id.txtV_planStatus);
		txtV_planStatus.setText(sPlanStatusNames.get(0));
		
		btn_planQuery = (Button) findViewById(R.id.btn_planQuery);
		btn_planQuery.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		int _viewId = view.getId();
		switch (_viewId) {
		//计划年月
		case R.id.lyot_planDate: {
			DatePickerDialog.Builder _builder = new DatePickerDialog.Builder(this);
			_builder.setTitle(R.string.plan_date)
					.setMinPickableYear(mMinPickableYear)
					.setMaxPickableYear(mMaxPickableYear)
					.setOnDateSelectedListener(this);
			
			DatePickerDialog _datePickerDialog = _builder.create();					
			_datePickerDialog.show();
			
	        break;
		}
		//计划状态
		case R.id.lyot_planStatus:
			CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(getApplicationContext(), sPlanStatusNames, view.getWidth());
			_cndSteelSpinner.setOnItemClickListener(this);
			_cndSteelSpinner.showAsDropDown(view, 0, -5);
			break;
		//查询按钮
		case R.id.btn_planQuery:
			Intent _intent = new Intent(this,PlanQueryResultListActivity.class);
			
			//请求参数
			Bundle _queryParams = new Bundle();
			_queryParams.putString(QueryParams.QUERY_PARAM_PLAN_DATE_YEAR, mQueryParamYear);
			_queryParams.putString(QueryParams.QUERY_PARAM_PLAN_DATE_MONTH, mQueryParamMonth);
			_queryParams.putString(QueryParams.QUERY_PARAM_PLAN_STATUS, mQueryParamStatus);
			_intent.putExtras(_queryParams);
			
			startActivity(_intent);
		default:
			break;
		}

	}

	/**
	 * 时间选择回调
	 */
	@Override
	public void onDateSelected(Date selectedDate) {
		txtV_planDate.setText(DateUtil.getFormatDateTime(selectedDate, QUERY_DATE_FORMAT));
		
		mQueryParamYear = DateUtil.getFormatDateTime(selectedDate, "yyyy");
		mQueryParamMonth = DateUtil.getFormatDateTime(selectedDate, "MM");
	}

	/**
	 * CndSteelSpinner 点击条目后回调
	 */
	@Override
	public void onItemClick(int position) {
		txtV_planStatus.setText(sPlanStatusNames.get(position));
		
		mQueryParamStatus = sPlanStatusCodes.get(position);
	}


}
