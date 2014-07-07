package com.cndsteel.plan.activities;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.handler.AbsActivityHandler;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.plan.beans.PlanBean;
import com.cndsteel.plan.beans.PlanQueryResultDetailBean;
import com.cndsteel.settings.beans.SettingsBean;

public class PlanQueryResultDetailActivity extends FrameActivity {
	
	/** 请求参数 **/
	private String mQueryParamBookingId;
	private String mQueryParamSessionId;
	
	private LinearLayout lyot_planDetails;
	
	private TextView plan_detail_yearMonth;  //订货计划年月
	private TextView plan_detail_must_get_pledge_money;  //应收保证金金额
	private TextView plan_detail_must_get_pledge_date;  //应收保证金日期
	private TextView plan_detail_status;  //状态
	private TextView plan_detail_tonnage;  //计划吨数
	private TextView plan_detail_reality_tonnage;  //实际吨数
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_plan_query_result_detail);

		init();

	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}

	private void initVariables() {
		mQueryParamBookingId = getIntent().getStringExtra(QueryParams.QUERY_PARAM_PLAN_BOOKING_ID);
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
	}

	private void initViews() {
		setTopBarTitle(R.string.topBarTitle_plan_datail);
		
//		imgBtn_topRight = (ImageButton) findViewById(R.id.imgBtn_topRight);
//		imgBtn_topRight.setVisibility(View.VISIBLE);
//		imgBtn_topRight.setBackgroundResource(R.drawable.add);
		
		lyot_planDetails = (LinearLayout)findViewById(R.id.lyot_planDetails);
		
		plan_detail_yearMonth = (TextView) findViewById(R.id.plan_detail_yearMonth);
		plan_detail_must_get_pledge_money = (TextView) findViewById(R.id.plan_detail_must_get_pledge_money);
		plan_detail_must_get_pledge_date = (TextView) findViewById(R.id.plan_detail_must_get_pledge_date);
		plan_detail_status = (TextView) findViewById(R.id.plan_detail_status);
		plan_detail_tonnage = (TextView) findViewById(R.id.plan_detail_tonnage);
		plan_detail_reality_tonnage = (TextView) findViewById(R.id.plan_detail_reality_tonnage);
	}
	
	private LinkedHashMap<String,String> createWebServiceQueryParams(){
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PLAN_BOOKING_ID,mQueryParamBookingId);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID,mQueryParamSessionId);
		
		return _webServiceRequestParams;
	}
	
	private AbsActivityHandler<PlanQueryResultDetailActivity> mActivityHandler = new AbsActivityHandler<PlanQueryResultDetailActivity>(this) {
		@Override
		protected void handleMessage(PlanQueryResultDetailActivity theActivity,
				Message msg) {
			int _msgWhat = msg.what;
			switch (_msgWhat) {
			case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
				theActivity.dismissProgressDialog();
				
				PlanQueryResultDetailBean _planPlanQueryResultDetailBean = (PlanQueryResultDetailBean)msg.obj;
				plan_detail_yearMonth.setText(_planPlanQueryResultDetailBean.schDate);
				plan_detail_must_get_pledge_money.setText(_planPlanQueryResultDetailBean.receivedMargin);
				plan_detail_must_get_pledge_date.setText(_planPlanQueryResultDetailBean.receivedMarginDate);
				plan_detail_status.setText(_planPlanQueryResultDetailBean.status);
				plan_detail_tonnage.setText(_planPlanQueryResultDetailBean.weight);
				plan_detail_reality_tonnage.setText(_planPlanQueryResultDetailBean.actWeight);
				
				ArrayList<PlanBean> _planBeans = _planPlanQueryResultDetailBean.planBeans;
				if(null != _planBeans && _planBeans.size() > 0){
					LinearLayout _planDetailItemView = (LinearLayout)getLayoutInflater().inflate(R.layout.activity_plan_query_result_detail_item, null);
				
					for(PlanBean _planBean : _planBeans){
						((TextView)_planDetailItemView.findViewById(R.id.plan_detail_goodsName)).setText(_planBean.pname);
						((TextView)_planDetailItemView.findViewById(R.id.plan_detail_material)).setText(_planBean.material);
						((TextView)_planDetailItemView.findViewById(R.id.plan_detail_reserve_tonnage)).setText(_planBean.weight);
						((TextView)_planDetailItemView.findViewById(R.id.plan_detail_btm_reality_tonnage)).setText(_planBean.actWeight);
						((TextView)_planDetailItemView.findViewById(R.id.plan_detail_steelWorks)).setText(_planBean.steel);
					}
					
					LinearLayout.LayoutParams _layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					lyot_planDetails.addView(_planDetailItemView, _layoutParams);
				}
				
				lyot_planDetails.setVisibility(View.VISIBLE);
				
				break;
			}

			default:
				break;
			}
			
		}
	};
	
	private void loadDatas(int datasMsgWhat){
		showLoadingProgressDialog();
		
		LinkedHashMap<String, String> _webServiceRequestParams = createWebServiceQueryParams();
		
		mWebServiceThread = new WebServiceThread(new PlanQueryResultDetailBean(), _webServiceRequestParams, mActivityHandler, datasMsgWhat);
		mWebServiceThread.start();
	}


}