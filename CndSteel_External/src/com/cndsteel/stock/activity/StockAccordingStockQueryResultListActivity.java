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
import android.widget.LinearLayout;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.stock.adapter.StockAccordingStockQueryResultListAdapter;
import com.cndsteel.stock.bean.StockAccordingStockQueryResultBean;
import com.cndsteel.stock.bean.StockAccordingStockQueryResultListBean;
import com.cndsteel.stock.bean.StockBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

public class StockAccordingStockQueryResultListActivity extends FrameActivity implements ExpandableListView.OnChildClickListener,PullToRefreshBase.OnRefreshListener<ExpandableListView>{
	
	/** 查询参数 **/
	private String mQueryParamStoreDateFrom;
	private String mQueryParamStoreDateTo;
	private String mQueryParamWareHouseId;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	/** 仓库明细 **/
	private PullToRefreshExpandableListView listV_stockQueryResult;
	private StockAccordingStockQueryResultListAdapter mStockAccordingStockQueryResultListAdapter;
	//private ArrayList<AccordingStockQueryResultBean> mAccordingStockQueryResultBean;

	private LinearLayout lyot_stockQueryResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendFrameworkCenter(R.layout.activity_stock_according_stock_query_result);
		setTopBarTitle(R.string.stock_warehouse_detail);
		
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
		mQueryParamWareHouseId = _intent.getStringExtra(QueryParams.QUERY_PARAM_WARE_ID);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initViews() {
		lyot_stockQueryResult = (LinearLayout)findViewById(R.id.lyot_stockQueryResult);
		
		//仓库明细
		listV_stockQueryResult = (PullToRefreshExpandableListView)findViewById(R.id.listV_stockQueryResult);
		listV_stockQueryResult.setMode(Mode.BOTH);
		listV_stockQueryResult.setOnRefreshListener(this);
		listV_stockQueryResult.getRefreshableView().setOnChildClickListener(this);
		listV_stockQueryResult.getRefreshableView().setGroupIndicator(null);
		
		mStockAccordingStockQueryResultListAdapter = new StockAccordingStockQueryResultListAdapter(getApplicationContext());
		mStockAccordingStockQueryResultListAdapter.initDatas(new ArrayList<StockAccordingStockQueryResultBean>());
		listV_stockQueryResult.getRefreshableView().setAdapter(mStockAccordingStockQueryResultListAdapter);
	}
	
	private void loadDatas(int msgWhat) {
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = createWebServiceRequestParams();

		mWebServiceThread = new WebServiceThread(new StockAccordingStockQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private LinkedHashMap<String, String> createWebServiceRequestParams(){
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_DATE_FROM, mQueryParamStoreDateFrom);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_DATE_TO, mQueryParamStoreDateTo);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_WARE_ID, mQueryParamWareHouseId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		return _webServiceRequestParams;
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<StockAccordingStockQueryResultListActivity> mWeakReference;
		
		public TheHandler(StockAccordingStockQueryResultListActivity activity){
			mWeakReference = new WeakReference<StockAccordingStockQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final StockAccordingStockQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int msgWhat = msg.what;
				switch (msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					StockAccordingStockQueryResultListBean _stockAccordingStockQueryResultListBean = (StockAccordingStockQueryResultListBean)msg.obj;
					if(_stockAccordingStockQueryResultListBean.code > 0){
						_theActivity.mStockAccordingStockQueryResultListAdapter.refreshDatas(_stockAccordingStockQueryResultListBean.accordingStockQueryBeans);
						
						//展开所有分组
						for(int i = 0; i < _stockAccordingStockQueryResultListBean.accordingStockQueryBeans.size(); i ++){
							_theActivity.listV_stockQueryResult.getRefreshableView().expandGroup(i);
						}
						
						_theActivity.lyot_stockQueryResult.setVisibility(View.VISIBLE);
					}else {
						_theActivity.showAlertDialog(R.string.stock_warehouse_detail, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.stock_warehouse_detail)), new DialogInterface.OnClickListener() {
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
					_theActivity.listV_stockQueryResult.onRefreshComplete();
					
					StockAccordingStockQueryResultListBean _stockAccordingStockQueryResultListBean = (StockAccordingStockQueryResultListBean)msg.obj;
					if(_stockAccordingStockQueryResultListBean.code > 0){
						_theActivity.mStockAccordingStockQueryResultListAdapter.refreshDatas(_stockAccordingStockQueryResultListBean.accordingStockQueryBeans);
						
						//展开所有分组
						for(int i = 0; i < _stockAccordingStockQueryResultListBean.accordingStockQueryBeans.size(); i ++){
							_theActivity.listV_stockQueryResult.getRefreshableView().expandGroup(i);
						}
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_stockQueryResult.onRefreshComplete();
					
					StockAccordingStockQueryResultListBean _stockAccordingStockQueryResultListBean = (StockAccordingStockQueryResultListBean)msg.obj;
					if(_stockAccordingStockQueryResultListBean.code > 0){
						_theActivity.mStockAccordingStockQueryResultListAdapter.appendDatas(_stockAccordingStockQueryResultListBean.accordingStockQueryBeans);
						
						//展开所有分组
						for(int i = 0; i < _stockAccordingStockQueryResultListBean.accordingStockQueryBeans.size(); i ++){
							_theActivity.listV_stockQueryResult.getRefreshableView().expandGroup(i);
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

	@Override
	public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
		//下拉更新
		if(listV_stockQueryResult.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_stockQueryResult.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View view,int groupPosition, int childPosition, long id) {
		String _stockWareId = ((StockAccordingStockQueryResultBean)mStockAccordingStockQueryResultListAdapter.getGroup(groupPosition)).wareId;
		String _stockWareName = ((StockAccordingStockQueryResultBean)mStockAccordingStockQueryResultListAdapter.getGroup(groupPosition)).wareName;
		
		StockBean _stockBean = (StockBean)mStockAccordingStockQueryResultListAdapter.getChild(groupPosition, childPosition);
		
		Intent _intent = new Intent(this,StockAccordingStockQueryResultDetailActivity.class);
		_intent.putExtra("title", _stockWareName);
		_intent.putExtra("pName", _stockBean.pname);
		_intent.putExtra("material", _stockBean.material);
		_intent.putExtra("piece", _stockBean.piece);
		_intent.putExtra("weight", _stockBean.weight);
		_intent.putExtra("spec", _stockBean.spec);
		_intent.putExtra("amount", _stockBean.amount);
		
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_DATE_FROM, mQueryParamStoreDateFrom);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_DATE_TO, mQueryParamStoreDateTo);
		_intent.putExtra(QueryParams.QUERY_PARAM_WARE_ID, _stockWareId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_PNAME_ID, _stockBean.pnameId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_MATERIAL_ID, _stockBean.materialId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_SPEC, _stockBean.spec);
		
		startActivity(_intent);
		
		return false;
	}

}
