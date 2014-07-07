package com.cndsteel.shipment.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.settings.beans.SettingsBean;
import com.cndsteel.shipment.adapter.ShipmentAsContractNumQueryResultListAdapter;
import com.cndsteel.shipment.bean.ShipmentAsContractNumQueryResultListBean;
import com.cndsteel.shipment.bean.ShipmentBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ShipmentAsContractNumQueryResultListActivity extends FrameActivity implements OnItemClickListener,OnRefreshListener<ListView>{
	
	private String mQueryParamShipDateFrom;
	private String mQueryParamShipDateTo;
	private String mQueryParamConCode;
	private String mQueryParamPNameId;
	private String mQueryParamMaterialId;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshListView listV_shipmentAsContractNumQueryResultList;
	private ShipmentAsContractNumQueryResultListAdapter mShipmentAsContractNumQueryResultListAdapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.shipmentDetail);
		appendFrameworkCenter(R.layout.activity_shipment_as_contract_num_query_result_list);
		
		init();
	}


	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}


	private void initVariables() {
		Intent _intent = getIntent();
		
		mQueryParamShipDateFrom = _intent.getStringExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_DATE_FROM);
		mQueryParamShipDateTo = _intent.getStringExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_DATE_TO);
		mQueryParamConCode = _intent.getStringExtra(QueryParams.QUERY_PARAM_CON_CODE);
		mQueryParamPNameId = _intent.getStringExtra(QueryParams.QUERY_PARAM_PNAME_ID);
		mQueryParamMaterialId = _intent.getStringExtra(QueryParams.QUERY_PARAM_MATERIAL_ID);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
	}


	private void initViews() {
		listV_shipmentAsContractNumQueryResultList = (PullToRefreshListView)findViewById(R.id.listV_shipmentAsContractNumQueryResultList);
		listV_shipmentAsContractNumQueryResultList.setMode(Mode.BOTH);
		listV_shipmentAsContractNumQueryResultList.setOnRefreshListener(this);
		listV_shipmentAsContractNumQueryResultList.getRefreshableView().setOnItemClickListener(this);
		
		mShipmentAsContractNumQueryResultListAdapter = new ShipmentAsContractNumQueryResultListAdapter(getApplicationContext());
		mShipmentAsContractNumQueryResultListAdapter.initDatas(new ArrayList<ShipmentBean>());
		
		listV_shipmentAsContractNumQueryResultList.setAdapter(mShipmentAsContractNumQueryResultListAdapter);	
	}
	
	private void loadDatas(int msgWhat){
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_DATE_FROM, mQueryParamShipDateFrom);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_DATE_TO, mQueryParamShipDateTo);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_CODE, mQueryParamConCode);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PNAME_ID, mQueryParamPNameId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_MATERIAL_ID, mQueryParamMaterialId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new ShipmentAsContractNumQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<ShipmentAsContractNumQueryResultListActivity> mWeakReference;
		
		public TheHandler(ShipmentAsContractNumQueryResultListActivity activity){
			mWeakReference = new WeakReference<ShipmentAsContractNumQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final ShipmentAsContractNumQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int msgWhat = msg.what;
				switch (msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					ShipmentAsContractNumQueryResultListBean _shipmentAsContractNumQueryResultListBean = (ShipmentAsContractNumQueryResultListBean)msg.obj;
					if(_shipmentAsContractNumQueryResultListBean.code > 0){
						_theActivity.mShipmentAsContractNumQueryResultListAdapter.refreshDatas(_shipmentAsContractNumQueryResultListBean.shipmentBeans);
					}else {
						_theActivity.showAlertDialog(R.string.shipmentDetail, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.shipmentDetail)), new DialogInterface.OnClickListener() {
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
					_theActivity.listV_shipmentAsContractNumQueryResultList.onRefreshComplete();
					
					ShipmentAsContractNumQueryResultListBean _shipmentAsContractNumQueryResultListBean = (ShipmentAsContractNumQueryResultListBean)msg.obj;
					if(_shipmentAsContractNumQueryResultListBean.code > 0){
						_theActivity.mShipmentAsContractNumQueryResultListAdapter.refreshDatas(_shipmentAsContractNumQueryResultListBean.shipmentBeans);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_shipmentAsContractNumQueryResultList.onRefreshComplete();
					
					ShipmentAsContractNumQueryResultListBean _shipmentAsContractNumQueryResultListBean = (ShipmentAsContractNumQueryResultListBean)msg.obj;
					if(_shipmentAsContractNumQueryResultListBean.code > 0){
						_theActivity.mShipmentAsContractNumQueryResultListAdapter.appendDatas(_shipmentAsContractNumQueryResultListBean.shipmentBeans);
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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ShipmentBean _shipmentBean = (ShipmentBean)parent.getAdapter().getItem(position);
		
		Intent _intent = new Intent(this,ShipmentAsContractNumQueryResultDetailActivity.class);
		_intent.putExtra(QueryParams.QUERY_PARAM_CON_ID, _shipmentBean.conId);
		_intent.putExtra(QueryParams.QUERY_PARAM_CON_CODE, _shipmentBean.conCode);
		_intent.putExtra(QueryParams.QUERY_PARAM_WEIGHT, _shipmentBean.weight);
		_intent.putExtra(QueryParams.QUERY_PARAM_PIECE, _shipmentBean.piece);
		
		_intent.putExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_ID, _shipmentBean.shipId);
		
		startActivity(_intent);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		//下拉更新
		if(listV_shipmentAsContractNumQueryResultList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_shipmentAsContractNumQueryResultList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}

}
