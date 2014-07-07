package com.cndsteel.stock.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.utils.DateUtils;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.settings.beans.SettingsBean;
import com.cndsteel.stock.adapter.StockQueryResultDetailListAdapter;
import com.cndsteel.stock.bean.StockAccordingStockQueryResultDetailListBean;
import com.cndsteel.stock.bean.StockBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 规格明细
 * @author zhxl
 *
 */
public class StockQueryResultDetailListActivity extends FrameActivity {

	private String mQueryParamWareId;
	private String mQueryParamStoreDate;
	private String mQueryParamPNameId;
	private String mQueryParamMaterialId;
	private String mQueryParamSpec;
	private String mQueryParamConId;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshListView listV_stockWarehouseDetailList;
	private StockQueryResultDetailListAdapter mStockAccordingStockQueryResultDetailListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setTopBarTitle(R.string.stock_spec_detials);
		super.appendFrameworkCenter(R.layout.activity_stock_according_stock_query_result_detail_list);
	
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}
	
	private void loadDatas(int msgWhat) {
		showLoadingProgressDialog();
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_WARE_ID, mQueryParamWareId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_STORE_DATE, DateUtils.getFormatDateTime(DateUtils.getDateFromString(mQueryParamStoreDate, "yyyy/MM/dd"), "yyyy-MM-dd"));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_PNAME_ID, mQueryParamPNameId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_MATERIAL_ID, mQueryParamMaterialId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_SPEC, mQueryParamSpec);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_CON_ID, mQueryParamConId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new StockAccordingStockQueryResultDetailListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}

	private void initViews(){
		listV_stockWarehouseDetailList = (PullToRefreshListView)findViewById(R.id.listV_stockWarehouseDetailList);
		listV_stockWarehouseDetailList.setMode(Mode.DISABLED);
		
		mStockAccordingStockQueryResultDetailListAdapter = new StockQueryResultDetailListAdapter(getApplicationContext());
		mStockAccordingStockQueryResultDetailListAdapter.initDatas(new ArrayList<StockBean>());
		listV_stockWarehouseDetailList.setAdapter(mStockAccordingStockQueryResultDetailListAdapter);
	}

	private void initVariables(){
		Intent _intent = getIntent();
		
		mQueryParamWareId = _intent.getStringExtra(QueryParams.QUERY_PARAM_WARE_ID);
		mQueryParamStoreDate = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_STORE_DATE);
		mQueryParamPNameId = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_PNAME_ID);
		mQueryParamMaterialId = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_MATERIAL_ID);
		mQueryParamSpec = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_SPEC);
		mQueryParamConId = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_CON_ID);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<StockQueryResultDetailListActivity> mWeakReference;
		
		public TheHandler(StockQueryResultDetailListActivity activity){
			mWeakReference = new WeakReference<StockQueryResultDetailListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final StockQueryResultDetailListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch(_msgWhat){
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					StockAccordingStockQueryResultDetailListBean _stockAccordingStockQueryResultDetailListBean = (StockAccordingStockQueryResultDetailListBean)msg.obj;
					if(_stockAccordingStockQueryResultDetailListBean.code > 0){
						_theActivity.mStockAccordingStockQueryResultDetailListAdapter.refreshDatas(_stockAccordingStockQueryResultDetailListBean.stockBeans);
					}else {
						_theActivity.showAlertDialog(R.string.stock_spec_detials, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.stock_spec_detials)), new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if(which == DialogInterface.BUTTON_NEUTRAL){
									_theActivity.finish();
								}
							}
						});
					}
					
					break;
				}
				default: 
					break;
				}
			}
		}
	}
}
