package com.cndsteel.shipment.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.settings.beans.SettingsBean;
import com.cndsteel.shipment.adapter.ShipmentAsContractNumQueryResultDetailAdapter;
import com.cndsteel.shipment.bean.ShipmentAsContractNumQueryResultDetailBean;
import com.cndsteel.shipment.bean.ShipmentAsContractNumQueryResultDetailListBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

public class ShipmentAsContractNumQueryResultDetailActivity extends FrameActivity implements OnRefreshListener<ExpandableListView>,OnChildClickListener {
	
	private String mQueryParamConId;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshExpandableListView listV_shipmentDetailAsContractList;
	private ShipmentAsContractNumQueryResultDetailAdapter mShipmentAsContractNumQueryResultDetailAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.shipmentDetail);
		appendFrameworkCenter(R.layout.shipment_detail_as_contract);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void initVariables(){
		mQueryParamConId = getIntent().getStringExtra(QueryParams.QUERY_PARAM_CON_ID);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
	}
	
	private void initViews() {
		Intent _intent = getIntent();
		
		((TextView)findViewById(R.id.ShipmentDetailAsContract_ContractNum)).setText(_intent.getStringExtra(QueryParams.QUERY_PARAM_CON_CODE));
		((TextView)findViewById(R.id.ShipmentDetailAsContract_Tonnage)).setText(_intent.getStringExtra(QueryParams.QUERY_PARAM_WEIGHT));
		((TextView)findViewById(R.id.ShipmentDetailAsContract_numberOfPackages)).setText(_intent.getStringExtra(QueryParams.QUERY_PARAM_PIECE));
		
		listV_shipmentDetailAsContractList = (PullToRefreshExpandableListView)findViewById(R.id.listV_shipmentDetailAsContractList);
		listV_shipmentDetailAsContractList.setMode(Mode.BOTH);
		listV_shipmentDetailAsContractList.getRefreshableView().setGroupIndicator(null);
		listV_shipmentDetailAsContractList.setOnRefreshListener(this);
		listV_shipmentDetailAsContractList.getRefreshableView().setOnChildClickListener(this);
		
		mShipmentAsContractNumQueryResultDetailAdapter = new ShipmentAsContractNumQueryResultDetailAdapter(getApplicationContext());
		mShipmentAsContractNumQueryResultDetailAdapter.initDatas(new ArrayList<ShipmentAsContractNumQueryResultDetailListBean>());
		
		listV_shipmentDetailAsContractList.getRefreshableView().setAdapter(mShipmentAsContractNumQueryResultDetailAdapter);
	}
	
	private void loadDatas(int msgWhat){
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_ID, mQueryParamConId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new ShipmentAsContractNumQueryResultDetailBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}

	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<ShipmentAsContractNumQueryResultDetailActivity> mWeakReference;
		
		public TheHandler(ShipmentAsContractNumQueryResultDetailActivity activity){
			mWeakReference = new WeakReference<ShipmentAsContractNumQueryResultDetailActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final ShipmentAsContractNumQueryResultDetailActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int msgWhat = msg.what;
				switch (msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					ShipmentAsContractNumQueryResultDetailBean _shipmentAsContractNumQueryResultDetailBean = (ShipmentAsContractNumQueryResultDetailBean)msg.obj;
					if(_shipmentAsContractNumQueryResultDetailBean.code > 0){
						_theActivity.mShipmentAsContractNumQueryResultDetailAdapter.refreshDatas(_shipmentAsContractNumQueryResultDetailBean.shipmentAsContractNumQueryResultDetailListBeans);
					}
					
					//展开所有分组
					for(int i = 0; i < _shipmentAsContractNumQueryResultDetailBean.shipmentAsContractNumQueryResultDetailListBeans.size(); i ++){
						_theActivity.listV_shipmentDetailAsContractList.getRefreshableView().expandGroup(i);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_shipmentDetailAsContractList.onRefreshComplete();
					
					ShipmentAsContractNumQueryResultDetailBean _shipmentAsContractNumQueryResultDetailBean = (ShipmentAsContractNumQueryResultDetailBean)msg.obj;
					if(_shipmentAsContractNumQueryResultDetailBean.code > 0){
						_theActivity.mShipmentAsContractNumQueryResultDetailAdapter.refreshDatas(_shipmentAsContractNumQueryResultDetailBean.shipmentAsContractNumQueryResultDetailListBeans);
					}
					
					//展开所有分组
					for(int i = 0; i < _shipmentAsContractNumQueryResultDetailBean.shipmentAsContractNumQueryResultDetailListBeans.size(); i ++){
						_theActivity.listV_shipmentDetailAsContractList.getRefreshableView().expandGroup(i);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_shipmentDetailAsContractList.onRefreshComplete();
					
					ShipmentAsContractNumQueryResultDetailBean _shipmentAsContractNumQueryResultDetailBean = (ShipmentAsContractNumQueryResultDetailBean)msg.obj;
					if(_shipmentAsContractNumQueryResultDetailBean.code > 0){
						_theActivity.mShipmentAsContractNumQueryResultDetailAdapter.appendDatas(_shipmentAsContractNumQueryResultDetailBean.shipmentAsContractNumQueryResultDetailListBeans);
					}
					
					//展开所有分组
					for(int i = 0; i < _shipmentAsContractNumQueryResultDetailBean.shipmentAsContractNumQueryResultDetailListBeans.size(); i ++){
						_theActivity.listV_shipmentDetailAsContractList.getRefreshableView().expandGroup(i);
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
		if(listV_shipmentDetailAsContractList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_shipmentDetailAsContractList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View view,int groupPosition, int childPosition, long id) {
//		ShipmentBean _shipmentBean = (ShipmentBean)mShipmentAsContractNumQueryResultDetailAdapter.getChild(groupPosition, childPosition);
//		
//		Intent _intent = new Intent(this,ShipmentDetailQueryResultListActivity.class);
//		
//		_intent.putExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_ID, "");
//		_intent.putExtra(QueryParams.QUERY_PARAM_CON_ID, mQueryParamConId);
//		_intent.putExtra(QueryParams.QUERY_PARAM_PNAME_ID, _shipmentBean.pnameId);
//		_intent.putExtra(QueryParams.QUERY_PARAM_MATERIAL_ID, _shipmentBean.materialId);
//		_intent.putExtra(QueryParams.QUERY_PARAM_SPEC, _shipmentBean.spec);
//		
//		startActivity(_intent);
		
		return false;
	}
}
