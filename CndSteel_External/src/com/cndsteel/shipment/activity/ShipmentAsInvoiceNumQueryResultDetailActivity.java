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
import com.cndsteel.shipment.adapter.ShipmentAsInvoiceNumQueryResultDetailAdapter;
import com.cndsteel.shipment.bean.ShipmentAsInvoiceNumQueryResultDetailBean;
import com.cndsteel.shipment.bean.ShipmentAsInvoiceNumQueryResultDetailListBean;
import com.cndsteel.shipment.bean.ShipmentBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

public class ShipmentAsInvoiceNumQueryResultDetailActivity extends FrameActivity implements OnChildClickListener,OnRefreshListener<ExpandableListView> {
	
	private String mQueryParamShipId;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshExpandableListView listV_shipmentDetailListview;
	private ShipmentAsInvoiceNumQueryResultDetailAdapter mShipmentAsInvoiceNumQueryResultDetailAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.shipmentDetail);
		appendFrameworkCenter(R.layout.shipment_detail_as_invoice);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void initVariables() {
		mQueryParamShipId = getIntent().getStringExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_ID);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
	}

	private void initViews() {
		Intent _intent = getIntent();
		
		((TextView)findViewById(R.id.ShipmentDetailAsInvoice_InvoiceNum)).setText(_intent.getStringExtra("shipNO"));
		((TextView)findViewById(R.id.ShipmentDetailAsInvoice_Warehouse)).setText(_intent.getStringExtra("storeName"));
		((TextView)findViewById(R.id.ShipmentDetailAsInvoice_InvoiceDate)).setText(_intent.getStringExtra("shipDate"));
		
		
		listV_shipmentDetailListview = (PullToRefreshExpandableListView)findViewById(R.id.listV_shipmentDetailListview);
		listV_shipmentDetailListview.setMode(Mode.BOTH);
		listV_shipmentDetailListview.getRefreshableView().setGroupIndicator(null);
		listV_shipmentDetailListview.getRefreshableView().setOnChildClickListener(this);
		listV_shipmentDetailListview.setOnRefreshListener(this);
		
		mShipmentAsInvoiceNumQueryResultDetailAdapter = new ShipmentAsInvoiceNumQueryResultDetailAdapter(getApplicationContext());
		mShipmentAsInvoiceNumQueryResultDetailAdapter.initDatas(new ArrayList<ShipmentAsInvoiceNumQueryResultDetailListBean>());
		
		listV_shipmentDetailListview.getRefreshableView().setAdapter(mShipmentAsInvoiceNumQueryResultDetailAdapter);
	}

	private void loadDatas(int msgWhat){
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_ID, mQueryParamShipId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new ShipmentAsInvoiceNumQueryResultDetailBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<ShipmentAsInvoiceNumQueryResultDetailActivity> mWeakReference;
		
		public TheHandler(ShipmentAsInvoiceNumQueryResultDetailActivity activity){
			mWeakReference = new WeakReference<ShipmentAsInvoiceNumQueryResultDetailActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final ShipmentAsInvoiceNumQueryResultDetailActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int msgWhat = msg.what;
				switch (msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					ShipmentAsInvoiceNumQueryResultDetailBean _shipmentAsInvoiceNumQueryResultDetailBean = (ShipmentAsInvoiceNumQueryResultDetailBean)msg.obj;
					if(_shipmentAsInvoiceNumQueryResultDetailBean.code > 0){
						_theActivity.mShipmentAsInvoiceNumQueryResultDetailAdapter.refreshDatas(_shipmentAsInvoiceNumQueryResultDetailBean.shipmentAsInvoiceNumQueryResultDetailListBeans);
					}
					
					//展开所有分组
					for(int i = 0; i < _shipmentAsInvoiceNumQueryResultDetailBean.shipmentAsInvoiceNumQueryResultDetailListBeans.size(); i ++){
						_theActivity.listV_shipmentDetailListview.getRefreshableView().expandGroup(i);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_shipmentDetailListview.onRefreshComplete();
					
					ShipmentAsInvoiceNumQueryResultDetailBean _shipmentAsInvoiceNumQueryResultDetailBean = (ShipmentAsInvoiceNumQueryResultDetailBean)msg.obj;
					if(_shipmentAsInvoiceNumQueryResultDetailBean.code > 0){
						_theActivity.mShipmentAsInvoiceNumQueryResultDetailAdapter.refreshDatas(_shipmentAsInvoiceNumQueryResultDetailBean.shipmentAsInvoiceNumQueryResultDetailListBeans);
					}
					
					//展开所有分组
					for(int i = 0; i < _shipmentAsInvoiceNumQueryResultDetailBean.shipmentAsInvoiceNumQueryResultDetailListBeans.size(); i ++){
						_theActivity.listV_shipmentDetailListview.getRefreshableView().expandGroup(i);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_shipmentDetailListview.onRefreshComplete();
					
					ShipmentAsInvoiceNumQueryResultDetailBean _shipmentAsInvoiceNumQueryResultDetailBean = (ShipmentAsInvoiceNumQueryResultDetailBean)msg.obj;
					if(_shipmentAsInvoiceNumQueryResultDetailBean.code > 0){
						_theActivity.mShipmentAsInvoiceNumQueryResultDetailAdapter.appendDatas(_shipmentAsInvoiceNumQueryResultDetailBean.shipmentAsInvoiceNumQueryResultDetailListBeans);
					}
					
					//展开所有分组
					for(int i = 0; i < _shipmentAsInvoiceNumQueryResultDetailBean.shipmentAsInvoiceNumQueryResultDetailListBeans.size(); i ++){
						_theActivity.listV_shipmentDetailListview.getRefreshableView().expandGroup(i);
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
		if(listV_shipmentDetailListview.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_shipmentDetailListview.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View view,int groupPosition, int childPosition, long id) {
		ShipmentAsInvoiceNumQueryResultDetailListBean _shipmentAsInvoiceNumQueryResultDetailListBean = (ShipmentAsInvoiceNumQueryResultDetailListBean)mShipmentAsInvoiceNumQueryResultDetailAdapter.getGroup(groupPosition);
		ShipmentBean _shipmentBean = (ShipmentBean)mShipmentAsInvoiceNumQueryResultDetailAdapter.getChild(groupPosition, childPosition);
		
		Intent _intent = new Intent(this,ShipmentDetailQueryResultListActivity.class);
		_intent.putExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_ID, mQueryParamShipId);
		_intent.putExtra(QueryParams.QUERY_PARAM_CON_ID, _shipmentAsInvoiceNumQueryResultDetailListBean.conId);
		_intent.putExtra(QueryParams.QUERY_PARAM_PNAME_ID, _shipmentBean.pnameId);
		_intent.putExtra(QueryParams.QUERY_PARAM_MATERIAL_ID, _shipmentBean.materialId);
		_intent.putExtra(QueryParams.QUERY_PARAM_SPEC, _shipmentBean.spec);
		
		startActivity(_intent);
		
		return false;
	}

}
