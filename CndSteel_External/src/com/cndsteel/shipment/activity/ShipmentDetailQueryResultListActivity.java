package com.cndsteel.shipment.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.shipment.adapter.ShipmentDetailQueryResultListAdapter;
import com.cndsteel.shipment.bean.ShipmentBean;
import com.cndsteel.shipment.bean.ShipmentDetailQueryResultListBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 发货单明细
 * @author zhxl
 *
 */
public class ShipmentDetailQueryResultListActivity extends FrameActivity implements OnRefreshListener<ListView>{
	
	private String mQueryParamShipId;
	private String mQueryParamConId;
	private String mQueryParamPNameId;
	private String mQueryParamMaterialId;
	private String mQueryParamSpec;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshListView listV_shipmentQueryResultDetailList;
	private ShipmentDetailQueryResultListAdapter mShipmentDetailQueryResultListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setTopBarTitle(R.string.shipment_invoiceNum_details);
		super.appendFrameworkCenter(R.layout.activity_shipment_detail_query_result_list);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void initVariables(){
		Intent _intent = getIntent();
		
		mQueryParamShipId = _intent.getStringExtra(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_ID);
		mQueryParamConId = _intent.getStringExtra(QueryParams.QUERY_PARAM_CON_ID);
		mQueryParamPNameId = _intent.getStringExtra(QueryParams.QUERY_PARAM_PNAME_ID);
		mQueryParamMaterialId = _intent.getStringExtra(QueryParams.QUERY_PARAM_MATERIAL_ID);
		mQueryParamSpec = _intent.getStringExtra(QueryParams.QUERY_PARAM_SPEC);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}
	
	private void initViews(){
		listV_shipmentQueryResultDetailList = (PullToRefreshListView)findViewById(R.id.listV_shipmentQueryResultDetailList);
		listV_shipmentQueryResultDetailList.setMode(Mode.BOTH);
		listV_shipmentQueryResultDetailList.setOnRefreshListener(this);
		
		mShipmentDetailQueryResultListAdapter = new ShipmentDetailQueryResultListAdapter(getApplicationContext());
		mShipmentDetailQueryResultListAdapter.initDatas(new ArrayList<ShipmentBean>());
		
		listV_shipmentQueryResultDetailList.setAdapter(mShipmentDetailQueryResultListAdapter);
	}
	
	private void loadDatas(int msgWhat) {
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();

		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_ID, mQueryParamShipId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_ID, mQueryParamConId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PNAME_ID, mQueryParamPNameId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_MATERIAL_ID, mQueryParamMaterialId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SPEC, mQueryParamSpec);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new ShipmentDetailQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<ShipmentDetailQueryResultListActivity> mWeakReference;
		
		public TheHandler(ShipmentDetailQueryResultListActivity activity){
			mWeakReference = new WeakReference<ShipmentDetailQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final ShipmentDetailQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch(_msgWhat){
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					ShipmentDetailQueryResultListBean _shipmentDetailQueryResultListBean = (ShipmentDetailQueryResultListBean)msg.obj;
					if(_shipmentDetailQueryResultListBean.code > 0){
						_theActivity.mShipmentDetailQueryResultListAdapter.refreshDatas(_shipmentDetailQueryResultListBean.shipmentBeans);
					}else {
						_theActivity.showAlertDialog(R.string.shipment_invoiceNum_details, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.shipment_invoiceNum_details)), new DialogInterface.OnClickListener() {
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

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}
}
