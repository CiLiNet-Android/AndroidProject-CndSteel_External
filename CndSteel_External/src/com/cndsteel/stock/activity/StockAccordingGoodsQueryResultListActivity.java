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
import com.cndsteel.stock.adapter.StockAccordingGoodsQueryResultListAdapter;
import com.cndsteel.stock.bean.StockAccordingGoodsQueryResultBean;
import com.cndsteel.stock.bean.StockAccordingGoodsQueryResultListBean;
import com.cndsteel.stock.bean.StockBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

public class StockAccordingGoodsQueryResultListActivity extends FrameActivity implements PullToRefreshBase.OnRefreshListener<ExpandableListView>,ExpandableListView.OnChildClickListener {
	
	private String mQueryParamStoreDateFrom;
	private String mQueryParamStoreDateTo;
	private String mQueryParamPNameId;
	private String mQueryParamMaterialId;
	private String mQueryParamWareId;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshExpandableListView listV_accordingGoodsQueryResultList;
	private StockAccordingGoodsQueryResultListAdapter mStockAccordingGoodsQueryResultListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.stock_warehouse_detail);
		appendFrameworkCenter(R.layout.activity_stock_according_goods_query_result);
		
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
		mQueryParamPNameId = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_PNAME_ID);
		mQueryParamMaterialId = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_MATERIAL_ID);
		mQueryParamWareId = _intent.getStringExtra(QueryParams.QUERY_PARAM_STOCK_WARE_ID);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initViews() {
		listV_accordingGoodsQueryResultList = (PullToRefreshExpandableListView)findViewById(R.id.listV_accordingGoodsQueryResultList);
		listV_accordingGoodsQueryResultList.setMode(Mode.BOTH);
		listV_accordingGoodsQueryResultList.getRefreshableView().setGroupIndicator(null);
		listV_accordingGoodsQueryResultList.setOnRefreshListener(this);
		listV_accordingGoodsQueryResultList.getRefreshableView().setOnChildClickListener(this);
		
		mStockAccordingGoodsQueryResultListAdapter = new StockAccordingGoodsQueryResultListAdapter(getApplicationContext());
		mStockAccordingGoodsQueryResultListAdapter.initDatas(new ArrayList<StockAccordingGoodsQueryResultBean>());
		
		listV_accordingGoodsQueryResultList.getRefreshableView().setAdapter(mStockAccordingGoodsQueryResultListAdapter);
	}
	
	private void loadDatas(int msgWhat) {
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_DATE_FROM, mQueryParamStoreDateFrom);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_DATE_TO, mQueryParamStoreDateTo);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_PNAME_ID, mQueryParamPNameId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_MATERIAL_ID, mQueryParamMaterialId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STOCK_WARE_ID, mQueryParamWareId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		
		mWebServiceThread = new WebServiceThread(new StockAccordingGoodsQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	@Override
	public boolean onChildClick(ExpandableListView parent, View view,int groupPosition, int childPosition, long id) {
		
		StockAccordingGoodsQueryResultBean _stockAccordingGoodsQueryResultBean = (StockAccordingGoodsQueryResultBean)mStockAccordingGoodsQueryResultListAdapter.getGroup(groupPosition);
		StockBean _stockBean = (StockBean)mStockAccordingGoodsQueryResultListAdapter.getChild(groupPosition, childPosition);
		
		Intent _intent = new Intent(this,StockAccordingGoodsQueryResultDetailActivity.class);
		_intent.putExtra("stockWareName", _stockBean.wareName);
		_intent.putExtra("pName", _stockAccordingGoodsQueryResultBean.pName);
		_intent.putExtra("material", _stockAccordingGoodsQueryResultBean.material);
		_intent.putExtra("weight", _stockBean.weight);
		_intent.putExtra("piece", _stockBean.piece);
		_intent.putExtra("spec", _stockAccordingGoodsQueryResultBean.spec);
		_intent.putExtra("amount", _stockBean.amount);
		
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_DATE_FROM, mQueryParamStoreDateFrom);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_DATE_TO, mQueryParamStoreDateTo);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_WARE_ID, _stockBean.wareId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_PNAME_ID, _stockAccordingGoodsQueryResultBean.pNameId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_MATERIAL_ID, _stockAccordingGoodsQueryResultBean.materialId);
		_intent.putExtra(QueryParams.QUERY_PARAM_STOCK_SPEC, _stockAccordingGoodsQueryResultBean.spec);
		
		startActivity(_intent);
		
		return false;
	}
	
	@Override
	public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
		//下拉更新
		if(listV_accordingGoodsQueryResultList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_accordingGoodsQueryResultList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<StockAccordingGoodsQueryResultListActivity> mWeakReference;
		
		public TheHandler(StockAccordingGoodsQueryResultListActivity activity){
			mWeakReference = new WeakReference<StockAccordingGoodsQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final StockAccordingGoodsQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int msgWhat = msg.what;
				switch (msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					StockAccordingGoodsQueryResultListBean _stockAccordingGoodsQueryResultListBean = (StockAccordingGoodsQueryResultListBean)msg.obj;
					if(_stockAccordingGoodsQueryResultListBean.code > 0){
						_theActivity.mStockAccordingGoodsQueryResultListAdapter.refreshDatas(_stockAccordingGoodsQueryResultListBean.stockAccordingGoodsQueryResultBeans);
						
						//展开所有分组
						for(int i = 0; i < _stockAccordingGoodsQueryResultListBean.stockAccordingGoodsQueryResultBeans.size(); i ++){
							_theActivity.listV_accordingGoodsQueryResultList.getRefreshableView().expandGroup(i);
						}
						
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
					_theActivity.listV_accordingGoodsQueryResultList.onRefreshComplete();
					
					StockAccordingGoodsQueryResultListBean _stockAccordingGoodsQueryResultListBean = (StockAccordingGoodsQueryResultListBean)msg.obj;
					if(_stockAccordingGoodsQueryResultListBean.code > 0){
						_theActivity.mStockAccordingGoodsQueryResultListAdapter.refreshDatas(_stockAccordingGoodsQueryResultListBean.stockAccordingGoodsQueryResultBeans);
						
						//展开所有分组
						for(int i = 0; i < _stockAccordingGoodsQueryResultListBean.stockAccordingGoodsQueryResultBeans.size(); i ++){
							_theActivity.listV_accordingGoodsQueryResultList.getRefreshableView().expandGroup(i);
						}
						
					}
			
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_accordingGoodsQueryResultList.onRefreshComplete();
					
					StockAccordingGoodsQueryResultListBean _stockAccordingGoodsQueryResultListBean = (StockAccordingGoodsQueryResultListBean)msg.obj;
					if(_stockAccordingGoodsQueryResultListBean.code > 0){
						_theActivity.mStockAccordingGoodsQueryResultListAdapter.appendDatas(_stockAccordingGoodsQueryResultListBean.stockAccordingGoodsQueryResultBeans);
						
						//展开所有分组
						for(int i = 0; i < _stockAccordingGoodsQueryResultListBean.stockAccordingGoodsQueryResultBeans.size(); i ++){
							_theActivity.listV_accordingGoodsQueryResultList.getRefreshableView().expandGroup(i);
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
