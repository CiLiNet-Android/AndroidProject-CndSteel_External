package com.cndsteel.bill.activity;

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
import com.cndsteel.bill.adapter.BillContractTrackingQueryResultListAdapter;
import com.cndsteel.bill.bean.BillBean;
import com.cndsteel.bill.bean.BillContractTrackingQueryResultListBean;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class BillContractTrackingQueryResultListActivity extends FrameActivity implements OnItemClickListener,OnRefreshListener<ListView> {
	
	private String mQueryParamConCode;
	private String mQueryParamCustomerStatus;
	private String mQueryParamWzStatus;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshListView listV_billContractTrackingQueryResultList;
	private BillContractTrackingQueryResultListAdapter mBillContractTrackingQueryResultListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.appModule_bill);
		appendFrameworkCenter(R.layout.activity_bill_contract_tracking_query_result_list);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void initVariables() {
		Intent _intent = getIntent();
		
		mQueryParamConCode = _intent.getStringExtra(QueryParams.QUERY_PARAM_CON_CODE);
		mQueryParamCustomerStatus = _intent.getStringExtra(QueryParams.QUERY_PARAM_BILL_CUSTOMER_STATUS);
		mQueryParamWzStatus = _intent.getStringExtra(QueryParams.QUERY_PARAM_BILL_WZ_STATUS);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initViews() {
		listV_billContractTrackingQueryResultList = (PullToRefreshListView)findViewById(R.id.listV_billContractTrackingQueryResultList);
		listV_billContractTrackingQueryResultList.setMode(Mode.BOTH);
		listV_billContractTrackingQueryResultList.setOnRefreshListener(this);
		listV_billContractTrackingQueryResultList.getRefreshableView().setOnItemClickListener(this);
		
		mBillContractTrackingQueryResultListAdapter = new BillContractTrackingQueryResultListAdapter(getApplicationContext());
		mBillContractTrackingQueryResultListAdapter.initDatas(new ArrayList<BillBean>());
		
		listV_billContractTrackingQueryResultList.getRefreshableView().setAdapter(mBillContractTrackingQueryResultListAdapter);
	}

	private void loadDatas(int msgWhat){
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_CODE, mQueryParamConCode);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_BILL_CUSTOMER_STATUS, mQueryParamCustomerStatus);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_BILL_WZ_STATUS, mQueryParamWzStatus);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new BillContractTrackingQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<BillContractTrackingQueryResultListActivity> mWeakReference;
		
		public TheHandler(BillContractTrackingQueryResultListActivity activity){
			mWeakReference = new WeakReference<BillContractTrackingQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final BillContractTrackingQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					BillContractTrackingQueryResultListBean _billContractTrackingQueryResultListBean = (BillContractTrackingQueryResultListBean)msg.obj;
					if(_billContractTrackingQueryResultListBean.code > 0){
						_theActivity.mBillContractTrackingQueryResultListAdapter.refreshDatas(_billContractTrackingQueryResultListBean.billBeans);
					}else {
						_theActivity.showAlertDialog(R.string.appModule_bill, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.appModule_bill)), new DialogInterface.OnClickListener() {
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
					_theActivity.listV_billContractTrackingQueryResultList.onRefreshComplete();
					
					BillContractTrackingQueryResultListBean _billContractTrackingQueryResultListBean = (BillContractTrackingQueryResultListBean)msg.obj;
					if(_billContractTrackingQueryResultListBean.code > 0){
						_theActivity.mBillContractTrackingQueryResultListAdapter.refreshDatas(_billContractTrackingQueryResultListBean.billBeans);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_billContractTrackingQueryResultList.onRefreshComplete();
					
					BillContractTrackingQueryResultListBean _billContractTrackingQueryResultListBean = (BillContractTrackingQueryResultListBean)msg.obj;
					if(_billContractTrackingQueryResultListBean.code > 0){
						_theActivity.mBillContractTrackingQueryResultListAdapter.appendDatas(_billContractTrackingQueryResultListBean.billBeans);
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
		startActivity(BillContractTrackingQueryResultDetailActivity.class);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		//下拉更新
		if(listV_billContractTrackingQueryResultList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_billContractTrackingQueryResultList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}

}
