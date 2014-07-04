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
import com.cndsteel.contract.beans.ContractQueryResultListBean;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.shipment.adapter.ShipmentAsInvoiceNumQueryResultListAdapter;
import com.cndsteel.shipment.bean.ShipmentAsInvoiceNumQueryResultListBean;
import com.cndsteel.shipment.bean.ShipmentBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ShipmentAsInvoiceNumQueryResultListActivity extends FrameActivity implements OnItemClickListener,OnRefreshListener<ListView>{
	
	private String mQueryParamShipDateFrom;
	private String mQueryParamShipDateTo;
	private String mQueryParamShipNO;
	private String mQueryParamWareId;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshListView listV_shipmentAsInvoiceNumQueryResultList;
	private ShipmentAsInvoiceNumQueryResultListAdapter mShipmentAsInvoiceNumQueryResultListAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendFrameworkCenter(R.layout.activity_shipment_as_invoice_num_query_result_list);
		setTopBarTitle(R.string.appModule_shipment);
		
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
		mQueryParamShipNO = _intent.getStringExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_NO);
		mQueryParamWareId = _intent.getStringExtra(QueryParams.QUERY_PARAM_WARE_ID);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initViews() {
		listV_shipmentAsInvoiceNumQueryResultList = (PullToRefreshListView)findViewById(R.id.listV_shipmentAsInvoiceNumQueryResultList);
		listV_shipmentAsInvoiceNumQueryResultList.setMode(Mode.BOTH);
		listV_shipmentAsInvoiceNumQueryResultList.setOnRefreshListener(this);
		listV_shipmentAsInvoiceNumQueryResultList.getRefreshableView().setOnItemClickListener(this);
		
		mShipmentAsInvoiceNumQueryResultListAdapter = new ShipmentAsInvoiceNumQueryResultListAdapter(getApplicationContext());
		mShipmentAsInvoiceNumQueryResultListAdapter.initDatas(new ArrayList<ShipmentBean>());
		
		listV_shipmentAsInvoiceNumQueryResultList.setAdapter(mShipmentAsInvoiceNumQueryResultListAdapter);
	}
	
	private void loadDatas(int msgWhat){
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_DATE_FROM, mQueryParamShipDateFrom);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_DATE_TO, mQueryParamShipDateTo);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_NO, mQueryParamShipNO);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_WARE_ID, mQueryParamWareId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new ShipmentAsInvoiceNumQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<ShipmentAsInvoiceNumQueryResultListActivity> mWeakReference;
		
		public TheHandler(ShipmentAsInvoiceNumQueryResultListActivity activity){
			mWeakReference = new WeakReference<ShipmentAsInvoiceNumQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final ShipmentAsInvoiceNumQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int msgWhat = msg.what;
				switch (msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					ShipmentAsInvoiceNumQueryResultListBean _shipmentAsInvoiceNumQueryResultListBean = (ShipmentAsInvoiceNumQueryResultListBean)msg.obj;
					if(_shipmentAsInvoiceNumQueryResultListBean.code > 0){
						_theActivity.mShipmentAsInvoiceNumQueryResultListAdapter.refreshDatas(_shipmentAsInvoiceNumQueryResultListBean.shipmentBeans);
					}else {
						_theActivity.showAlertDialog(R.string.appModule_contract, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.appModule_contract)), new DialogInterface.OnClickListener() {
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
					_theActivity.listV_shipmentAsInvoiceNumQueryResultList.onRefreshComplete();
					
					ShipmentAsInvoiceNumQueryResultListBean _shipmentAsInvoiceNumQueryResultListBean = (ShipmentAsInvoiceNumQueryResultListBean)msg.obj;
					if(_shipmentAsInvoiceNumQueryResultListBean.code > 0){
						_theActivity.mShipmentAsInvoiceNumQueryResultListAdapter.refreshDatas(_shipmentAsInvoiceNumQueryResultListBean.shipmentBeans);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_shipmentAsInvoiceNumQueryResultList.onRefreshComplete();
					
					ShipmentAsInvoiceNumQueryResultListBean _shipmentAsInvoiceNumQueryResultListBean = (ShipmentAsInvoiceNumQueryResultListBean)msg.obj;
					if(_shipmentAsInvoiceNumQueryResultListBean.code > 0){
						_theActivity.mShipmentAsInvoiceNumQueryResultListAdapter.appendDatas(_shipmentAsInvoiceNumQueryResultListBean.shipmentBeans);
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
		
		Intent _intent = new Intent(this,ShipmentAsInvoiceNumQueryResultDetailActivity.class);
		_intent.putExtra("shipNO", _shipmentBean.shipNO);
		_intent.putExtra("storeName", _shipmentBean.storeName);
		_intent.putExtra("shipDate", _shipmentBean.shipDate);
		
		_intent.putExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_ID, _shipmentBean.shipId);
		
		startActivity(_intent);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		//下拉更新
		if(listV_shipmentAsInvoiceNumQueryResultList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_shipmentAsInvoiceNumQueryResultList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}
}
