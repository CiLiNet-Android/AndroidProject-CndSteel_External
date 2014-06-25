package com.cndsteel.contract.activities;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.contract.beans.ContractBean;
import com.cndsteel.contract.beans.ContractQueryResultDetailBean;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.utils.DateUtils;
import com.cndsteel.framework.webService.WebServiceThread;

public class ContractQueryResultDetailActivity extends FrameActivity {
	
	private WebServiceThread mWebServiceThread;
	
	private String mQueryParamId;
	private String mQueryParamSessionId;
	
	private LinearLayout lyot_contractDetails;
	
	private TextView contract_detail_number;  //合同号
	private TextView contract_detail_signDate;  //签约日期
	private TextView contract_detail_tonnage;  //合同吨数
	private TextView contract_detail_amountOfMoney;  //合同金额
	private TextView contract_detail_mustGetPledgeDate;  //应收保证日期
	private TextView contract_detail_mustGetPledgeMoney;  //应收保证金金额
	private TextView contract_detail_realityGetPledgeDate;  //实收保证日期
	private TextView contract_detail_maturityDate;  //到期日

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendFrameworkCenter(R.layout.activity_contract_query_result_detail);
		
		init();
	}

	private void init() {
		initVariables();
		initView();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void initVariables() {
		mQueryParamId = getIntent().getStringExtra(QueryParams.QUERY_PARAM_ID);
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initView() {
		setTopBarTitle(R.string.contract_detail);
		
		lyot_contractDetails = (LinearLayout)findViewById(R.id.lyot_contractDetails);
		
		contract_detail_number = (TextView) findViewById(R.id.contract_detail_number);
		contract_detail_signDate = (TextView) findViewById(R.id.contract_detail_signDate);
		contract_detail_tonnage = (TextView) findViewById(R.id.contract_detail_tonnage);
		contract_detail_amountOfMoney = (TextView) findViewById(R.id.contract_detail_amountOfMoney);
		contract_detail_mustGetPledgeDate = (TextView) findViewById(R.id.contract_detail_mustGetPledgeDate);
		contract_detail_mustGetPledgeMoney = (TextView) findViewById(R.id.contract_detail_mustGetPledgeMoney);
		contract_detail_realityGetPledgeDate = (TextView) findViewById(R.id.contract_detail_realityGetPledgeDate);
		contract_detail_maturityDate = (TextView)findViewById(R.id.contract_detail_maturityDate);
	}

	private void loadDatas(int msgWhat){
		showLoadingProgressDialog();
		
		LinkedHashMap<String, String> _webServiceRequestParams = createWebServiceRequestParams();
		
		mWebServiceThread = new WebServiceThread(new ContractQueryResultDetailBean(), _webServiceRequestParams, mHandler, msgWhat);
		mWebServiceThread.start();
	}
	
	private LinkedHashMap<String, String> createWebServiceRequestParams(){
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>(2);
		
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_ID, mQueryParamId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		return _webServiceRequestParams;
	}
	
	/**
	 * ActivityHandler
	 * @author zhxl
	 *
	 */
	private ContractQueryResultDetailActivityHandler mHandler = new ContractQueryResultDetailActivityHandler(this);
	
	private static class ContractQueryResultDetailActivityHandler extends Handler {
		private WeakReference<ContractQueryResultDetailActivity> mWeakReference;
		
		public ContractQueryResultDetailActivityHandler(ContractQueryResultDetailActivity activity){
			mWeakReference = new WeakReference<ContractQueryResultDetailActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final ContractQueryResultDetailActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS:{
					_theActivity.dismissProgressDialog();
					
					ContractQueryResultDetailBean _contractQueryResultDetailBean = (ContractQueryResultDetailBean)msg.obj;
					
					_theActivity.contract_detail_number.setText(_contractQueryResultDetailBean.conCode);
					
					Date _signDate = DateUtils.getDate(_contractQueryResultDetailBean.signDate, "yyyy-MM-dd HH:mm:ss");
					_theActivity.contract_detail_signDate.setText(DateUtils.getFormatDateTime(_signDate, "yyyy/MM/dd"));
					
					_theActivity.contract_detail_tonnage.setText(_contractQueryResultDetailBean.weight);
					_theActivity.contract_detail_amountOfMoney.setText(_contractQueryResultDetailBean.amount);
					
					Date _marginDate = DateUtils.getDate(_contractQueryResultDetailBean.marginDate, "yyyy-MM-dd HH:mm:ss");
					_theActivity.contract_detail_mustGetPledgeDate.setText(DateUtils.getFormatDateTime(_marginDate, "yyyy/MM/dd"));
					
					_theActivity.contract_detail_mustGetPledgeMoney.setText(_contractQueryResultDetailBean.marginAmt);
					
			
					//Date marginDated = DateUtils.getDate(_contractQueryResultDetailBean.marginDated, "yyyy-MM-dd HH:mm:ss");
					//_theActivity.contract_detail_realityGetPledgeDate.setText(DateUtils.getFormatDateTime(marginDated, "yyyy/MM/dd"));
					_theActivity.contract_detail_realityGetPledgeDate.setText(_contractQueryResultDetailBean.marginDated);
					
					Date _dueDate = DateUtils.getDate(_contractQueryResultDetailBean.dueDate, "yyyy-MM-dd");
					_theActivity.contract_detail_maturityDate.setText(DateUtils.getFormatDateTime(_dueDate, "yyyy/MM/dd"));
					
					ArrayList<ContractBean> _contractBeans = _contractQueryResultDetailBean.contractBeans;
					if(null != _contractBeans && _contractBeans.size() > 0){
						LinearLayout _contractDetailsView = (LinearLayout)_theActivity.getLayoutInflater().inflate(R.layout.activity_contract_query_result_detail_item, null);
					
						for(ContractBean _contractBean : _contractBeans){
							((TextView)_contractDetailsView.findViewById(R.id.contract_detail_goodsName)).setText(_contractBean.pname);
							((TextView)_contractDetailsView.findViewById(R.id.contract_detail_material)).setText(_contractBean.material);
							((TextView)_contractDetailsView.findViewById(R.id.txtV_contractDetailSpec)).setText(_contractBean.spec);
							((TextView)_contractDetailsView.findViewById(R.id.contract_detail_reserve)).setText(_contractBean.weight);
							((TextView)_contractDetailsView.findViewById(R.id.txtV_steelWorks)).setText(_contractBean.steel);
						}
						
						LinearLayout.LayoutParams _layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
					
						_theActivity.lyot_contractDetails.addView(_contractDetailsView, _layoutParams);
					}
					
					_theActivity.lyot_contractDetails.setVisibility(View.VISIBLE);
					
					break;
				}
				default:
					break;
				}
			}
		}
	}
		

}
