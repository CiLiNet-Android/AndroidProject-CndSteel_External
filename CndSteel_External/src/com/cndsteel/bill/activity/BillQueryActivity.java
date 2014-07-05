package com.cndsteel.bill.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.adapter.BillQueryViewPagerAdapter;
import com.cndsteel.bill.enums.CustomerStatus;
import com.cndsteel.bill.enums.WzStatus;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.utils.DateUtils;
import com.cndsteel.framework.views.dialogs.wheelpicker.YearMonthPickerDialog;
import com.cndsteel.framework.views.spinner.AbsSpinner;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;

public class BillQueryActivity extends FrameActivity implements OnClickListener, OnPageChangeListener {
	
	private static final int BILL_PAGE_CONTRACT_INVOICE = 0;
	private static final int BILL_PAGE_CONTRACT_TRACKING = 1;
	
	private static final String QUERY_PARAM_DATE_FORMAT = "yyyy-MM";
	
	private int mMinPickableYear = Calendar.getInstance().get(Calendar.YEAR) - 6;  //当前的年份-6
	private int mMaxPickableYear = Calendar.getInstance().get(Calendar.YEAR) + 1;  //当前的年份+1
	
	private ViewPager vPager_billQuery;
	
	/**
	 * 合同开票页面
	 */
	private TextView txtV_contractDateStart;
	private TextView txtV_contractDateEnd;
	private EditText edTxt_contractNum;
	private RelativeLayout lyot_contractDateStart;
	private RelativeLayout lyot_contractDateEnd;
	/**
	 * 合同开票查询请求参数
	 */
	private String mContractInvoiceQueryParamConYearMontFrom;
	private String mContractInvoiceQueryParamConYearMontTo;
	private String mContractInvoiceQueryParamConCode;
	
	
	/**
	 * 合同正本跟踪页面
	 */
	private EditText edTxt_contractTrackingNum;
	private TextView txtV_corporationSendAndReceiveState;
	private RelativeLayout lyot_corporationSendAndReceiveState;
	private TextView txtV_logisticsSendAndReceiveState;
	private RelativeLayout lyot_logisticsSendAndReceiveState;
	/**
	 * 合同正本跟踪查询请求参数
	 */
	private String mContractTrackingQueryParamConCode;
	private String mContractTrackingQueryParamCustomerStatus;
	private String mContractTrackingQueryParamWzStatus;
	
	/**
	 * tab
	 */
	private ImageView imgV_theContractOfMakeOutAnInvoice;
	private ImageView imgV_ContractTracking;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.appModule_bill);
		appendFrameworkCenter(R.layout.activity_bill_main);
		
		init();
	}
	
	private void init() {
		initVariables();
		initViews();
	}
	
	private void initVariables() {
		mContractInvoiceQueryParamConYearMontFrom = DateUtils.getFormatDateTime(new Date(), QUERY_PARAM_DATE_FORMAT);
		mContractInvoiceQueryParamConYearMontTo = DateUtils.getFormatDateTime(new Date(), QUERY_PARAM_DATE_FORMAT);
		mContractInvoiceQueryParamConCode = "";
		
		mContractTrackingQueryParamConCode = "";
		mContractTrackingQueryParamCustomerStatus = "";
		mContractTrackingQueryParamWzStatus = "";
	}

	private void initViews() {
		findViewById(R.id.btn_theContractOfMakeOutAnInvoice).setOnClickListener(this);
		imgV_theContractOfMakeOutAnInvoice = (ImageView)findViewById(R.id.imgV_theContractOfMakeOutAnInvoice);
		
		findViewById(R.id.btn_ContractTracking).setOnClickListener(this);
		imgV_ContractTracking = (ImageView)findViewById(R.id.imgV_ContractTracking);
	
		vPager_billQuery = (ViewPager)findViewById(R.id.vPager_billQuery);
		vPager_billQuery.setOnPageChangeListener(this);
		
		ArrayList<View> _viewPages = new ArrayList<View>();
		_viewPages.add(initContractInvoiceViewPage());
		_viewPages.add(initContractTrackingViewPage());
		
		BillQueryViewPagerAdapter _billQueryViewPagerAdapter = new BillQueryViewPagerAdapter(getApplicationContext());
		_billQueryViewPagerAdapter.initViewPages(_viewPages);
	
		vPager_billQuery.setAdapter(_billQueryViewPagerAdapter);
	}
	
	/** 初使化ViewPager的合同开票页面 **/
	private View initContractInvoiceViewPage(){
		View _viewPage = inflateView(R.layout.bill_contract_invoice);
		
		//合同年月从
		txtV_contractDateStart = (TextView) _viewPage.findViewById(R.id.txtV_contractDateStart);
		txtV_contractDateStart.setText(mContractInvoiceQueryParamConYearMontFrom);
		
		lyot_contractDateStart = (RelativeLayout) _viewPage.findViewById(R.id.lyot_contractDateStart);
		lyot_contractDateStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				YearMonthPickerDialog.Builder _builder = new YearMonthPickerDialog.Builder(BillQueryActivity.this);
				_builder.setTitle(R.string.contract_date_start);
				_builder.setMinPickableYear(mMinPickableYear);  //设置时间选择器上可选年份的最小值
				_builder.setMaxPickableYear(mMaxPickableYear);  //设置时间选择器上可选年份的最大值
				_builder.setOnDateSelectedListener(new YearMonthPickerDialog.OnDateSelectedListener() {
					@Override
					public void onDateSelected(Date selectedDate) {
						txtV_contractDateStart.setText(DateUtils.getFormatDateTime(selectedDate, QUERY_PARAM_DATE_FORMAT));
						mContractInvoiceQueryParamConYearMontFrom = DateUtils.getFormatDateTime(selectedDate, QUERY_PARAM_DATE_FORMAT);
					}
				});
				
				YearMonthPickerDialog _datePickerDialog = _builder.create();					
				_datePickerDialog.show();
				
			}
		});
		
		//合同年月至
		txtV_contractDateEnd = (TextView) _viewPage.findViewById(R.id.txtV_contractDateEnd);
		txtV_contractDateEnd.setText(mContractInvoiceQueryParamConYearMontTo);
		
		lyot_contractDateEnd = (RelativeLayout) _viewPage.findViewById(R.id.lyot_contractDateEnd);
		lyot_contractDateEnd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				YearMonthPickerDialog.Builder _builder = new YearMonthPickerDialog.Builder(BillQueryActivity.this);
				_builder.setTitle(R.string.contract_date_end);
				_builder.setMinPickableYear(mMinPickableYear);
				_builder.setMaxPickableYear(mMaxPickableYear);
				_builder.setOnDateSelectedListener(new YearMonthPickerDialog.OnDateSelectedListener() {
					@Override
					public void onDateSelected(Date selectedDate) {
						txtV_contractDateEnd.setText(DateUtils.getFormatDateTime(selectedDate, QUERY_PARAM_DATE_FORMAT));
						mContractInvoiceQueryParamConYearMontTo = DateUtils.getFormatDateTime(selectedDate, QUERY_PARAM_DATE_FORMAT);
					}
				});
				
				YearMonthPickerDialog _datePickerDialog = _builder.create();					
				_datePickerDialog.show();
				
				
			}
		});
		
		//合同号
		edTxt_contractNum = (EditText) _viewPage.findViewById(R.id.edTxt_contractNum);
		
		//查询按钮
		_viewPage.findViewById(R.id.btn_contractInvoiceQuery).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mContractInvoiceQueryParamConCode = edTxt_contractNum.getText().toString().trim();
				
				Intent _intent = new Intent(BillQueryActivity.this,BillContractInvoiceQueryResultActivity.class);
				_intent.putExtra(QueryParams.QUERY_PARAM_BILL_CON_YEAR_MONT_FROM, mContractInvoiceQueryParamConYearMontFrom);
				_intent.putExtra(QueryParams.QUERY_PARAM_BILL_CON_YEAR_MONT_TO, mContractInvoiceQueryParamConYearMontTo);
				_intent.putExtra(QueryParams.QUERY_PARAM_CON_CODE, mContractInvoiceQueryParamConCode);
				
				startActivity(_intent);
				
			}
		});
		
		return _viewPage;
	}
	
	/** 初使化ViewPager的合同正本跟踪页面 **/
	private View initContractTrackingViewPage(){
		View _viewPage = inflateView(R.layout.bill_contract_tracking);
		
		//合同号
		edTxt_contractTrackingNum = (EditText)_viewPage.findViewById(R.id.edTxt_contractTrackingNum);
		
		//我司收发状态
		txtV_corporationSendAndReceiveState = (TextView)_viewPage.findViewById(R.id.txtV_corporationSendAndReceiveState);
		txtV_corporationSendAndReceiveState.setText(R.string.contract_all);
		
		lyot_corporationSendAndReceiveState = (RelativeLayout)_viewPage.findViewById(R.id.lyot_corporationSendAndReceiveState);
		lyot_corporationSendAndReceiveState.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final ArrayList<String> _customerStatusNames = new ArrayList<String>();
				_customerStatusNames.add("全部");
				
				final CustomerStatus[] _customerStatusArray = CustomerStatus.values();
				for(CustomerStatus  _customerStatus : _customerStatusArray){
					_customerStatusNames.add(_customerStatus.getValue());
				}
				
				CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(BillQueryActivity.this, _customerStatusNames, lyot_corporationSendAndReceiveState.getWidth());
				_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
					@Override
					public void onItemClick(int position){
						txtV_corporationSendAndReceiveState.setText(_customerStatusNames.get(position));
						
						int _selPosition = position;
						if(_selPosition > 1){
							mContractTrackingQueryParamCustomerStatus = _customerStatusArray[--_selPosition].name();
						}else {
							mContractTrackingQueryParamCustomerStatus = "";
						}
					}

				});
				_cndSteelSpinner.showAsDropDown(lyot_corporationSendAndReceiveState, 0, -5);
			}
		});
		
		//物流收发状态
		txtV_logisticsSendAndReceiveState = (TextView)_viewPage.findViewById(R.id.txtV_logisticsSendAndReceiveState);
		txtV_logisticsSendAndReceiveState.setText(R.string.contract_all);
		
		lyot_logisticsSendAndReceiveState = (RelativeLayout)_viewPage.findViewById(R.id.lyot_logisticsSendAndReceiveState);
		lyot_logisticsSendAndReceiveState.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final ArrayList<String> _wzStatusNames = new ArrayList<String>();
				_wzStatusNames.add("全部");
				
				final WzStatus[] _wzStatusArray = WzStatus.values();
				for(WzStatus  _wzStatus : _wzStatusArray){
					_wzStatusNames.add(_wzStatus.getValue());
				}
				
				CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(BillQueryActivity.this, _wzStatusNames, lyot_logisticsSendAndReceiveState.getWidth());
				_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
					@Override
					public void onItemClick(int position){
						txtV_logisticsSendAndReceiveState.setText(_wzStatusNames.get(position));
						
						int _selPosition = position;
						if(_selPosition > 1){
							mContractTrackingQueryParamWzStatus = _wzStatusArray[--_selPosition].name();
						}else {
							mContractTrackingQueryParamWzStatus = "";
						}
					}

				});
				_cndSteelSpinner.showAsDropDown(lyot_logisticsSendAndReceiveState, 0, -5);
			}
		});
		
		//查询
		_viewPage.findViewById(R.id.btn_contractTrackingQuery).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mContractTrackingQueryParamConCode = edTxt_contractTrackingNum.getText().toString().trim();
				
				Intent _intent = new Intent(BillQueryActivity.this,BillContractTrackingQueryResultListActivity.class);
				_intent.putExtra(QueryParams.QUERY_PARAM_CON_CODE, mContractTrackingQueryParamConCode);
				_intent.putExtra(QueryParams.QUERY_PARAM_BILL_CUSTOMER_STATUS, mContractTrackingQueryParamCustomerStatus);
				_intent.putExtra(QueryParams.QUERY_PARAM_BILL_WZ_STATUS, mContractTrackingQueryParamWzStatus);
				
				startActivity(_intent);
			}
		});
		
		return _viewPage;
	}
	
	
	@Override
	public void onClick(View view) {
		int _viewId = view.getId();
		switch (_viewId) {
		case R.id.btn_theContractOfMakeOutAnInvoice: {
			vPager_billQuery.setCurrentItem(BILL_PAGE_CONTRACT_INVOICE, true);
			break;
		}
		case R.id.btn_ContractTracking: {
			vPager_billQuery.setCurrentItem(BILL_PAGE_CONTRACT_TRACKING, true);
			break;
		}

		default:
			break;
		}
		
	}
	
	@Override
	public void onPageSelected(int position) {
		switch (position) {
		//Tab的合同开票
		case BILL_PAGE_CONTRACT_INVOICE:
			imgV_theContractOfMakeOutAnInvoice.setVisibility(View.VISIBLE);
			imgV_ContractTracking.setVisibility(View.INVISIBLE);
			
			break;
		//Tab的合同正本跟踪
		case BILL_PAGE_CONTRACT_TRACKING:
			imgV_theContractOfMakeOutAnInvoice.setVisibility(View.INVISIBLE);
			imgV_ContractTracking.setVisibility(View.VISIBLE);
			
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {}
	
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}
}
