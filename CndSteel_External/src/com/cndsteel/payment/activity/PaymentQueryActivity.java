package com.cndsteel.payment.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.bean.LoadPayModeBean;
import com.cndsteel.framework.bean.PayModeBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.utils.DateUtils;
import com.cndsteel.framework.views.dialogs.wheelpicker.YearMonthDayPickerDialog;
import com.cndsteel.framework.views.dialogs.wheelpicker.YearMonthPickerDialog;
import com.cndsteel.framework.views.spinner.AbsSpinner;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.payment.adapter.PaymentQueryViewPagerAdapter;
import com.cndsteel.settings.beans.SettingsBean;

public class PaymentQueryActivity extends FrameActivity implements OnClickListener, OnPageChangeListener {
	
	/** 按付款信息 **/
	private static final int PAGE_AS_INFORMATION = 0;
	/** 按合同款项 **/
	private static final int PAGE_AS_CONTRACT = 1;
	
	private int mMinPickableYear = Calendar.getInstance().get(Calendar.YEAR) - 6;
	private int mMaxPickableYear = Calendar.getInstance().get(Calendar.YEAR) + 1;

	private static final int INFORMATION_PAGE_HANDLER_MSG_LOAD_PAY_MODE = 0x100;
	
	private String mQueryParamSessionId;
	
	/**
	 * 付款信息
	 */
	private RelativeLayout lyot_paymentDateStart;
	private TextView txtV_paymentDateStart;
	private RelativeLayout lyot_paymentDateEnd;
	private TextView txtV_paymentDateEnd;
	private RelativeLayout lyot_paymentMethod;
	private TextView txtV_paymentMethod;
	private EditText edTxt_paymentBillNum;
	/**
	 * 付款信息请求参数
	 */
	private String mQueryParamPayDateFrom;
	private String mQueryParamPayDateTo;
	private String mQueryParamPayMode;
	private String mQueryParamBillNO;
	
	
	/**
	 * 合同付款
	 */
	private RelativeLayout lyot_paymentPlanDate;
	private TextView txtV_paymentPlanDate;
	private EditText edTxt_paymentContractNum;
	/**
	 * 合同款项请求参数
	 */
	private String mQueryParamConYear;
	private String mQueryParamConMonth;
	private String mQueryParamConCode;
	
	private ViewPager vPager_paymentQuery;
	
	/**
	 * 顶部按钮
	 */
	private Button btn_paymentInfo;
	private ImageView imgV_paymentInfo;
	private Button btn_paymentContractFunds;
	private ImageView imgV_paymentContractFunds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendFrameworkCenter(R.layout.activity_payment_main);
		setTopBarTitle(R.string.appModule_runningAccount);

		init();
	}

	private void init() {
		initVariables();
		initViews();
	}
	
	private void initVariables(){
		Calendar _nowCalendar = Calendar.getInstance();
		
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
		
		//付款信息
		String _nowDate = DateUtils.getFormatDateTime(_nowCalendar.getTime(), "yyyy-MM-dd");
		mQueryParamPayDateFrom = _nowDate;
		mQueryParamPayDateTo = _nowDate;
		mQueryParamPayMode = "";
		mQueryParamBillNO = "";
		
		//合同款项
		mQueryParamConYear = String.valueOf(_nowCalendar.get(Calendar.YEAR));
		mQueryParamConMonth = String.valueOf(_nowCalendar.get(Calendar.MONTH + 1));
		mQueryParamConCode = "";
	}

	private void initViews() {
		btn_paymentInfo = (Button) findViewById(R.id.btn_paymentInfo);
		btn_paymentInfo.setOnClickListener(this);
		imgV_paymentInfo = (ImageView) findViewById(R.id.imgV_paymentInfo);
		
		btn_paymentContractFunds = (Button) findViewById(R.id.btn_paymentContractFunds);
		btn_paymentContractFunds.setOnClickListener(this);
		imgV_paymentContractFunds = (ImageView) findViewById(R.id.imgV_paymentContractFunds);
		
		ArrayList<View> _viewPages = new ArrayList<View>(2);
		_viewPages.add(initPaymentInfoViewPage());
		_viewPages.add(initContractFundViewPage());

		PaymentQueryViewPagerAdapter _paymentQueryViewPagerAdapter = new PaymentQueryViewPagerAdapter(getApplicationContext());
		_paymentQueryViewPagerAdapter.initDatas(_viewPages);
		
		vPager_paymentQuery = (ViewPager)findViewById(R.id.vPager_paymentQuery);
		vPager_paymentQuery.setAdapter(_paymentQueryViewPagerAdapter);
		vPager_paymentQuery.setCurrentItem(PAGE_AS_INFORMATION);
		vPager_paymentQuery.setOnPageChangeListener(this);
	}
	
	/**
	 * 初始化付款信息View
	 */
	private View initPaymentInfoViewPage(){
		View _viewPage = inflateView(R.layout.layout_payment_viewpager_information);
		
		//支付日期从
		txtV_paymentDateStart = (TextView)_viewPage.findViewById(R.id.txtV_paymentDateStart);
		txtV_paymentDateStart.setText(DateUtils.getFormatDateTime(new Date(), "yyyy/MM/dd"));
				
		lyot_paymentDateStart = (RelativeLayout)_viewPage.findViewById(R.id.lyot_paymentDateStart);
		lyot_paymentDateStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(PaymentQueryActivity.this);
				_builder.setTitle(R.string.paymentDateStart)
				.setMinPickableYear(mMinPickableYear)
				.setMaxPickableYear(mMaxPickableYear)
				.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
					@Override
					public void onDateSelected(Date selectedDate) {
						txtV_paymentDateStart.setText(DateUtils.getFormatDateTime(selectedDate, "yyyy/MM/dd"));
						mQueryParamPayDateFrom = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
					}
				}).create().show();
			}
		});
		
		//支付日期至
		txtV_paymentDateEnd = (TextView)_viewPage.findViewById(R.id.txtV_paymentDateEnd);
		txtV_paymentDateEnd.setText(DateUtils.getFormatDateTime(new Date(), "yyyy/MM/dd"));
		
		lyot_paymentDateEnd = (RelativeLayout)_viewPage.findViewById(R.id.lyot_paymentDateEnd);
		lyot_paymentDateEnd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(PaymentQueryActivity.this);
				_builder.setTitle(R.string.paymentDateEnd)
				.setMinPickableYear(mMinPickableYear)
				.setMaxPickableYear(mMaxPickableYear)
				.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
					@Override
					public void onDateSelected(Date selectedDate) {
						txtV_paymentDateEnd.setText(DateUtils.getFormatDateTime(selectedDate, "yyyy/MM/dd"));
						mQueryParamPayDateTo = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
					}
				}).create().show();
			}
		});
		
		//支付方式
		txtV_paymentMethod = (TextView)_viewPage.findViewById(R.id.txtV_paymentMethod);
		txtV_paymentMethod.setText("全部");
		
		lyot_paymentMethod = (RelativeLayout)_viewPage.findViewById(R.id.lyot_paymentMethod);
		lyot_paymentMethod.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				loadDatas(INFORMATION_PAGE_HANDLER_MSG_LOAD_PAY_MODE);
			}
		});
		
		//票据号
		edTxt_paymentBillNum = (EditText)_viewPage.findViewById(R.id.edTxt_paymentBillNum);
		
		//查询按钮
		((Button)_viewPage.findViewById(R.id.btn_paymentInfoQuery)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mQueryParamBillNO = edTxt_paymentBillNum.getText().toString();
				
				Intent _intent = new Intent(PaymentQueryActivity.this,PaymentAsInformationQueryResultListActivity.class);
				_intent.putExtra(QueryParams.QUERY_PARAM_PAYMENT_PAY_DATE_FROM, mQueryParamPayDateFrom);
				_intent.putExtra(QueryParams.QUERY_PARAM_PAYMENT_PAY_DATE_TO, mQueryParamPayDateTo);
				_intent.putExtra(QueryParams.QUERY_PARAM_PAYMENT_PAY_MODE, mQueryParamPayMode);
				_intent.putExtra(QueryParams.QUERY_PARAM_PAYMENT_BILL_NO, mQueryParamBillNO);
				
				startActivity(_intent);
			}
		});
		
		return _viewPage;
	}
	
	/**
	 * 初始化合同款项View
	 */
	private View initContractFundViewPage(){
		View _viewPage = inflateView(R.layout.layout_payment_viewpager_contract);
		
		//计划年月
		txtV_paymentPlanDate = (TextView)_viewPage.findViewById(R.id.txtV_paymentPlanDate);
		txtV_paymentPlanDate.setText(DateUtils.getFormatDateTime(new Date(), "yyyy/MM"));
		
		lyot_paymentPlanDate = (RelativeLayout)_viewPage.findViewById(R.id.lyot_paymentPlanDate);
		lyot_paymentPlanDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				YearMonthPickerDialog.Builder _builder = new YearMonthPickerDialog.Builder(PaymentQueryActivity.this);
				_builder.setTitle(R.string.plan_date)
				.setMinPickableYear(mMinPickableYear)
				.setMaxPickableYear(mMaxPickableYear)
				.setOnDateSelectedListener(new YearMonthPickerDialog.OnDateSelectedListener() {
					@Override
					public void onDateSelected(Date selectedDate) {
						txtV_paymentPlanDate.setText(DateUtils.getFormatDateTime(selectedDate, "yyyy/MM"));
						mQueryParamConYear = DateUtils.getFormatDateTime(selectedDate, "yyyy");
						mQueryParamConMonth = DateUtils.getFormatDateTime(selectedDate, "dd");
					}
				}).create().show();
			}
		});
		
		//合同号
		edTxt_paymentContractNum = (EditText)_viewPage.findViewById(R.id.edTxt_paymentContractNum);
		
		//查询按钮
		((Button)_viewPage.findViewById(R.id.btn_paymentContractNumQuery)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mQueryParamConCode = edTxt_paymentContractNum.getText().toString();
				
				Intent _intent = new Intent(PaymentQueryActivity.this,PaymentAsContractQueryResultListActivity.class);
				_intent.putExtra(QueryParams.QUERY_PARAM_CON_YEAR, mQueryParamConYear);
				_intent.putExtra(QueryParams.QUERY_PARAM_CON_MONTH, mQueryParamConMonth);
				_intent.putExtra(QueryParams.QUERY_PARAM_CON_CODE, mQueryParamConCode);
				
				startActivity(_intent);
			}
		});
		
		return _viewPage;
	}
	
	private void loadDatas(int msgWhat){
		showLoadingProgressDialog();
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new LoadPayModeBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<PaymentQueryActivity> mWeakReference;
		
		public TheHandler(PaymentQueryActivity activity){
			mWeakReference = new WeakReference<PaymentQueryActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final PaymentQueryActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case INFORMATION_PAGE_HANDLER_MSG_LOAD_PAY_MODE:{
					_theActivity.dismissProgressDialog();
					
					final ArrayList<PayModeBean> _payModeBeans = new ArrayList<PayModeBean>();
					_payModeBeans.add(new PayModeBean("","全部"));
					
					LoadPayModeBean _loadPayModeBean = (LoadPayModeBean)msg.obj;
					if(_loadPayModeBean.code > 0){
						_payModeBeans.addAll(_payModeBeans.size(), _loadPayModeBean.payModeBeans);
					}
					
					final ArrayList<String> _payModeNames = new ArrayList<String>();
					for(PayModeBean _payModeBean : _payModeBeans){
						_payModeNames.add(_payModeBean.text);
					}
					
					CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(_theActivity.getApplicationContext(), _payModeNames, _theActivity.lyot_paymentMethod.getWidth());
					_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
						@Override
						public void onItemClick(int position){
							_theActivity.txtV_paymentMethod.setText(_payModeNames.get(position));
							_theActivity.mQueryParamPayMode = _payModeBeans.get(position).value;
						}
	
					});
					_cndSteelSpinner.showAsDropDown(_theActivity.lyot_paymentMethod, 0, -5);
					
					break;
				}
				default: 
					break;
				}
			}
		}
	}
	

	@Override
	public void onClick(View view) {
		int _viewId = view.getId();
		switch (_viewId) {
		//按付款信息
		case R.id.btn_paymentInfo: 
			vPager_paymentQuery.setCurrentItem(PAGE_AS_INFORMATION, true);
			break;
		//按合同款项
		case R.id.btn_paymentContractFunds:
			vPager_paymentQuery.setCurrentItem(PAGE_AS_CONTRACT, true);
			break;
			
		default:
			break;
		}

	}

	@Override
	public void onPageScrollStateChanged(int state) {}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

	@Override
	public void onPageSelected(int position) {
		switch (position) {
		case PAGE_AS_INFORMATION:
			imgV_paymentInfo.setVisibility(View.VISIBLE);
			imgV_paymentContractFunds.setVisibility(View.INVISIBLE);
			
			break;
		case PAGE_AS_CONTRACT:
			imgV_paymentInfo.setVisibility(View.INVISIBLE);
			imgV_paymentContractFunds.setVisibility(View.VISIBLE);
			
			break;
		default:
			break;
		}
		
	}
}
