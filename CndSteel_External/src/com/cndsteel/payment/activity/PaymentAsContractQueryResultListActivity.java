package com.cndsteel.payment.activity;

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
import com.cndsteel.payment.adapter.PaymentAsContractQueryResultListAdapter;
import com.cndsteel.payment.bean.PaymentAsContractQueryResultListBean;
import com.cndsteel.payment.bean.PaymentBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class PaymentAsContractQueryResultListActivity extends FrameActivity implements OnItemClickListener,OnRefreshListener<ListView>{
	
	private String mQueryParamConYear;
	private String mQueryParamConMonth;
	private String mQueryParamConCode;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshListView listV_paymentContractQueryResultList;
	private PaymentAsContractQueryResultListAdapter mPaymentAsContractQueryResultListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.contract_Sum_terms);
		appendFrameworkCenter(R.layout.activity_payment_contract_query_result_list);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void initVariables() {
		Intent _intent = getIntent();
		
		mQueryParamConYear = _intent.getStringExtra(QueryParams.QUERY_PARAM_CON_YEAR);
		mQueryParamConMonth = _intent.getStringExtra(QueryParams.QUERY_PARAM_CON_MONTH);
		mQueryParamConCode = _intent.getStringExtra(QueryParams.QUERY_PARAM_CON_CODE);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initViews() {
		listV_paymentContractQueryResultList = (PullToRefreshListView)findViewById(R.id.listV_paymentContractQueryResultList);
		listV_paymentContractQueryResultList.setMode(Mode.BOTH);
		listV_paymentContractQueryResultList.setOnRefreshListener(this);
		listV_paymentContractQueryResultList.getRefreshableView().setOnItemClickListener(this);
		
		mPaymentAsContractQueryResultListAdapter = new PaymentAsContractQueryResultListAdapter(getApplicationContext());
		mPaymentAsContractQueryResultListAdapter.initDatas(new ArrayList<PaymentBean>());
		listV_paymentContractQueryResultList.getRefreshableView().setAdapter(mPaymentAsContractQueryResultListAdapter);
	}
	
	private void loadDatas(int msgWhat){
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_YEAR, mQueryParamConYear);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_MONTH, mQueryParamConMonth);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_CODE, mQueryParamConCode);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new PaymentAsContractQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<PaymentAsContractQueryResultListActivity> mWeakReference;
		
		public TheHandler(PaymentAsContractQueryResultListActivity activity){
			mWeakReference = new WeakReference<PaymentAsContractQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final PaymentAsContractQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					PaymentAsContractQueryResultListBean _paymentAsContractQueryResultListBean = (PaymentAsContractQueryResultListBean)msg.obj;
					if(_paymentAsContractQueryResultListBean.code > 0){
						_theActivity.mPaymentAsContractQueryResultListAdapter.refreshDatas(_paymentAsContractQueryResultListBean.paymentBeans);
					}else {
						_theActivity.showAlertDialog(R.string.contract_Sum_terms, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.contract_Sum_terms)), new DialogInterface.OnClickListener() {
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
					_theActivity.listV_paymentContractQueryResultList.onRefreshComplete();
					
					PaymentAsContractQueryResultListBean _paymentAsContractQueryResultListBean = (PaymentAsContractQueryResultListBean)msg.obj;
					if(_paymentAsContractQueryResultListBean.code > 0){
						_theActivity.mPaymentAsContractQueryResultListAdapter.refreshDatas(_paymentAsContractQueryResultListBean.paymentBeans);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_paymentContractQueryResultList.onRefreshComplete();
					
					PaymentAsContractQueryResultListBean _paymentAsContractQueryResultListBean = (PaymentAsContractQueryResultListBean)msg.obj;
					if(_paymentAsContractQueryResultListBean.code > 0){
						_theActivity.mPaymentAsContractQueryResultListAdapter.appendDatas(_paymentAsContractQueryResultListBean.paymentBeans);
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
		//下拉更新
		if(listV_paymentContractQueryResultList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_paymentContractQueryResultList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}
	
	/** ListView的item的事件处理 **/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		PaymentBean _paymentBean = (PaymentBean)parent.getAdapter().getItem(position);
		
		Intent _intent = new Intent(this,PaymentAsContractQueryResultDetailActivity.class);
		_intent.putExtra("conCode", _paymentBean.conCode);
		
		_intent.putExtra(QueryParams.QUERY_PARAM_CON_ID, _paymentBean.conId);
		
		startActivity(_intent);
		
	}
}
