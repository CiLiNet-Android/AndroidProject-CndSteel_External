package com.cndsteel.stock.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ExpandableListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.settings.beans.SettingsBean;
import com.cndsteel.stock.adapter.StockAccordingContractQueryResultListAdapter;
import com.cndsteel.stock.bean.StockAccordingContractQueryResultBean;
import com.cndsteel.stock.bean.StockAccordingContractQueryResultListBean;
import com.cndsteel.stock.bean.StockBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

public class StockAccordingContractQueryResultListActivity extends FrameActivity implements PullToRefreshBase.OnRefreshListener<ExpandableListView>,ExpandableListView.OnChildClickListener {
	
	private String mQueryParamStoreDateFrom;
	private String mQueryParamStoreDateTo;
	private String mQueryParamConCode;
	private String mQueryParamWareId;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshExpandableListView listV_stockAccordingContractQueryResult;
	private StockAccordingContractQueryResultListAdapter mStockAccordingContractQueryResultListAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.appendFrameworkCenter(R.layout.activity_stock_according_contract_query_result);
		super.setTopBarTitle(R.string.contract_detail);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void loadDatas(int msgWhat) {
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_DATE_FROM, mQueryParamStoreDateFrom);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_DATE_TO, mQueryParamStoreDateTo);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_CONCODE, mQueryParamConCode);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_WARE_ID, mQueryParamWareId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new StockAccordingContractQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}

	private void initVariables() {
		Intent _intent = getIntent();
		
		mQueryParamStoreDateFrom = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_DATE_FROM);
		mQueryParamStoreDateTo = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_DATE_TO);
		mQueryParamConCode = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_CONCODE);
	    mQueryParamWareId = _intent.getStringExtra(QueryParams.QUERY_PARAM_WARE_ID);
	    
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
	}

	private void initViews() {
		//仓库明细
		listV_stockAccordingContractQueryResult = (PullToRefreshExpandableListView)findViewById(R.id.listV_stockAccordingContractQueryResult);
		listV_stockAccordingContractQueryResult.setMode(Mode.BOTH);
		listV_stockAccordingContractQueryResult.setOnRefreshListener(this);
		listV_stockAccordingContractQueryResult.getRefreshableView().setOnChildClickListener(this);
		listV_stockAccordingContractQueryResult.getRefreshableView().setGroupIndicator(null);
		
		mStockAccordingContractQueryResultListAdapter = new StockAccordingContractQueryResultListAdapter(getApplicationContext());
		mStockAccordingContractQueryResultListAdapter.initDatas(new ArrayList<StockAccordingContractQueryResultBean>());
		listV_stockAccordingContractQueryResult.getRefreshableView().setAdapter(mStockAccordingContractQueryResultListAdapter);
	}
	

	@Override
	public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
		//下拉更新
		if(listV_stockAccordingContractQueryResult.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_stockAccordingContractQueryResult.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View view,int groupPosition, int childPosition, long id) {
		String _conId = ((StockAccordingContractQueryResultBean)mStockAccordingContractQueryResultListAdapter.getGroup(groupPosition)).conId;
		
		StockBean _stockBean = (StockBean)mStockAccordingContractQueryResultListAdapter.getChild(groupPosition, childPosition);
		
		Intent _intent = new Intent(this,StockAccordingContractQueryResultDetailActivity.class);
		_intent.putExtra("stockWareName", _stockBean.wareName);
		_intent.putExtra("pName", _stockBean.pname);
		_intent.putExtra("material", _stockBean.material);
		_intent.putExtra("weight", _stockBean.weight);
		_intent.putExtra("piece", _stockBean.piece);
		_intent.putExtra("spec", _stockBean.spec);
		_intent.putExtra("amount", _stockBean.amount);
		
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_DATE_FROM, mQueryParamStoreDateFrom);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_DATE_TO, mQueryParamStoreDateTo);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_CON_ID, _conId);
		_intent.putExtra(QueryParams.QUERY_PARAM_WARE_ID, _stockBean.wareId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_PNAME_ID, _stockBean.pnameId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_MATERIAL_ID, _stockBean.materialId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_SPEC, _stockBean.spec);
		
		startActivity(_intent);
		
		return false;
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<StockAccordingContractQueryResultListActivity> mWeakReference;
		
		public TheHandler(StockAccordingContractQueryResultListActivity activity){
			mWeakReference = new WeakReference<StockAccordingContractQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final StockAccordingContractQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int msgWhat = msg.what;
				switch (msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					StockAccordingContractQueryResultListBean _stockAccordingContractQueryResultListBean = (StockAccordingContractQueryResultListBean)msg.obj;
					if(_stockAccordingContractQueryResultListBean.code > 0){
						_theActivity.mStockAccordingContractQueryResultListAdapter.refreshDatas(_stockAccordingContractQueryResultListBean.stockAccordingContractQueryResultBeans);
						
						//展开所有分组
						for(int i = 0; i < _stockAccordingContractQueryResultListBean.stockAccordingContractQueryResultBeans.size(); i ++){
							_theActivity.listV_stockAccordingContractQueryResult.getRefreshableView().expandGroup(i);
						}
						
					}else {
						_theActivity.showAlertDialog(R.string.contract_detail, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.contract_detail)), new DialogInterface.OnClickListener() {
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
				case Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_stockAccordingContractQueryResult.onRefreshComplete();
					
					StockAccordingContractQueryResultListBean _stockAccordingContractQueryResultListBean = (StockAccordingContractQueryResultListBean)msg.obj;
					if(_stockAccordingContractQueryResultListBean.code > 0){
						_theActivity.mStockAccordingContractQueryResultListAdapter.refreshDatas(_stockAccordingContractQueryResultListBean.stockAccordingContractQueryResultBeans);
						
						//展开所有分组
						for(int i = 0; i < _stockAccordingContractQueryResultListBean.stockAccordingContractQueryResultBeans.size(); i ++){
							_theActivity.listV_stockAccordingContractQueryResult.getRefreshableView().expandGroup(i);
						}
					}
			
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_stockAccordingContractQueryResult.onRefreshComplete();
					
					StockAccordingContractQueryResultListBean _stockAccordingContractQueryResultListBean = (StockAccordingContractQueryResultListBean)msg.obj;
					if(_stockAccordingContractQueryResultListBean.code > 0){
						_theActivity.mStockAccordingContractQueryResultListAdapter.appendDatas(_stockAccordingContractQueryResultListBean.stockAccordingContractQueryResultBeans);
						
						//展开所有分组
						for(int i = 0; i < _stockAccordingContractQueryResultListBean.stockAccordingContractQueryResultBeans.size(); i ++){
							_theActivity.listV_stockAccordingContractQueryResult.getRefreshableView().expandGroup(i);
						}
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
