package com.cndsteel.payment.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.payment.adapter.PaymentAsInformetionQueryResultDetailAdapter;
import com.cndsteel.payment.bean.PaymentAsInformetionQueryResultDetailBean;
import com.cndsteel.payment.bean.PaymentBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class PaymentAsInformetionQueryResultDetailActivity extends FrameActivity implements OnRefreshListener<ListView>{
	
	private String mQueryParamPayId;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshListView listV_PaymentDetailAsInfoList;
	private PaymentAsInformetionQueryResultDetailAdapter mPaymentAsInformetionQueryResultDetailAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.paymentDetail);
		appendFrameworkCenter(R.layout.payment_detail_as_information);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void initVariables() {
		Intent _intent = getIntent();
		
		mQueryParamPayId = _intent.getStringExtra(QueryParams.QUERY_PARAM_PAYMENT_PAY_ID);
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initViews() {
		Intent _intent = getIntent();
		
		((TextView)findViewById(R.id.txtV_paymentInfoQueryDetailPaymentDate)).setText(_intent.getStringExtra("payDate"));
		((TextView)findViewById(R.id.txtV_paymentInfoQueryDetailPaymentMode)).setText(_intent.getStringExtra("payMode"));
		((TextView)findViewById(R.id.txtV_paymentInfoQueryDetailAmount)).setText(_intent.getStringExtra("amount"));
		((TextView)findViewById(R.id.txtV_paymentInfoQueryDetailBillNO)).setText(_intent.getStringExtra("billNO"));
		
		listV_PaymentDetailAsInfoList = (PullToRefreshListView)findViewById(R.id.listV_PaymentDetailAsInfoList);
		listV_PaymentDetailAsInfoList.setMode(Mode.BOTH);
		listV_PaymentDetailAsInfoList.setOnRefreshListener(this);
		
		mPaymentAsInformetionQueryResultDetailAdapter = new PaymentAsInformetionQueryResultDetailAdapter(getApplicationContext());
		mPaymentAsInformetionQueryResultDetailAdapter.initDatas(new ArrayList<PaymentBean>());
		listV_PaymentDetailAsInfoList.getRefreshableView().setAdapter(mPaymentAsInformetionQueryResultDetailAdapter);
	}
	
	private void loadDatas(int msgWhat){
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAYMENT_PAY_ID, mQueryParamPayId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new PaymentAsInformetionQueryResultDetailBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		mWebServiceThread.start();
	}

	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<PaymentAsInformetionQueryResultDetailActivity> mWeakReference;
		
		public TheHandler(PaymentAsInformetionQueryResultDetailActivity activity){
			mWeakReference = new WeakReference<PaymentAsInformetionQueryResultDetailActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final PaymentAsInformetionQueryResultDetailActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS:{
					_theActivity.dismissProgressDialog();
					
					PaymentAsInformetionQueryResultDetailBean _paymentAsInformetionQueryResultDetailBean = (PaymentAsInformetionQueryResultDetailBean)msg.obj;
					if(_paymentAsInformetionQueryResultDetailBean.code > 0){
						_theActivity.mPaymentAsInformetionQueryResultDetailAdapter.refreshDatas(_paymentAsInformetionQueryResultDetailBean.paymentBeans);
					}
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS:{
					_theActivity.listV_PaymentDetailAsInfoList.onRefreshComplete();
					
					PaymentAsInformetionQueryResultDetailBean _paymentAsInformetionQueryResultDetailBean = (PaymentAsInformetionQueryResultDetailBean)msg.obj;
					if(_paymentAsInformetionQueryResultDetailBean.code > 0){
						_theActivity.mPaymentAsInformetionQueryResultDetailAdapter.refreshDatas(_paymentAsInformetionQueryResultDetailBean.paymentBeans);
					}
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS:{
					_theActivity.listV_PaymentDetailAsInfoList.onRefreshComplete();
					
					PaymentAsInformetionQueryResultDetailBean _paymentAsInformetionQueryResultDetailBean = (PaymentAsInformetionQueryResultDetailBean)msg.obj;
					if(_paymentAsInformetionQueryResultDetailBean.code > 0){
						_theActivity.mPaymentAsInformetionQueryResultDetailAdapter.appendDatas(_paymentAsInformetionQueryResultDetailBean.paymentBeans);
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
		if(listV_PaymentDetailAsInfoList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_PaymentDetailAsInfoList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}
}
