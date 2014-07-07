package com.cndsteel.shipment.activity;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.bean.LoadMaterialBean;
import com.cndsteel.framework.bean.LoadPNameBean;
import com.cndsteel.framework.bean.LoadWareHouseBean;
import com.cndsteel.framework.bean.MaterialBean;
import com.cndsteel.framework.bean.PNameBean;
import com.cndsteel.framework.bean.WareHouseBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.utils.DateUtils;
import com.cndsteel.framework.views.dialogs.wheelpicker.YearMonthDayPickerDialog;
import com.cndsteel.framework.views.spinner.AbsSpinner;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.settings.beans.SettingsBean;
import com.cndsteel.shipment.adapter.ShipmentQueryViewPagerAdapter;

public class ShipmentQueryActivity extends FrameActivity implements OnClickListener,OnPageChangeListener {

	/** 按发货单号 **/
	private static final int PAGE_AS_INVOICE_NUMBER = 0;
	/** 按合同 **/
	private static final int PAGE_AS_CONTRACT_NUMBER = 1;
	
	private static final String DISPLAY_DATE_FORMAT = "yyyy/MM/dd";
	
	private static final int SHIP_PAGE_HANDLER_MSG_LOAD_WARE_HOUSE = 0x100;
	
	private static final int CONTRACT_PAGE_HANDLER_MSG_LOAD_PNAME = 0x200;
	private static final int CONTRACT_PAGE_HANDLER_MSG_LOAD_MATERIAL = 0x201;
	
	private int mMinPickableYear = Calendar.getInstance().get(Calendar.YEAR) - 6;
	private int mMaxPickableYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
	
	/**
	 * 按发货单请求页面
	 */
	private TextView txtV_shipment_invoiceDate_start;
	private TextView txtV_shipment_invoiceDate_end;
	private EditText edTxt_shipment_invoiceNum;
	private RelativeLayout lyot_shipment_warehouse;
	private TextView txtV_shipment_warehouse;
	
	/**
	 * 按发货单号请求参数
	 */
	private String mShipQueryParamShipDateFrom;
	private String mShipQueryParamShipDateTo;
	private String mShipQueryParamShipNO;
	private String mShipQueryParamWareId;
	
	
	/**
	 * 按合同查询页面
	 */
	private EditText edTxt_shipmentAsContractNum_contractNum;
	private RelativeLayout lyot_shipmentAsContractNum_goodsName;
	private RelativeLayout lyot_shipmentAsContractNum_material;
	private TextView txtV_shipmentAsContractNum_invoiceDate_start;
	private TextView txtV_shipmentAsContractNum_invoiceDate_end;
	private TextView txtV_shipmentAsContractNum_goodsName;
	private TextView txtV_shipmentAsContractNum_material;
	/**
	 * 按合同查询请求参数
	 */
	private String mContractQueryParamShipDateFrom;
	private String mContractQueryParamShipDateTo;
	private String mContractQueryParamConCode;
	private String mContractQueryParamPNameId;
	private String mContractQueryParamMaterialId;
	
	private String mQueryParamSessionId;

	private ViewPager vPager_shipmentQuery;

	/**
	 * title
	 */
	private ImageButton shipment_img_invoiceNum;
	private ImageButton shipment_img_contractNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendFrameworkCenter(R.layout.activity_shipment_main);
		setTopBarTitle(R.string.appModule_shipment);

		init();
	}

	private void init() {
		initVariables();
		initViews();
	}
	
	private void initVariables(){
		/** 按发货单号请求参数 **/
		mShipQueryParamShipDateFrom = DateUtils.getFormatDateTime(new Date(), QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
		mShipQueryParamShipDateTo = DateUtils.getFormatDateTime(new Date(), QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
		mShipQueryParamShipNO = "";
		mShipQueryParamWareId = "";
		
		/** 按合同请求参数 **/
		mContractQueryParamShipDateFrom = DateUtils.getFormatDateTime(new Date(), QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
		mContractQueryParamShipDateTo = DateUtils.getFormatDateTime(new Date(), QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
		mContractQueryParamConCode = "";
		mContractQueryParamPNameId = "";
		mContractQueryParamMaterialId = "";
		
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
	}

	private void initViews() {
		shipment_img_invoiceNum = (ImageButton) findViewById(R.id.shipment_img_invoiceNum);
		findViewById(R.id.shipment_btn_invoiceNum).setOnClickListener(this);
		
		shipment_img_contractNum = (ImageButton) findViewById(R.id.shipment_img_contractNum);
		findViewById(R.id.shipment_btn_contractNum).setOnClickListener(this);
		
		
		vPager_shipmentQuery = (ViewPager)findViewById(R.id.vPager_shipmentQuery);
		vPager_shipmentQuery.setOnPageChangeListener(this);
		
		ShipmentQueryViewPagerAdapter _shipmentQueryViewPagerAdapter = new ShipmentQueryViewPagerAdapter();
		
		ArrayList<View> _shipmentQueryViewPages = new ArrayList<View>(2);
		_shipmentQueryViewPages.add(initShipQueryViewPage());
		_shipmentQueryViewPages.add(initContractQueryViewPage());
		
		_shipmentQueryViewPagerAdapter.initViewPages(_shipmentQueryViewPages);
		
		vPager_shipmentQuery.setAdapter(_shipmentQueryViewPagerAdapter);
	}
	
	private View initShipQueryViewPage(){
		View _viewPage = inflateView(R.layout.shipment_as_invoice_number);
		
		//发货日期从
		txtV_shipment_invoiceDate_start = (TextView)_viewPage.findViewById(R.id.txtV_shipment_invoiceDate_start);
		txtV_shipment_invoiceDate_start.setText(DateUtils.getFormatDateTime(new Date(), DISPLAY_DATE_FORMAT));
		
		_viewPage.findViewById(R.id.lyot_shipment_invoiceDate_start).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(ShipmentQueryActivity.this);
				_builder.setTitle(R.string.shipment_invoiceDate_start)
						.setMaxPickableYear(mMaxPickableYear)
						.setMinPickableYear(mMinPickableYear)
						.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
							@Override
							public void onDateSelected(Date selectedDate) {
								txtV_shipment_invoiceDate_start.setText(DateUtils.getFormatDateTime(selectedDate, DISPLAY_DATE_FORMAT));
								mShipQueryParamShipDateFrom = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
							}
						}).create().show();
			}
		});
		
		//发货日期至
		txtV_shipment_invoiceDate_end = (TextView)_viewPage.findViewById(R.id.txtV_shipment_invoiceDate_end);
		txtV_shipment_invoiceDate_end.setText(DateUtils.getFormatDateTime(new Date(), DISPLAY_DATE_FORMAT));
		
		_viewPage.findViewById(R.id.lyot_shipment_invoiceDate_end).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(ShipmentQueryActivity.this);
				_builder.setTitle(R.string.shipment_invoiceDate_end)
						.setMaxPickableYear(mMaxPickableYear)
						.setMinPickableYear(mMinPickableYear)
						.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
							@Override
							public void onDateSelected(Date selectedDate) {
								txtV_shipment_invoiceDate_end.setText(DateUtils.getFormatDateTime(selectedDate, DISPLAY_DATE_FORMAT));
								mShipQueryParamShipDateTo = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
							}
						}).create().show();
			}
		});
		
		//发货单号
		edTxt_shipment_invoiceNum = (EditText)_viewPage.findViewById(R.id.edTxt_shipment_invoiceNum);
		
		//仓库
		txtV_shipment_warehouse = (TextView)_viewPage.findViewById(R.id.txtV_shipment_warehouse);
		txtV_shipment_warehouse.setText("全部");
		
		lyot_shipment_warehouse = (RelativeLayout)_viewPage.findViewById(R.id.lyot_shipment_warehouse);
		lyot_shipment_warehouse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				loadDatas(SHIP_PAGE_HANDLER_MSG_LOAD_WARE_HOUSE);
			}
		});
		
		//查询
		_viewPage.findViewById(R.id.btn_shipmentAsInvoiceNum).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mShipQueryParamShipNO = edTxt_shipment_invoiceNum.getText().toString();
				
				Intent _intent = new Intent(ShipmentQueryActivity.this,ShipmentAsInvoiceNumQueryResultListActivity.class);
			
				_intent.putExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_DATE_FROM, mShipQueryParamShipDateFrom);
				_intent.putExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_DATE_TO, mShipQueryParamShipDateTo);
				_intent.putExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_NO, mShipQueryParamShipNO);
				_intent.putExtra(QueryParams.QUERY_PARAM_WARE_ID, mShipQueryParamWareId);
				
				startActivity(_intent);
			}
		});
		
		return _viewPage;
	}
	
	private View initContractQueryViewPage(){
		View _viewPage = inflateView(R.layout.shipment_as_contract_number);
		
		//发货日期从
		txtV_shipmentAsContractNum_invoiceDate_start = (TextView)_viewPage.findViewById(R.id.txtV_shipmentAsContractNum_invoiceDate_start);
		txtV_shipmentAsContractNum_invoiceDate_start.setText(DateUtils.getFormatDate(DISPLAY_DATE_FORMAT));
		
		_viewPage.findViewById(R.id.lyot_shipmentAsContractNum_invoiceDate_start).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(ShipmentQueryActivity.this);
				_builder.setTitle(R.string.shipment_invoiceDate_start)
						.setMaxPickableYear(mMaxPickableYear)
						.setMinPickableYear(mMinPickableYear)
						.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
							@Override
							public void onDateSelected(Date selectedDate) {
								txtV_shipmentAsContractNum_invoiceDate_start.setText(DateUtils.getFormatDateTime(selectedDate, DISPLAY_DATE_FORMAT));
								mContractQueryParamShipDateFrom = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
							}
						}).create().show();
			}
		});
		
		//发送日期至
		txtV_shipmentAsContractNum_invoiceDate_end = (TextView)_viewPage.findViewById(R.id.txtV_shipmentAsContractNum_invoiceDate_end);
		txtV_shipmentAsContractNum_invoiceDate_end.setText(DateUtils.getFormatDate(DISPLAY_DATE_FORMAT));
		
		_viewPage.findViewById(R.id.lyot_shipmentAsContractNum_invoiceDate_end).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(ShipmentQueryActivity.this);
				_builder.setTitle(R.string.shipment_invoiceDate_end)
						.setMaxPickableYear(mMaxPickableYear)
						.setMinPickableYear(mMinPickableYear)
						.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
							@Override
							public void onDateSelected(Date selectedDate) {
								txtV_shipmentAsContractNum_invoiceDate_end.setText(DateUtils.getFormatDateTime(selectedDate, DISPLAY_DATE_FORMAT));
								mContractQueryParamShipDateTo = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
							}
						}).create().show();
			}
		});
		
		//合同号
		edTxt_shipmentAsContractNum_contractNum = (EditText)_viewPage.findViewById(R.id.edTxt_shipmentAsContractNum_contractNum);
		
		//品名
		txtV_shipmentAsContractNum_goodsName = (TextView)_viewPage.findViewById(R.id.txtV_shipmentAsContractNum_goodsName);
		txtV_shipmentAsContractNum_goodsName.setText("全部");
		
		lyot_shipmentAsContractNum_goodsName = (RelativeLayout)_viewPage.findViewById(R.id.lyot_shipmentAsContractNum_goodsName);
		lyot_shipmentAsContractNum_goodsName.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				loadDatas(CONTRACT_PAGE_HANDLER_MSG_LOAD_PNAME);
			}
		});
		
		//材质
		txtV_shipmentAsContractNum_material = (TextView)_viewPage.findViewById(R.id.txtV_shipmentAsContractNum_material);
		txtV_shipmentAsContractNum_material.setText("全部");
		
		lyot_shipmentAsContractNum_material = (RelativeLayout)_viewPage.findViewById(R.id.lyot_shipmentAsContractNum_material);
		lyot_shipmentAsContractNum_material.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				loadDatas(CONTRACT_PAGE_HANDLER_MSG_LOAD_MATERIAL);
			}
		});
		
		//查询
		_viewPage.findViewById(R.id.btn_shipmentAsContractNum).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mContractQueryParamConCode = edTxt_shipmentAsContractNum_contractNum.getText().toString();
				
				Intent _intent = new Intent(ShipmentQueryActivity.this,ShipmentAsContractNumQueryResultListActivity.class);
				
				_intent.putExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_DATE_FROM, mContractQueryParamShipDateFrom);
				_intent.putExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_DATE_TO, mContractQueryParamShipDateTo);
				_intent.putExtra(QueryParams.QUERY_PARAM_CON_CODE, mContractQueryParamConCode);
				_intent.putExtra(QueryParams.QUERY_PARAM_PNAME_ID, mContractQueryParamPNameId);
				_intent.putExtra(QueryParams.QUERY_PARAM_MATERIAL_ID, mContractQueryParamMaterialId);
				
				startActivity(_intent);
			}
		});
		
		return _viewPage;
	}
	
	private void loadDatas(int msgWhat){
		showLoadingProgressDialog();
		
		switch (msgWhat) {
		case SHIP_PAGE_HANDLER_MSG_LOAD_WARE_HOUSE: {
			LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
			
			mWebServiceThread = new WebServiceThread(new LoadWareHouseBean(), _webServiceRequestParams, mTheHandler, msgWhat);
			
			break;
		}
		case CONTRACT_PAGE_HANDLER_MSG_LOAD_PNAME: {
			LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
			
			mWebServiceThread = new WebServiceThread(new LoadPNameBean(), _webServiceRequestParams, mTheHandler, msgWhat);
			
			break;
		}
		case CONTRACT_PAGE_HANDLER_MSG_LOAD_MATERIAL: {
			LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PNAME_ID, mContractQueryParamPNameId);
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
			
			mWebServiceThread = new WebServiceThread(new LoadMaterialBean(), _webServiceRequestParams, mTheHandler, msgWhat);
			
			break;
		}
		default:
			break;
		}
		
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<ShipmentQueryActivity> mWeakReference;
		
		public TheHandler(ShipmentQueryActivity activity){
			mWeakReference = new WeakReference<ShipmentQueryActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final ShipmentQueryActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch(_msgWhat){
				case SHIP_PAGE_HANDLER_MSG_LOAD_WARE_HOUSE: {
					_theActivity.dismissProgressDialog();
					
					//注入数据
					LoadWareHouseBean _loadWareHouseBean = (LoadWareHouseBean)msg.obj;
					
					final ArrayList<WareHouseBean> _wareHouseBeans = new ArrayList<WareHouseBean>();
					_wareHouseBeans.add(new WareHouseBean("","全部"));
					if(_loadWareHouseBean.code > 0){
						_wareHouseBeans.addAll(_wareHouseBeans.size(), _loadWareHouseBean.wareHouseBeans);
					}
					
					final ArrayList<String> _wareHouseNames = new ArrayList<String>();
					for(WareHouseBean _wareHouseBean : _wareHouseBeans){
						_wareHouseNames.add(_wareHouseBean.name);
					}
					
					CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(_theActivity.getApplicationContext(), _wareHouseNames, _theActivity.lyot_shipment_warehouse.getWidth());
					_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
						@Override
						public void onItemClick(int position){
							_theActivity.txtV_shipment_warehouse.setText(_wareHouseNames.get(position));
							_theActivity.mShipQueryParamWareId = _wareHouseBeans.get(position).id;
						}
	
					});
					_cndSteelSpinner.showAsDropDown(_theActivity.lyot_shipment_warehouse, 0, -5);
					
					break;
				}
				case CONTRACT_PAGE_HANDLER_MSG_LOAD_PNAME: {
					_theActivity.dismissProgressDialog();
					
					LoadPNameBean _loadPNameBean = (LoadPNameBean)msg.obj;
					
					final ArrayList<PNameBean> _pNameBeans = new ArrayList<PNameBean>();
					_pNameBeans.add(new PNameBean("","全部"));
					if(_loadPNameBean.code > 0){
						_pNameBeans.addAll(_pNameBeans.size(), _loadPNameBean.pNameBeans);
					}
					
					final ArrayList<String> _pNames = new ArrayList<String>();
					for(PNameBean _pNameBean : _pNameBeans){
						_pNames.add(_pNameBean.name);
					}
					
					CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(_theActivity.getApplicationContext(), _pNames, _theActivity.lyot_shipmentAsContractNum_goodsName.getWidth());
					_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
						@Override
						public void onItemClick(int position){
							_theActivity.txtV_shipmentAsContractNum_goodsName.setText(_pNames.get(position));
							_theActivity.mContractQueryParamPNameId = _pNameBeans.get(position).id;
							
							_theActivity.txtV_shipmentAsContractNum_material.setText("全部");
							_theActivity.mContractQueryParamMaterialId = "";
						}
					});
					_cndSteelSpinner.showAsDropDown(_theActivity.lyot_shipmentAsContractNum_goodsName, 0, -5);
					
					break;
				}
				case CONTRACT_PAGE_HANDLER_MSG_LOAD_MATERIAL: {
					_theActivity.dismissProgressDialog();
					
					LoadMaterialBean _loadMaterialBean = (LoadMaterialBean)msg.obj;
					
					final ArrayList<MaterialBean> _materialBeans = new ArrayList<MaterialBean>();
					_materialBeans.add(new MaterialBean("","全部"));
					if(_loadMaterialBean.code > 0){
						_materialBeans.addAll(_materialBeans.size(), _loadMaterialBean.materialBeans);
					}
					
					final ArrayList<String> _materialNames = new ArrayList<String>();
					for(MaterialBean _materialBean : _materialBeans){
						_materialNames.add(_materialBean.name);
					}
					
					CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(_theActivity.getApplicationContext(), _materialNames, _theActivity.lyot_shipmentAsContractNum_material.getWidth());
					_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
						@Override
						public void onItemClick(int position){
							_theActivity.txtV_shipmentAsContractNum_material.setText(_materialBeans.get(position).name);
							_theActivity.mContractQueryParamMaterialId = _materialBeans.get(position).id;
						}
					});
					_cndSteelSpinner.showAsDropDown(_theActivity.lyot_shipmentAsContractNum_material, 0, -5);
					
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
		switch (view.getId()) {
		//按发货单号
		case R.id.shipment_btn_invoiceNum:
			vPager_shipmentQuery.setCurrentItem(PAGE_AS_INVOICE_NUMBER);
			break;
		//按合同号
		case R.id.shipment_btn_contractNum:
			vPager_shipmentQuery.setCurrentItem(PAGE_AS_CONTRACT_NUMBER);
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageSelected(int position) {
		switch (position) {
		case PAGE_AS_INVOICE_NUMBER:
			shipment_img_invoiceNum.setVisibility(View.VISIBLE);
			shipment_img_contractNum.setVisibility(View.GONE);
			break;
		case PAGE_AS_CONTRACT_NUMBER:
			shipment_img_invoiceNum.setVisibility(View.GONE);
			shipment_img_contractNum.setVisibility(View.VISIBLE);
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
