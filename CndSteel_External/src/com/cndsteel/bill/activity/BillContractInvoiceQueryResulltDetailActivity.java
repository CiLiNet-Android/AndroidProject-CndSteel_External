package com.cndsteel.bill.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.adapter.BillContractInvoiceQueryResulltDetailAdapter;
import com.cndsteel.bill.bean.BillBean;
import com.cndsteel.bill.bean.BillContractInvoiceQueryResulltDetailBean;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.settings.beans.SettingsBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class BillContractInvoiceQueryResulltDetailActivity extends FrameActivity implements OnRefreshListener<ListView> {
	
	private PullToRefreshListView listV_billContractInvoiceQueryResulDetailList;
	
	private BillContractInvoiceQueryResulltDetailAdapter billContractInvoiceQueryResulltDetailAdapter;
	
	/**
	 * 请求参数
	 */
	private String mContractInvoiceQueryParamConId;
	private int mContractInvoiceQueryParamPageIndex;
	private int mContractInvoiceQueryParamPageSize;
	private String mContractInvoiceQueryParamSessionId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.TheContractOfMakeOutAnInvoiceDetail);
		appendFrameworkCenter(R.layout.bill_contract_invoice_detail);
		
		init();
		
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
		
	}
	private void loadDatas(int msgWhat) {
		
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceQueryParams = new LinkedHashMap<String, String>();
		_webServiceQueryParams.put(QueryParams.QUERY_PARAM_CON_ID, mContractInvoiceQueryParamConId);
		_webServiceQueryParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mContractInvoiceQueryParamPageIndex));
		_webServiceQueryParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mContractInvoiceQueryParamPageSize));
		_webServiceQueryParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mContractInvoiceQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new BillContractInvoiceQueryResulltDetailBean(), _webServiceQueryParams, new MyHandler(this), msgWhat);
		mWebServiceThread.start();
	}
	
	private static class MyHandler extends Handler {
		
		private WeakReference<BillContractInvoiceQueryResulltDetailActivity> mWeakReference;
		
		public MyHandler(BillContractInvoiceQueryResulltDetailActivity activity) {
			mWeakReference = new WeakReference<BillContractInvoiceQueryResulltDetailActivity>(activity);
		}
		
		@Override
		public void handleMessage(Message msg) {
			final BillContractInvoiceQueryResulltDetailActivity _activity = mWeakReference.get();
			
			if(null != _activity){
				
				switch (msg.what) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS:{
					BillContractInvoiceQueryResulltDetailBean _billContractInvoiceQueryResulltDetailBean = (BillContractInvoiceQueryResulltDetailBean) msg.obj;
					if(_billContractInvoiceQueryResulltDetailBean.code > 0){
						_activity.dismissProgressDialog();
						_activity.billContractInvoiceQueryResulltDetailAdapter.refreshDatas(_billContractInvoiceQueryResulltDetailBean.mBillBeansList);
					}else {
						_activity.showAlertDialog(R.string.TheContractOfMakeOutAnInvoiceDetail, _activity.getString(R.string.DialogMsgNoDatas, _activity.getString(R.string.TheContractOfMakeOutAnInvoiceDetail)), new DialogInterface.OnClickListener() {
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
					_activity.listV_billContractInvoiceQueryResulDetailList.onRefreshComplete();
					
					BillContractInvoiceQueryResulltDetailBean _billContractInvoiceQueryResulltDetailBean = (BillContractInvoiceQueryResulltDetailBean) msg.obj;
					if(_billContractInvoiceQueryResulltDetailBean.code > 0){
						_activity.billContractInvoiceQueryResulltDetailAdapter.refreshDatas(_billContractInvoiceQueryResulltDetailBean.mBillBeansList);
					}		
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS:{
					_activity.listV_billContractInvoiceQueryResulDetailList.onRefreshComplete();
					
					BillContractInvoiceQueryResulltDetailBean _billContractInvoiceQueryResulltDetailBean = (BillContractInvoiceQueryResulltDetailBean) msg.obj;
					if(_billContractInvoiceQueryResulltDetailBean.code > 0){
						_activity.billContractInvoiceQueryResulltDetailAdapter.appendDatas(_billContractInvoiceQueryResulltDetailBean.mBillBeansList);
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
		initView();		
	}
	
	private void initVariables() {
		
		mContractInvoiceQueryParamConId = getIntent().getStringExtra(QueryParams.QUERY_PARAM_CON_ID);
		mContractInvoiceQueryParamPageIndex = 1;
		mContractInvoiceQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mContractInvoiceQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
		
	}
	private void initView() {
		
		((TextView) findViewById(R.id.txtV_contractInvoiceDetailConCode)).setText(getIntent().getStringExtra(QueryParams.QUERY_PARAM_CON_CODE));
		((TextView) findViewById(R.id.txtV_contractInvoiceDetailInvedAmt)).setText(getIntent().getStringExtra(QueryParams.QUERY_PARAM_BILL_INVEDAMT));
		
		listV_billContractInvoiceQueryResulDetailList = (PullToRefreshListView) findViewById(R.id.listV_billContractInvoiceQueryResulDetailList);
		listV_billContractInvoiceQueryResulDetailList.setMode(Mode.BOTH);
		listV_billContractInvoiceQueryResulDetailList.setOnRefreshListener(this);
		
		billContractInvoiceQueryResulltDetailAdapter = new BillContractInvoiceQueryResulltDetailAdapter(getApplicationContext());
		billContractInvoiceQueryResulltDetailAdapter.initDatas(new ArrayList<BillBean>());
		
		listV_billContractInvoiceQueryResulDetailList.setAdapter(billContractInvoiceQueryResulltDetailAdapter);
		
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		if(listV_billContractInvoiceQueryResulDetailList.isHeaderShown()){
			
			mContractInvoiceQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
			
		}else if(listV_billContractInvoiceQueryResulDetailList.isFooterShown()){
			
			mContractInvoiceQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
			
		}
	}

}
