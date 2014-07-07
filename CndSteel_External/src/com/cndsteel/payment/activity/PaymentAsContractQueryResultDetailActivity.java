package com.cndsteel.payment.activity;

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
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.payment.adapter.PaymentAsContractQueryResultDetailAdapter;
import com.cndsteel.payment.bean.PaymentAsContractQueryResultDetailBean;
import com.cndsteel.payment.bean.PaymentBean;
import com.cndsteel.settings.beans.SettingsBean;

public class PaymentAsContractQueryResultDetailActivity extends FrameActivity {

	private String mQueryParamConId;
	private String mQueryParamSessionId;
	
	private ListView listV_paymentContractQueryDetailList;
	private PaymentAsContractQueryResultDetailAdapter mPaymentAsContractQueryResultDetailAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.contract_Sum_terms_detail);
		appendFrameworkCenter(R.layout.payment_detail_as_contract_activity);

		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas();
	}

	private void initVariables() {
		mQueryParamConId = getIntent().getStringExtra(QueryParams.QUERY_PARAM_CON_ID);
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
	}
	
	private void initViews(){
		((TextView)findViewById(R.id.contract_terms_Num)).setText(getIntent().getStringExtra("conCode"));
		
		mPaymentAsContractQueryResultDetailAdapter = new PaymentAsContractQueryResultDetailAdapter(getApplicationContext());
		mPaymentAsContractQueryResultDetailAdapter.initDatas(new ArrayList<PaymentBean>());
		
		listV_paymentContractQueryDetailList = (ListView)findViewById(R.id.listV_paymentContractQueryDetailList);
		listV_paymentContractQueryDetailList.setAdapter(mPaymentAsContractQueryResultDetailAdapter);
	}

	private void loadDatas(){
		showLoadingProgressDialog();
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_ID, mQueryParamConId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new PaymentAsContractQueryResultDetailBean(), _webServiceRequestParams, mTheHandler, Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<PaymentAsContractQueryResultDetailActivity> mWeakReference;
		
		public TheHandler(PaymentAsContractQueryResultDetailActivity activity){
			mWeakReference = new WeakReference<PaymentAsContractQueryResultDetailActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final PaymentAsContractQueryResultDetailActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS:{
					_theActivity.dismissProgressDialog();
					
					PaymentAsContractQueryResultDetailBean _paymentAsContractQueryResultDetailBean = (PaymentAsContractQueryResultDetailBean)msg.obj;
					if(_paymentAsContractQueryResultDetailBean.code > 0){
						_theActivity.mPaymentAsContractQueryResultDetailAdapter.refreshDatas(_paymentAsContractQueryResultDetailBean.paymentBeans);
					}else {
						_theActivity.showAlertDialog(R.string.contract_Sum_terms_detail, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.contract_Sum_terms_detail)), new DialogInterface.OnClickListener() {
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
	
	
}
