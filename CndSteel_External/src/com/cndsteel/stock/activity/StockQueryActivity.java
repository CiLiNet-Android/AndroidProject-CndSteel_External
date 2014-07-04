package com.cndsteel.stock.activity;

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
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.utils.DateUtils;
import com.cndsteel.framework.views.dialogs.wheelpicker.YearMonthDayPickerDialog;
import com.cndsteel.framework.views.spinner.AbsSpinner;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.stock.adapter.StockQueryViewPagerAdapter;

public class StockQueryActivity extends FrameActivity implements OnClickListener,
		OnPageChangeListener {

	/** 按库存 **/
	private static final int ACCORDING_STOCK_PAGE_INDEX = 0;
	/** 按合同 **/
	private static final int ACCORDING_CONTRACT_PAGE_INDEX = 1;
	/** 按商品 **/
	private static final int ACCORDING_GOODS_PAGE_INDEX = 2;
	
	private static final int STOCK_PAGE_HANDLER_MSG_LOAD_WAREHOUSES = 0x500;
	private static final int CONTRACT_PAGE_HANDLER_MSG_LOAD_WAREHOUSES = 0x600;
	
	private static final int GOODS_PAGE_HANDLER_MSG_LOAD_WAREHOUSES = 0x700;
	private static final int GOODS_PAGE_HANDLER_MSG_LOAD_PNAME = 0x701;
	private static final int GOODS_PAGE_HANDLER_MSG_LOAD_MATERIAL = 0x702;
	
	private ViewPager vPager_stockQuery;
	
	private static final String TXT_DATE_FORMAT = "yyyy/MM/dd";
	
	private int mMinPickableYear = Calendar.getInstance().get(Calendar.YEAR) - 6;
	private int mMaxPickableYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
	
	
	private String mQueryParamSessionId;
	
	/**
	 * title
	 */
	private ImageView imgV_accordingStocking;
	private ImageView imgV_accordingContract;
	private ImageView imgV_accordingGoods;

	/**
	 * 按库存
	 */
	private TextView txtV_stock_into_date_start;
	private TextView txtV_stock_into_date_end;
	private RelativeLayout lyot_stock_warehouse;
	private TextView txtV_stock_warehouse;
	/** 请求参数 **/
	private String mStockQueryParamStoreDateForm;
	private String mStockQueryParamStoreDateTo;
	private String mStockQueryParamWareHouseId;
	
	/**
	 * 按合同
	 */
	private TextView txtV_StockFragmentContract_into_date_start;
	private TextView txtV_StockFragmentContract_into_date_end;
	private EditText txtV_StockFragmentContract_contract_num;
	private RelativeLayout lyot_StockFragmentContract_warehouse;
	private TextView txtV_StockFragmentContract_warehouse;
	/** 请求参数 **/
	private String mContractQueryParamStoreDateForm;
	private String mContractQueryParamStoreDateTo;
	private String mContractQueryParamWareHouseId;
	private String mContractQueryParamContractCode;

	/**
	 * 按商品
	 */
	private TextView txtV_StockFragmentGoods_into_date_start;
	private TextView txtV_StockFragmentGoods_into_date_end;
	/** 请求参数 **/
	private String mGoodsQueryParamStoreDateForm;
	private String mGoodsQueryParamStoreDateTo;
	private String mGoodsQueryParamPNameId;
	private String mGoodsQueryParamMaterialId;
	private String mGoodsQueryParamWareHouseId;
	
	//品名
	private RelativeLayout lyot_StockFragmentGoods_goodsName;
	private TextView txtV_StockFragmentGoods_goodsName;
	//材质
	private RelativeLayout lyot_StockFragmentGoods_material;
	private TextView txtV_StockFragmentGoods_material;

	private RelativeLayout lyot_StockFragmentGoods_warehouse;
	private TextView txtV_StockFragmentGoods_warehouse;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.appModule_stock);
		appendFrameworkCenter(R.layout.activity_stock_query);

		init();

	}

	private void init() {
		initVariables();
		initViews();
	}

	private void initVariables() {
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
		
		//按仓库
		mStockQueryParamStoreDateForm = DateUtils.getFormatDateTime(new Date(), QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
		mStockQueryParamStoreDateTo = DateUtils.getFormatDateTime(new Date(), QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
		mStockQueryParamWareHouseId = "";
		
		//按合同
		mContractQueryParamStoreDateForm = DateUtils.getFormatDateTime(new Date(), QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
		mContractQueryParamStoreDateTo = DateUtils.getFormatDateTime(new Date(), QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
		mContractQueryParamContractCode = "";
		mContractQueryParamWareHouseId = "";
		
		//按商品
		mGoodsQueryParamStoreDateForm = DateUtils.getFormatDateTime(new Date(), QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
		mGoodsQueryParamStoreDateTo = DateUtils.getFormatDateTime(new Date(), QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
		mGoodsQueryParamPNameId = "";
		mGoodsQueryParamMaterialId = "";
		mGoodsQueryParamWareHouseId = "";
	}

	private void initViews() {
		imgV_accordingStocking = (ImageView) findViewById(R.id.imgV_accordingStocking);
		findViewById(R.id.btn_accordingStocking).setOnClickListener(this);
		
		imgV_accordingContract = (ImageView) findViewById(R.id.imgV_accordingContract);
		findViewById(R.id.btn_accordingContract).setOnClickListener(this);
		
		imgV_accordingGoods = (ImageView) findViewById(R.id.imgV_accordingGoods);
		findViewById(R.id.btn_accordingGoods).setOnClickListener(this);
		
		vPager_stockQuery = (ViewPager) findViewById(R.id.vPager_stockQuery);
		
		ArrayList<View> _viewPages = new ArrayList<View>();
		_viewPages.add(initViewPage(ACCORDING_STOCK_PAGE_INDEX));
		_viewPages.add(initViewPage(ACCORDING_CONTRACT_PAGE_INDEX));
		_viewPages.add(initViewPage(ACCORDING_GOODS_PAGE_INDEX));
		
		StockQueryViewPagerAdapter _stockQueryViewPagerAdapter = new StockQueryViewPagerAdapter(getApplicationContext());
		_stockQueryViewPagerAdapter.initViewPages(_viewPages);
		vPager_stockQuery.setAdapter(_stockQueryViewPagerAdapter);
		vPager_stockQuery.setCurrentItem(ACCORDING_STOCK_PAGE_INDEX);
		vPager_stockQuery.setOnPageChangeListener(this);
	}
	
	private View initViewPage(int viewPageIndex){
		View _pageView = null;
		
		switch(viewPageIndex){
		case ACCORDING_STOCK_PAGE_INDEX: {
			_pageView = inflateView(R.layout.layout_stockquery_according_stock);
			
			//入库日期从
			txtV_stock_into_date_start = (TextView)_pageView.findViewById(R.id.txtV_stock_into_date_start);
			txtV_stock_into_date_start.setText(DateUtils.getFormatDateTime(new Date(), "yyyy/MM/dd"));
			_pageView.findViewById(R.id.lyot_stock_into_date_start).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(StockQueryActivity.this);
					_builder.setTitle(R.string.stock_into_date_start)
							.setMinPickableYear(mMinPickableYear)
							.setMaxPickableYear(mMaxPickableYear)
							.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
								@Override
								public void onDateSelected(Date selectedDate) {
									txtV_stock_into_date_start.setText(DateUtils.getFormatDateTime(selectedDate, TXT_DATE_FORMAT));
									mStockQueryParamStoreDateForm = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
								}
							}).create().show();
				}
			});
			
			//入库日期至
			txtV_stock_into_date_end = (TextView)_pageView.findViewById(R.id.txtV_stock_into_date_end);
			txtV_stock_into_date_end.setText(DateUtils.getFormatDateTime(new Date(), "yyyy/MM/dd"));
			_pageView.findViewById(R.id.lyot_stock_into_date_end).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(StockQueryActivity.this);
					_builder.setTitle(R.string.stock_into_date_end)
							.setMinPickableYear(mMinPickableYear)
							.setMaxPickableYear(mMaxPickableYear)
							.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
								@Override
								public void onDateSelected(Date selectedDate) {
									txtV_stock_into_date_end.setText(DateUtils.getFormatDateTime(selectedDate, TXT_DATE_FORMAT));
									mStockQueryParamStoreDateTo = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
								}
							}).create().show();
				}
			});
			
			//仓库
			txtV_stock_warehouse = (TextView)_pageView.findViewById(R.id.txtV_stock_warehouse);
			txtV_stock_warehouse.setText("全部");
		
			lyot_stock_warehouse = (RelativeLayout)_pageView.findViewById(R.id.lyot_stock_warehouse);
			lyot_stock_warehouse.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					loadDatas(STOCK_PAGE_HANDLER_MSG_LOAD_WAREHOUSES);
				}
				
			});
			
			//库存查询按钮
			_pageView.findViewById(R.id.btn_stock_accordingStock).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent _intent = new Intent(StockQueryActivity.this,StockAccordingStockQueryResultListActivity.class);
					
					Bundle _queryParams = new Bundle();
					_queryParams.putString(QueryParams.QUERY_PARAM_STOCK_DATE_FROM, mStockQueryParamStoreDateForm);
					_queryParams.putString(QueryParams.QUERY_PARAM_STOCK_DATE_TO, mStockQueryParamStoreDateTo);
					_queryParams.putString(QueryParams.QUERY_PARAM_WARE_ID, mStockQueryParamWareHouseId);
					_intent.putExtras(_queryParams);
					
					startActivity(_intent);
				}
			});
			
			break;
		}
		case ACCORDING_CONTRACT_PAGE_INDEX: {
			_pageView = inflateView(R.layout.layout_stockquery_according_contract);
			
			//入库时间从
			txtV_StockFragmentContract_into_date_start = (TextView)_pageView.findViewById(R.id.txtV_StockFragmentContract_into_date_start);
			txtV_StockFragmentContract_into_date_start.setText(DateUtils.getFormatDateTime(new Date(), "yyyy/MM/dd"));
			((RelativeLayout)_pageView.findViewById(R.id.lyot_StockFragmentContract_into_date_start)).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(StockQueryActivity.this);
					_builder.setTitle(R.string.stock_into_date_start)
							.setMinPickableYear(mMinPickableYear)
							.setMaxPickableYear(mMaxPickableYear)
							.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
								@Override
								public void onDateSelected(Date selectedDate) {
									txtV_StockFragmentContract_into_date_start.setText(DateUtils.getFormatDateTime(selectedDate, TXT_DATE_FORMAT));
									mContractQueryParamStoreDateForm = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
								}
							}).create().show();
				}
			});
			
			//入库时间至
			txtV_StockFragmentContract_into_date_end = (TextView)_pageView.findViewById(R.id.txtV_StockFragmentContract_into_date_end);
			txtV_StockFragmentContract_into_date_end.setText(DateUtils.getFormatDateTime(new Date(), "yyyy/MM/dd"));
			_pageView.findViewById(R.id.lyot_StockFragmentContract_into_date_end).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(StockQueryActivity.this);
					_builder.setTitle(R.string.stock_into_date_end)
							.setMinPickableYear(mMinPickableYear)
							.setMaxPickableYear(mMaxPickableYear)
							.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
								@Override
								public void onDateSelected(Date selectedDate) {
									txtV_StockFragmentContract_into_date_end.setText(DateUtils.getFormatDateTime(selectedDate, TXT_DATE_FORMAT));
									mContractQueryParamStoreDateTo = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
								}
							}).create().show();
				}
			});
			
			//合同号
			txtV_StockFragmentContract_contract_num = (EditText)_pageView.findViewById(R.id.txtV_StockFragmentContract_contract_num);
			
			//仓库
			txtV_StockFragmentContract_warehouse = (TextView)_pageView.findViewById(R.id.txtV_StockFragmentContract_warehouse);
			txtV_StockFragmentContract_warehouse.setText("全部");
			
			lyot_StockFragmentContract_warehouse = (RelativeLayout)_pageView.findViewById(R.id.lyot_StockFragmentContract_warehouse);
			lyot_StockFragmentContract_warehouse.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					loadDatas(CONTRACT_PAGE_HANDLER_MSG_LOAD_WAREHOUSES);
				}
			});
			
			//按合同查询按钮
			_pageView.findViewById(R.id.btn_StockFragmentContract).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mContractQueryParamContractCode = txtV_StockFragmentContract_contract_num.getText().toString();
					
					Intent _intent = new Intent(StockQueryActivity.this,StockAccordingContractQueryResultListActivity.class);
					
					Bundle _queryParams = new Bundle();
					_queryParams.putString(QueryParams.QUERY_PARAM_STOCK_DATE_FROM, mContractQueryParamStoreDateForm);
					_queryParams.putString(QueryParams.QUERY_PARAM_STOCK_DATE_TO, mContractQueryParamStoreDateTo);
					_queryParams.putString(QueryParams.QUERY_PARAM_STOCK_CONCODE, mContractQueryParamContractCode);
					_queryParams.putString(QueryParams.QUERY_PARAM_WARE_ID, mContractQueryParamWareHouseId);
					_intent.putExtras(_queryParams);
					
					startActivity(_intent);
				}
			});
			
			break;
		}
		case ACCORDING_GOODS_PAGE_INDEX: {
			_pageView = inflateView(R.layout.layout_stockquery_according_goods);
			
			//入库时间从
			txtV_StockFragmentGoods_into_date_start = (TextView)_pageView.findViewById(R.id.txtV_StockFragmentGoods_into_date_start);
			txtV_StockFragmentGoods_into_date_start.setText(DateUtils.getFormatDateTime(new Date(), "yyyy/MM/dd"));
			_pageView.findViewById(R.id.lyot_StockFragmentGoods_into_date_start).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(StockQueryActivity.this);
					_builder.setTitle(R.string.stock_into_date_start)
							.setMinPickableYear(mMinPickableYear)
							.setMaxPickableYear(mMaxPickableYear)
							.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
								@Override
								public void onDateSelected(Date selectedDate) {
									txtV_StockFragmentGoods_into_date_start.setText(DateUtils.getFormatDateTime(selectedDate, TXT_DATE_FORMAT));
									mGoodsQueryParamStoreDateForm = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
								}
							}).create().show();
				}
			});
			
			//入库时间至
			txtV_StockFragmentGoods_into_date_end = (TextView)_pageView.findViewById(R.id.txtV_StockFragmentGoods_into_date_end);
			txtV_StockFragmentGoods_into_date_end.setText(DateUtils.getFormatDateTime(new Date(), "yyyy/MM/dd"));
			_pageView.findViewById(R.id.lyot_StockFragmentGoods_into_date_end).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					YearMonthDayPickerDialog.Builder _builder = new YearMonthDayPickerDialog.Builder(StockQueryActivity.this);
					_builder.setTitle(R.string.stock_into_date_end)
							.setMinPickableYear(mMinPickableYear)
							.setMaxPickableYear(mMaxPickableYear)
							.setOnDateSelectedListener(new YearMonthDayPickerDialog.OnDateSelectedListener() {
								@Override
								public void onDateSelected(Date selectedDate) {
									txtV_StockFragmentGoods_into_date_end.setText(DateUtils.getFormatDateTime(selectedDate, TXT_DATE_FORMAT));
									mGoodsQueryParamStoreDateTo = DateUtils.getFormatDateTime(selectedDate, QueryParams.DEFAULT_QUERY_PARAM_DATE_FORMAT);
								}
							}).create().show();
				}
			});
			
			//品名
			txtV_StockFragmentGoods_goodsName = (TextView)_pageView.findViewById(R.id.txtV_StockFragmentGoods_goodsName);
			txtV_StockFragmentGoods_goodsName.setText("全部");
			lyot_StockFragmentGoods_goodsName = (RelativeLayout)_pageView.findViewById(R.id.lyot_StockFragmentGoods_goodsName);
			lyot_StockFragmentGoods_goodsName.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					loadDatas(GOODS_PAGE_HANDLER_MSG_LOAD_PNAME);
				}
			});
			
			//材质
			txtV_StockFragmentGoods_material = (TextView)_pageView.findViewById(R.id.txtV_StockFragmentGoods_material);
			txtV_StockFragmentGoods_material.setText("全部");
			lyot_StockFragmentGoods_material = (RelativeLayout)_pageView.findViewById(R.id.lyot_StockFragmentGoods_material);
			lyot_StockFragmentGoods_material.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					loadDatas(GOODS_PAGE_HANDLER_MSG_LOAD_MATERIAL);
				}
			});
			
			//仓库
			txtV_StockFragmentGoods_warehouse = (TextView)_pageView.findViewById(R.id.txtV_StockFragmentGoods_warehouse);
			txtV_StockFragmentGoods_warehouse.setText("全部");
			lyot_StockFragmentGoods_warehouse = (RelativeLayout)_pageView.findViewById(R.id.lyot_StockFragmentGoods_warehouse);
			lyot_StockFragmentGoods_warehouse.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					loadDatas(GOODS_PAGE_HANDLER_MSG_LOAD_WAREHOUSES);
				}
			});
			
			//按商品查询按钮
			_pageView.findViewById(R.id.btn_StockFragmentGoods).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent _intent = new Intent(StockQueryActivity.this,StockAccordingGoodsQueryResultListActivity.class);
					
					Bundle _queryParams = new Bundle();
					_queryParams.putString(QueryParams.QUERY_PARAM_STOCK_DATE_FROM, mGoodsQueryParamStoreDateForm);
					_queryParams.putString(QueryParams.QUERY_PARAM_STOCK_DATE_TO, mGoodsQueryParamStoreDateTo);
					_queryParams.putString(QueryParams.QUERY_PARAM_PNAME_ID, mGoodsQueryParamPNameId);
					_queryParams.putString(QueryParams.QUERY_PARAM_MATERIAL_ID, mGoodsQueryParamMaterialId);
					_queryParams.putString(QueryParams.QUERY_PARAM_WARE_ID, mGoodsQueryParamWareHouseId);
					_intent.putExtras(_queryParams);
					
					startActivity(_intent);
				}
			});
			
			break;
		}
		default: 
			break;
		}
		
		return _pageView;
	}

	//查询分类标题点击
	@Override
	public void onClick(View view) {
		int _viewId = view.getId();
		switch (_viewId) {
		case R.id.btn_accordingStocking: {
			vPager_stockQuery.setCurrentItem(ACCORDING_STOCK_PAGE_INDEX, true);
			break;
		}
		case R.id.btn_accordingContract: {
			vPager_stockQuery.setCurrentItem(ACCORDING_CONTRACT_PAGE_INDEX, true);
			break;
		}
		case R.id.btn_accordingGoods: {
			vPager_stockQuery.setCurrentItem(ACCORDING_GOODS_PAGE_INDEX, true);
			break;
		}
		default:
			break;
		}
	}


	/**
	 * ViewPager.setOnPageChangeListener的事件处理
	 **/
	@Override
	public void onPageSelected(int position) {
		switch (position) {
		// title--按库存
		case ACCORDING_STOCK_PAGE_INDEX:
			imgV_accordingStocking.setVisibility(View.VISIBLE);
			imgV_accordingContract.setVisibility(View.GONE);
			imgV_accordingGoods.setVisibility(View.GONE);
			break;
		// title--按合同
		case ACCORDING_CONTRACT_PAGE_INDEX:
			imgV_accordingStocking.setVisibility(View.GONE);
			imgV_accordingContract.setVisibility(View.VISIBLE);
			imgV_accordingGoods.setVisibility(View.GONE);
			break;
		// title--按商品
		case ACCORDING_GOODS_PAGE_INDEX:
			imgV_accordingStocking.setVisibility(View.GONE);
			imgV_accordingContract.setVisibility(View.GONE);
			imgV_accordingGoods.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}

	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}
	
	private void loadDatas(int msgWhat){
		showLoadingProgressDialog();
		
		switch(msgWhat){
		//加载仓库
		case STOCK_PAGE_HANDLER_MSG_LOAD_WAREHOUSES: {
			LinkedHashMap<String, String> _webServiceRequestParams = createWebServiceRequestParams();
			mWebServiceThread = new WebServiceThread(new LoadWareHouseBean(), _webServiceRequestParams, mTheHandler, STOCK_PAGE_HANDLER_MSG_LOAD_WAREHOUSES);
			mWebServiceThread.start();
			
			break;
		}
		case CONTRACT_PAGE_HANDLER_MSG_LOAD_WAREHOUSES: {
			LinkedHashMap<String, String> _webServiceRequestParams = createWebServiceRequestParams();
			mWebServiceThread = new WebServiceThread(new LoadWareHouseBean(), _webServiceRequestParams, mTheHandler, CONTRACT_PAGE_HANDLER_MSG_LOAD_WAREHOUSES);
			mWebServiceThread.start();
			
			break;
		}
		case GOODS_PAGE_HANDLER_MSG_LOAD_WAREHOUSES: {
			LinkedHashMap<String, String> _webServiceRequestParams = createWebServiceRequestParams();
			mWebServiceThread = new WebServiceThread(new LoadWareHouseBean(), _webServiceRequestParams, mTheHandler, GOODS_PAGE_HANDLER_MSG_LOAD_WAREHOUSES);
			mWebServiceThread.start();
			
			break;
		}
		//加载品名
		case GOODS_PAGE_HANDLER_MSG_LOAD_PNAME: {
			LinkedHashMap<String, String> _webServiceRequestParams = createWebServiceRequestParams();
			mWebServiceThread = new WebServiceThread(new LoadPNameBean(), _webServiceRequestParams, mTheHandler, GOODS_PAGE_HANDLER_MSG_LOAD_PNAME);
			mWebServiceThread.start();
			
			break;
		}
		//加载材质
		case GOODS_PAGE_HANDLER_MSG_LOAD_MATERIAL: {
			if(!TextUtils.isEmpty(mGoodsQueryParamPNameId)){
				LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>(2);
				_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PNAME_ID, mGoodsQueryParamPNameId);
				_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
				
				mWebServiceThread = new WebServiceThread(new LoadMaterialBean(), _webServiceRequestParams, mTheHandler, GOODS_PAGE_HANDLER_MSG_LOAD_MATERIAL);
				mWebServiceThread.start();
			}
			
			break;
		}
		default: 
			break;
		}
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<StockQueryActivity> mWeakReference;
		
		public TheHandler(StockQueryActivity activity){
			mWeakReference = new WeakReference<StockQueryActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final StockQueryActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch(_msgWhat){
				case STOCK_PAGE_HANDLER_MSG_LOAD_WAREHOUSES: {
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
					
					CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(_theActivity.getApplicationContext(), _wareHouseNames, _theActivity.lyot_stock_warehouse.getWidth());
					_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
						@Override
						public void onItemClick(int position){
							_theActivity.txtV_stock_warehouse.setText(_wareHouseNames.get(position));
							_theActivity.mStockQueryParamWareHouseId = _wareHouseBeans.get(position).id;
						}
	
					});
					_cndSteelSpinner.showAsDropDown(_theActivity.lyot_stock_warehouse, 0, -5);
					
					break;
				}
				case CONTRACT_PAGE_HANDLER_MSG_LOAD_WAREHOUSES: {
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
					
					CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(_theActivity.getApplicationContext(), _wareHouseNames, _theActivity.lyot_StockFragmentContract_warehouse.getWidth());
					_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
						@Override
						public void onItemClick(int position){
							_theActivity.txtV_StockFragmentContract_warehouse.setText(_wareHouseNames.get(position));
							_theActivity.mContractQueryParamWareHouseId = _wareHouseBeans.get(position).id;
						}
	
					});
					_cndSteelSpinner.showAsDropDown(_theActivity.lyot_StockFragmentContract_warehouse, 0, -5);
					
					break;
				}
				case GOODS_PAGE_HANDLER_MSG_LOAD_WAREHOUSES: {
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
					
					CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(_theActivity.getApplicationContext(), _wareHouseNames, _theActivity.lyot_StockFragmentGoods_warehouse.getWidth());
					_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
						@Override
						public void onItemClick(int position){
							_theActivity.txtV_StockFragmentGoods_warehouse.setText(_wareHouseNames.get(position));
							_theActivity.mGoodsQueryParamWareHouseId = _wareHouseBeans.get(position).id;
						}
	
					});
					_cndSteelSpinner.showAsDropDown(_theActivity.lyot_StockFragmentGoods_warehouse, 0, -5);
					
					break;
				}
				case GOODS_PAGE_HANDLER_MSG_LOAD_PNAME: {
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
					
					CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(_theActivity.getApplicationContext(), _pNames, _theActivity.lyot_StockFragmentGoods_goodsName.getWidth());
					_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
						@Override
						public void onItemClick(int position){
							_theActivity.txtV_StockFragmentGoods_goodsName.setText(_pNames.get(position));
							_theActivity.mGoodsQueryParamPNameId = _pNameBeans.get(position).id;
							
							_theActivity.txtV_StockFragmentGoods_material.setText("全部");
							_theActivity.mGoodsQueryParamMaterialId = "";
						}
					});
					_cndSteelSpinner.showAsDropDown(_theActivity.lyot_StockFragmentGoods_goodsName, 0, -5);
					
					break;
				}
				case GOODS_PAGE_HANDLER_MSG_LOAD_MATERIAL: {
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
					
					CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(_theActivity.getApplicationContext(), _materialNames, _theActivity.lyot_StockFragmentGoods_material.getWidth());
					_cndSteelSpinner.setOnItemClickListener(new AbsSpinner.OnItemClickListener() {
						@Override
						public void onItemClick(int position){
							_theActivity.txtV_StockFragmentGoods_material.setText(_materialBeans.get(position).name);
							_theActivity.mGoodsQueryParamMaterialId = _materialBeans.get(position).id;
						}
					});
					_cndSteelSpinner.showAsDropDown(_theActivity.lyot_StockFragmentGoods_material, 0, -5);
					
					break;
				}
				default: 
					break;
				}
			}
		}
	}
	
	private LinkedHashMap<String, String> createWebServiceRequestParams(){
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		return _webServiceRequestParams;
	}
}
