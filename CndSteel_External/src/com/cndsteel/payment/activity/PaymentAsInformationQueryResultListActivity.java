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
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.payment.adapter.PaymentAsInformationQueryResultListAdapter;
import com.cndsteel.payment.bean.PaymentAsInformationQueryResultListBean;
import com.cndsteel.payment.bean.PaymentBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class PaymentAsInformationQueryResultListActivity extends FrameActivity implements OnItemClickListener,OnRefreshListener<ListView> {
	
	/**
	 * 付款信息请求参数
	 */
	private String mQueryParamPayDateFrom;
	private String mQueryParamPayDateTo;
	private String mQueryParamPayMode;
	private String mQueryParamBillNO;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshListView listV_paymentInfoQueryResultList;
	private PaymentAsInformationQueryResultListAdapter mPaymentAsInformationQueryResultListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.payment_information);
		appendFrameworkCenter(R.layout.activity_payment_info_query_result_list);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void initVariables() {
		Intent _intent = getIntent();
		
		mQueryParamPayDateFrom = _intent.getStringExtra(QueryParams.QUERY_PARAM_PAYMENT_PAY_DATE_FROM);
		mQueryParamPayDateTo = _intent.getStringExtra(QueryParams.QUERY_PARAM_PAYMENT_PAY_DATE_TO);
		mQueryParamPayMode = _intent.getStringExtra(QueryParams.QUERY_PARAM_PAYMENT_PAY_MODE);
		mQueryParamBillNO = _intent.getStringExtra(QueryParams.QUERY_PARAM_PAYMENT_BILL_NO);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initViews() {
		listV_paymentInfoQueryResultList = (PullToRefreshListView)findViewById(R.id.listV_paymentInfoQueryResultList);
		listV_paymentInfoQueryResultList.setMode(Mode.BOTH);
		listV_paymentInfoQueryResultList.setOnRefreshListener(this);
		listV_paymentInfoQueryResultList.getRefreshableView().setOnItemClickListener(this);
		
		mPaymentAsInformationQueryResultListAdapter = new PaymentAsInformationQueryResultListAdapter(getApplicationContext());
		mPaymentAsInformationQueryResultListAdapter.initDatas(new ArrayList<PaymentBean>());
		
		listV_paymentInfoQueryResultList.getRefreshableView().setAdapter(mPaymentAsInformationQueryResultListAdapter);
	}
	
	private void loadDatas(int msgWhat){
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAYMENT_PAY_DATE_FROM, mQueryParamPayDateFrom);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAYMENT_PAY_DATE_TO, mQueryParamPayDateTo);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAYMENT_PAY_MODE, mQueryParamPayMode);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAYMENT_BILL_NO, mQueryParamBillNO);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new PaymentAsInformationQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<PaymentAsInformationQueryResultListActivity> mWeakReference;
		
		public TheHandler(PaymentAsInformationQueryResultListActivity activity){
			mWeakReference = new WeakReference<PaymentAsInformationQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final PaymentAsInformationQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					PaymentAsInformationQueryResultListBean _paymentAsInformationQueryResultListBean = (PaymentAsInformationQueryResultListBean)msg.obj;
					if(_paymentAsInformationQueryResultListBean.code > 0){
						_theActivity.mPaymentAsInformationQueryResultListAdapter.refreshDatas(_paymentAsInformationQueryResultListBean.paymentBeans);
					}else {
						_theActivity.showAlertDialog(R.string.payment_information, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.payment_information)), new DialogInterface.OnClickListener() {
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
					_theActivity.listV_paymentInfoQueryResultList.onRefreshComplete();
					
					PaymentAsInformationQueryResultListBean _paymentAsInformationQueryResultListBean = (PaymentAsInformationQueryResultListBean)msg.obj;
					if(_paymentAsInformationQueryResultListBean.code > 0){
						_theActivity.mPaymentAsInformationQueryResultListAdapter.refreshDatas(_paymentAsInformationQueryResultListBean.paymentBeans);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_paymentInfoQueryResultList.onRefreshComplete();
					
					PaymentAsInformationQueryResultListBean _paymentAsInformationQueryResultListBean = (PaymentAsInformationQueryResultListBean)msg.obj;
					if(_paymentAsInformationQueryResultListBean.code > 0){
						_theActivity.mPaymentAsInformationQueryResultListAdapter.appendDatas(_paymentAsInformationQueryResultListBean.paymentBeans);
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
		PaymentBean _paymentBean = (PaymentBean)parent.getAdapter().getItem(position);
		
		Intent _intent = new Intent(this,PaymentAsInformetionQueryResultDetailActivity.class);
		_intent.putExtra("payDate", _paymentBean.payDate);
		_intent.putExtra("payMode", _paymentBean.payMode);
		_intent.putExtra("amount", _paymentBean.amount);
		_intent.putExtra("billNO", _paymentBean.billNO);
		
		_intent.putExtra(QueryParams.QUERY_PARAM_PAYMENT_PAY_ID, _paymentBean.payId);
		
		startActivity(_intent);
	}
	
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		//下拉更新
		if(listV_paymentInfoQueryResultList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_paymentInfoQueryResultList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}

}
