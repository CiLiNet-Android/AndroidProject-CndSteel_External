package com.cndsteel.bill.activity;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.LinkedList;

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
import com.cndsteel.bill.adapter.BillContractInvoiceQueryResultListAdapter;
import com.cndsteel.bill.bean.BillBean;
import com.cndsteel.bill.bean.BillContractInvoiceQueryResultListBean;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class BillContractInvoiceQueryResultListActivity extends FrameActivity implements OnItemClickListener,OnRefreshListener<ListView> {
	
	private PullToRefreshListView listV_billContractInvoiceQueryResultList;
	
	private BillContractInvoiceQueryResultListAdapter _billContractInvoiceQueryResultListAdapter;
	
	/**
	 * 请求参数
	 */
	private String mContractInvoiceQueryParamConYearMontFrom;
	private String mContractInvoiceQueryParamconYearMontTo;
	private String mContractInvoiceQueryParamConCode;
	private int mContractInvoiceQueryParamPageindex;
	private int mContractInvoiceQueryParamPagesize;
	private String mContractinvoiceQueryParamSessionId;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.appModule_bill);
		
		appendFrameworkCenter(R.layout.activity_bill_invoice_query_result_list);
		
		init();
		
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
		
	}

	private void loadDatas(int msgWhat) {
		
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_BILL_CON_YEAR_MONT_FROM, mContractInvoiceQueryParamConYearMontFrom);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_BILL_CON_YEAR_MONT_TO,mContractInvoiceQueryParamconYearMontTo);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_CODE, mContractInvoiceQueryParamConCode);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mContractInvoiceQueryParamPageindex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mContractInvoiceQueryParamPagesize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mContractinvoiceQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new BillContractInvoiceQueryResultListBean(), _webServiceRequestParams, mMyHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private MyHandler mMyHandler = new MyHandler(this);
	
	private static class MyHandler extends Handler {
		
		private WeakReference<BillContractInvoiceQueryResultListActivity> mWeakReference;
		
		public MyHandler(BillContractInvoiceQueryResultListActivity activity) {
			mWeakReference = new WeakReference<BillContractInvoiceQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final BillContractInvoiceQueryResultListActivity _activity = mWeakReference.get();
			
			if(null != _activity){
				
				switch (msg.what) {
				
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS:{
					
					_activity.dismissProgressDialog();
					
					BillContractInvoiceQueryResultListBean _billContractInvoiceQueryResultListBean = (BillContractInvoiceQueryResultListBean) msg.obj;	
					
					if(_billContractInvoiceQueryResultListBean.code > 0){
						_activity._billContractInvoiceQueryResultListAdapter.refreshDatas(_billContractInvoiceQueryResultListBean.mBillBeansList);
					}else {
						_activity.showAlertDialog(R.string.appModule_bill, _activity.getString(R.string.DialogMsgNoDatas, _activity.getString(R.string.appModule_bill)), new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if(which == DialogInterface.BUTTON_NEUTRAL){
									_activity.finish();
								}
							}
						});
					}
					break;
				}
				
				case Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS:{
					_activity.listV_billContractInvoiceQueryResultList.onRefreshComplete();
					
					BillContractInvoiceQueryResultListBean _billContractInvoiceQueryResultListBean = (BillContractInvoiceQueryResultListBean) msg.obj;
					if(_billContractInvoiceQueryResultListBean.code > 0 ){
						_activity._billContractInvoiceQueryResultListAdapter.refreshDatas(_billContractInvoiceQueryResultListBean.mBillBeansList);
					}
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS:{
					_activity.listV_billContractInvoiceQueryResultList.onRefreshComplete();
					
					BillContractInvoiceQueryResultListBean _billContractInvoiceQueryResultListBean = (BillContractInvoiceQueryResultListBean) msg.obj;
					if(_billContractInvoiceQueryResultListBean.code > 0 ){
						_activity._billContractInvoiceQueryResultListAdapter.appendDatas(_billContractInvoiceQueryResultListBean.mBillBeansList);
					}
					break;
				}
				default:
					break;
				}			
			}	
		}			
	}

	private void init() {
		initVariables();
		initeView();		
	}

	private void initVariables() {
		Intent _intent = getIntent();
		
		mContractInvoiceQueryParamConYearMontFrom = _intent.getStringExtra(QueryParams.QUERY_PARAM_BILL_CON_YEAR_MONT_FROM);
		mContractInvoiceQueryParamconYearMontTo = _intent.getStringExtra(QueryParams.QUERY_PARAM_BILL_CON_YEAR_MONT_TO);
		mContractInvoiceQueryParamConCode = _intent.getStringExtra(QueryParams.QUERY_PARAM_CON_CODE);
		
		mContractInvoiceQueryParamPageindex = 1;
		mContractInvoiceQueryParamPagesize = Constants.DEFAULT_PAGE_SIZE;
		mContractinvoiceQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
		
	}

	private void initeView() {
		
		listV_billContractInvoiceQueryResultList = (PullToRefreshListView) findViewById(R.id.listV_billContractInvoiceQueryResultList);
		listV_billContractInvoiceQueryResultList.setMode(Mode.BOTH);
		listV_billContractInvoiceQueryResultList.getRefreshableView().setOnItemClickListener(this);
		listV_billContractInvoiceQueryResultList.setOnRefreshListener(this);
		
		_billContractInvoiceQueryResultListAdapter = new BillContractInvoiceQueryResultListAdapter(getApplicationContext());
		_billContractInvoiceQueryResultListAdapter.initDatas(new LinkedList<BillBean>());
		
		listV_billContractInvoiceQueryResultList.setAdapter(_billContractInvoiceQueryResultListAdapter);
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Intent _intent = new Intent(this,BillContractInvoiceQueryResulltDetailActivity.class);
		
		BillBean _billBean = (BillBean) parent.getAdapter().getItem(position);
		_intent.putExtra("conId", _billBean.conId);
		
		startActivity(_intent);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		
		if(listV_billContractInvoiceQueryResultList.isHeaderShown()){
			
			mContractInvoiceQueryParamPageindex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
			
		}else if(listV_billContractInvoiceQueryResultList.isFooterShown()){
			
			mContractInvoiceQueryParamPageindex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
			
		}
		
		
		
	}

}
