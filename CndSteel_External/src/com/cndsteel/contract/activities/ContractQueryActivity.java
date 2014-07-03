package com.cndsteel.contract.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.utils.DateUtils;
import com.cndsteel.framework.views.dialogs.wheelpicker.YearMonthPickerDialog;
import com.cndsteel.framework.views.spinner.AbsSpinner;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;

public class ContractQueryActivity extends FrameActivity implements View.OnClickListener,YearMonthPickerDialog.OnDateSelectedListener,AbsSpinner.OnItemClickListener  {
	
	private static final String QUERY_DATE_FORMAT = "yyyy/MM";
	
	private int mMinPickableYear = Calendar.getInstance().get(Calendar.YEAR) - 6;
	private int mMaxPickableYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
	
	private static ArrayList<String> sContractStatusNames;
	private static ArrayList<String> sContractStatusCodes;
	static {
		sContractStatusNames = new ArrayList<String>();
		sContractStatusNames.add("全部");
		sContractStatusNames.add("暂存");
		sContractStatusNames.add("未完结");
		sContractStatusNames.add("已完结");
		sContractStatusNames.add("作废");
		
		sContractStatusCodes = new ArrayList<String>();
		sContractStatusCodes.add("");
		sContractStatusCodes.add("TEMPORAL");
		sContractStatusCodes.add("OPENED");
		sContractStatusCodes.add("CLOSED");
		sContractStatusCodes.add("INVALID");
	}
	
	/** 选中查询条件 **/
	private String mQueryParamConYear;
	private String mQueryParamConMonth;
	private String mQueryParamConCode;
	private String mQueryParamStatus;
	
	//合同年月
	private RelativeLayout lyot_contractDate;
	private TextView txtV_contractDate;
	
	//合同号
	private EditText txtV_contractNumber;
	
	//合同状态
	private RelativeLayout lyot_contractStatus;
	private TextView txtV_contractStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_contract_query);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
	}

	private void initVariables() {
		mQueryParamConYear = DateUtils.getCurrentYear();
		mQueryParamConMonth = DateUtils.getCurrentMonth();
		mQueryParamConCode = "";
		mQueryParamStatus = "";
	}

	private void initViews() {
		setTopBarTitle(R.string.appModule_contract);
		
		lyot_contractDate = (RelativeLayout) findViewById(R.id.lyot_contractDate);
		lyot_contractDate.setOnClickListener(this);
		
		txtV_contractDate = (TextView) findViewById(R.id.txtV_contractDate);
		txtV_contractDate.setText(mQueryParamConYear + "/" + mQueryParamConMonth);
		
		txtV_contractNumber = (EditText) findViewById(R.id.txtV_contractNumber);
		
		lyot_contractStatus = (RelativeLayout) findViewById(R.id.lyot_contractStatus);
		lyot_contractStatus.setOnClickListener(this);
		
		txtV_contractStatus = (TextView) findViewById(R.id.txtV_contractStatus);
		
		((Button) findViewById(R.id.btn_contractQuery)).setOnClickListener(this);
		
	}

	/** 单击事件处理  **/
	@Override
	public void onClick(View view) {
		int _viewId = view.getId();
		switch (_viewId) {
		//合同年月
		case R.id.lyot_contractDate: {
			YearMonthPickerDialog.Builder _builder = new YearMonthPickerDialog.Builder(this);
			_builder.setTitle(R.string.contract_date)
					.setMinPickableYear(mMinPickableYear)
					.setMaxPickableYear(mMaxPickableYear)
					.setOnDateSelectedListener(this);
			
			YearMonthPickerDialog _datePickerDialog = _builder.create();					
			_datePickerDialog.show();
			
			break;
		}	
		//合同状态
		case R.id.lyot_contractStatus: {
			CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(getApplicationContext(), sContractStatusNames, view.getWidth());
			_cndSteelSpinner.setOnItemClickListener(this);
			_cndSteelSpinner.showAsDropDown(view, 0, -5);
			
			break;
		}	
		//查询按钮
		case R.id.btn_contractQuery: {
			mQueryParamConCode = txtV_contractNumber.getText().toString();
			
			Intent _intent = new Intent(this,ContractQueryResultListActivity.class);
			
			Bundle _queryParams = createQueryParams();
			_intent.putExtras(_queryParams);
			
			startActivity(_intent);
			
			break;
		}
		default:
			break;
		}
		
	}

	/**
	 * 时间选择回调
	 */
	@Override
	public void onDateSelected(Date selectedDate) {
		txtV_contractDate.setText(DateUtils.getFormatDateTime(selectedDate, QUERY_DATE_FORMAT));
		
		mQueryParamConYear = DateUtils.getFormatDateTime(selectedDate, "yyyy");
		mQueryParamConMonth = DateUtils.getFormatDateTime(selectedDate, "MM");
	}

	/**
	 * CndSteelSpinner 点击条目后回调
	 */
	@Override
	public void onItemClick(int position) {
		txtV_contractStatus.setText(sContractStatusNames.get(position));
		
		mQueryParamStatus = sContractStatusCodes.get(position);
	}
	
	private Bundle createQueryParams(){
		Bundle _queryParams = new Bundle();
		_queryParams.putString(QueryParams.QUERY_PARAM_CON_YEAR, mQueryParamConYear);
		_queryParams.putString(QueryParams.QUERY_PARAM_CON_MONTH, mQueryParamConMonth);
		_queryParams.putString(QueryParams.QUERY_PARAM_CON_CODE, mQueryParamConCode);
		_queryParams.putString(QueryParams.QUERY_PARAM_STATUS, mQueryParamStatus);
		
		return _queryParams;
	}
}
