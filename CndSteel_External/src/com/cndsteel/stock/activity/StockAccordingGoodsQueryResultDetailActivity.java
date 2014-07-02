package com.cndsteel.stock.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.stock.adapter.StockAccordingGoodsQueryResultDetailAdapter;
import com.cndsteel.stock.bean.StockAccordingContractQueryResultDetailBean;
import com.cndsteel.stock.bean.StockAccordingGoodsQueryResultDetailBean;
import com.cndsteel.stock.bean.StockBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 根据商品查询明细
 * @author zhxl
 *
 */
public class StockAccordingGoodsQueryResultDetailActivity extends FrameActivity implements AdapterView.OnItemClickListener,PullToRefreshBase.OnRefreshListener<ListView> {

	private String mQueryParamStoreDateFrom;
	private String mQueryParamStoreDateTo;
	private String mQueryParamWareId;
	private String mQueryParamPNameId;
	private String mQueryParamMaterialId;
	private String mQueryParamSpec;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshListView listV_accordingGoodsQueryResultDetailWarehouseList;
	private StockAccordingGoodsQueryResultDetailAdapter mStockAccordingGoodsQueryResultDetailAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendFrameworkCenter(R.layout.activity_stock_according_goods_query_result_detail);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void initVariables() {
		Intent _intent = getIntent();
		
		mQueryParamStoreDateFrom = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_DATE_FROM);
		mQueryParamStoreDateTo = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_DATE_TO);
		mQueryParamWareId = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_WARE_ID);
		mQueryParamPNameId = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_PNAME_ID);
		mQueryParamMaterialId = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_MATERIAL_ID);
		mQueryParamSpec = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_SPEC);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}
	
	private void initViews(){
		Intent _intent = getIntent();
		
		setTopBarTitle(_intent.getStringExtra("stockWareName"));
		
		((TextView)findViewById(R.id.txtV_accordingGoodsQueryResultDetailPName)).setText(_intent.getStringExtra("pName"));
		((TextView)findViewById(R.id.txtV_accordingGoodsQueryResultDetailMaterial)).setText(_intent.getStringExtra("material"));
		((TextView)findViewById(R.id.txtV_accordingGoodsQueryResultDetailWeight)).setText(_intent.getStringExtra("weight"));
		((TextView)findViewById(R.id.txtV_accordingGoodsQueryResultDetailPiece)).setText(_intent.getStringExtra("piece"));
		((TextView)findViewById(R.id.txtV_accordingGoodsQueryResultDetailSpec)).setText(_intent.getStringExtra("spec"));
		((TextView)findViewById(R.id.txtV_accordingGoodsQueryResultDetailAmount)).setText(_intent.getStringExtra("amount"));
		
		listV_accordingGoodsQueryResultDetailWarehouseList = (PullToRefreshListView)findViewById(R.id.listV_accordingGoodsQueryResultDetailWarehouseList);
		listV_accordingGoodsQueryResultDetailWarehouseList.setMode(Mode.BOTH);
		listV_accordingGoodsQueryResultDetailWarehouseList.setOnRefreshListener(this);
		listV_accordingGoodsQueryResultDetailWarehouseList.setOnItemClickListener(this);
		
		mStockAccordingGoodsQueryResultDetailAdapter = new StockAccordingGoodsQueryResultDetailAdapter(getApplicationContext());
		mStockAccordingGoodsQueryResultDetailAdapter.initDatas(new ArrayList<StockBean>());
		listV_accordingGoodsQueryResultDetailWarehouseList.setAdapter(mStockAccordingGoodsQueryResultDetailAdapter);
		
	}
	
	private void loadDatas(int msgWhat){
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_DATE_FROM, mQueryParamStoreDateFrom);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_DATE_TO, mQueryParamStoreDateTo);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_PNAME_ID, mQueryParamPNameId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_MATERIAL_ID, mQueryParamMaterialId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_WARE_ID, mQueryParamWareId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_SPEC, mQueryParamSpec);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new StockAccordingGoodsQueryResultDetailBean(),_webServiceRequestParams,  mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<StockAccordingGoodsQueryResultDetailActivity> mWeakReference;
		
		public TheHandler(StockAccordingGoodsQueryResultDetailActivity activity){
			mWeakReference = new WeakReference<StockAccordingGoodsQueryResultDetailActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final StockAccordingGoodsQueryResultDetailActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int msgWhat = msg.what;
				switch (msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					StockAccordingGoodsQueryResultDetailBean _stockAccordingGoodsQueryResultDetailBean = (StockAccordingGoodsQueryResultDetailBean)msg.obj;
					if(_stockAccordingGoodsQueryResultDetailBean.code > 0){
						_theActivity.mStockAccordingGoodsQueryResultDetailAdapter.refreshDatas(_stockAccordingGoodsQueryResultDetailBean.stockBeans);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_accordingGoodsQueryResultDetailWarehouseList.onRefreshComplete();
					
					StockAccordingGoodsQueryResultDetailBean _stockAccordingGoodsQueryResultDetailBean = (StockAccordingGoodsQueryResultDetailBean)msg.obj;
					if(_stockAccordingGoodsQueryResultDetailBean.code > 0){
						_theActivity.mStockAccordingGoodsQueryResultDetailAdapter.refreshDatas(_stockAccordingGoodsQueryResultDetailBean.stockBeans);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_accordingGoodsQueryResultDetailWarehouseList.onRefreshComplete();
					
					StockAccordingGoodsQueryResultDetailBean _stockAccordingGoodsQueryResultDetailBean = (StockAccordingGoodsQueryResultDetailBean)msg.obj;
					if(_stockAccordingGoodsQueryResultDetailBean.code > 0){
						_theActivity.mStockAccordingGoodsQueryResultDetailAdapter.appendDatas(_stockAccordingGoodsQueryResultDetailBean.stockBeans);
					}
					
					break;
				}
				default:
					break;
				}
			}
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		//下拉更新
		if(listV_accordingGoodsQueryResultDetailWarehouseList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_accordingGoodsQueryResultDetailWarehouseList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
		StockBean _stockBean = (StockBean)parent.getAdapter().getItem(position);
		
		Intent _intent = new Intent(this,StockQueryResultDetailListActivity.class);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_WARE_ID, mQueryParamWareId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_STORE_DATE, _stockBean.storeDate);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_PNAME_ID, mQueryParamPNameId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_MATERIAL_ID, mQueryParamMaterialId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_SPEC, mQueryParamSpec);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_CON_ID, "");
		
		startActivity(_intent);
		
	}


}
