package com.cndsteel.bill.activity;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.bean.BillContractTrackingQueryResultDetailBean;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.settings.beans.SettingsBean;

public class BillContractTrackingQueryResultDetailActivity extends FrameActivity {
	
	private String mQueryParamConId;
	private String mQueryParamSessionId;
	
	private TextView billContract_num;
	
	private ScrollView scrollV_billContractTrackingDetail; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.TheContractOfMakeOutAnInvoiceDetail);
		appendFrameworkCenter(R.layout.bill_contract_tracking_detail);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas();
	}
	
	private void initVariables(){
		mQueryParamConId = getIntent().getStringExtra(QueryParams.QUERY_PARAM_CON_ID);
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
	}

	private void initViews() {
		((TextView) findViewById(R.id.txtV_contractTrackingDetailConCode)).setText(getIntent().getStringExtra(QueryParams.QUERY_PARAM_CON_CODE));
		
		scrollV_billContractTrackingDetail = (ScrollView)findViewById(R.id.scrollV_billContractTrackingDetail);
	}
	
	private void loadDatas(){
		showLoadingProgressDialog();
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_ID, mQueryParamConId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new BillContractTrackingQueryResultDetailBean(), _webServiceRequestParams, mTheHandler, Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
		mWebServiceThread.start();
	}
	
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<BillContractTrackingQueryResultDetailActivity> mWeakReference;
		
		public TheHandler(BillContractTrackingQueryResultDetailActivity activity){
			mWeakReference = new WeakReference<BillContractTrackingQueryResultDetailActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final BillContractTrackingQueryResultDetailActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					BillContractTrackingQueryResultDetailBean _billContractTrackingQueryResultDetailBean = (BillContractTrackingQueryResultDetailBean)msg.obj;
					if(_billContractTrackingQueryResultDetailBean.code > 0){
						((TextView)_theActivity.findViewById(R.id.txtV_wzDelivcom)).setText(_billContractTrackingQueryResultDetailBean.wzDelivcom);
						((TextView)_theActivity.findViewById(R.id.txtV_wzDelivNO)).setText(_billContractTrackingQueryResultDetailBean.wzDelivNO);
						((TextView)_theActivity.findViewById(R.id.txtV_wzDelivDate)).setText(_billContractTrackingQueryResultDetailBean.wzDelivDate);
						((TextView)_theActivity.findViewById(R.id.txtV_wzDelivReceiver)).setText(_billContractTrackingQueryResultDetailBean.wzDelivReceiver);
						((TextView)_theActivity.findViewById(R.id.txtV_wzDelivSigner)).setText(_billContractTrackingQueryResultDetailBean.wzDelivSigner);
						((TextView)_theActivity.findViewById(R.id.txtV_wzDelivSignDate)).setText(_billContractTrackingQueryResultDetailBean.wzDelivSignDate);
						((TextView)_theActivity.findViewById(R.id.txtV_wzRemark)).setText(_billContractTrackingQueryResultDetailBean.wzRemark);
						((TextView)_theActivity.findViewById(R.id.txtV_cuDelivcom)).setText(_billContractTrackingQueryResultDetailBean.cuDelivcom);
						((TextView)_theActivity.findViewById(R.id.txtV_cuDelivNO)).setText(_billContractTrackingQueryResultDetailBean.cuDelivNO);
						((TextView)_theActivity.findViewById(R.id.txtV_cuDelivDate)).setText(_billContractTrackingQueryResultDetailBean.cuDelivDate);
						((TextView)_theActivity.findViewById(R.id.txtV_cuDelivReceiver)).setText(_billContractTrackingQueryResultDetailBean.cuDelivReceiver);
						((TextView)_theActivity.findViewById(R.id.txtV_cuDelivSigner)).setText(_billContractTrackingQueryResultDetailBean.cuDelivSigner);
						((TextView)_theActivity.findViewById(R.id.txtV_cuDelivSignDate)).setText(_billContractTrackingQueryResultDetailBean.cuDelivSignDate);
						((TextView)_theActivity.findViewById(R.id.txtV_cuRemark)).setText(_billContractTrackingQueryResultDetailBean.cuRemark);
						
						_theActivity.scrollV_billContractTrackingDetail.setVisibility(View.VISIBLE);
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
