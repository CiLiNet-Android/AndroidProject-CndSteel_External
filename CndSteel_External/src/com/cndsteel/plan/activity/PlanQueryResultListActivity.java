package com.cndsteel.plan.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.handler.AbsActivityHandler;
import com.cndsteel.framework.log.GlobalLog;
import com.cndsteel.framework.views.pullToRefreshListView.PullToRefreshListView;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.plan.bean.PlanBean;
import com.cndsteel.plan.bean.PlanQueryResultListBean;

public class PlanQueryResultListActivity extends FrameActivity implements OnItemClickListener{
	
	private WebServiceThread mWebServiceThread;
	
	private PullToRefreshListView listV_planList;
	
	private ArrayList<PlanBean> list;
	
	private ImageButton imgBtn_topRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//appendFrameworkCenter(R.layout.activity_plan_query_result_list);
		initViews();
		
		loadDatas();
	}
	
	private void loadDatas(){
		showLoadingProgressDialog();
		
		LinkedHashMap<String, String> _requestParams = new LinkedHashMap<String, String>();
		_requestParams.put("year", "");
		_requestParams.put("month","");
		_requestParams.put("status", "");
		_requestParams.put("pageindex", "1");
		_requestParams.put("pagesize", "5");
		_requestParams.put("sessionId", "20C5DA37D9CF5C8FDE3DD19E858D5614");
		
		mWebServiceThread = new WebServiceThread(new PlanQueryResultListBean(), _requestParams, mActivityHandler, Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
		mWebServiceThread.start();
	}
	
	private void initViews(){
		
		setTopBarTitle(R.string.topBarTitle_planQueryResultList);
		
		
		
//		listV_planList = (PullToRefreshListView)findViewById(R.id.listV_planList);
//		
//		list = new ArrayList<PlanBean>();
//		
//		
//		////////////////////////////////////////////////
//		//只能显示4条item，只要去加载第五条item那么activity就自动关闭退出
//		
//		for(int i = 0; i < 50; i ++){
//			
//			PlanBean _plan = new PlanBean();
////			_plan.plan_isEnd = "已完结";
////			_plan.plan_year_month = "2014/06/01";
////			_plan.plan_reserve_reality = "123/321";
////			_plan.plan_must_get_pledge_money = "12345";
////			_plan.plan_must_get_pledge_date = "2014/06/01";
//			
//			list.add(_plan);
//		}
//		PlanQueryResultListAdapter _adapter = new PlanQueryResultListAdapter(getContext());
//		_adapter.initDatas(list);
//		
//		listV_planList.setAdapter(_adapter);
//		listV_planList.setOnItemClickListener(this);
//		new OnItemClickListener(){
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
//				Log.d("COM.CNDSTEEL", "position = " + position);
//				if(position > 0){
//					listV_planList.stopRefresh();
//					listV_planList.stopLoad();
//				}
//				
//				listV_planList.setFooterMode(position % 2);
//			}
//		});
	}

	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		startActivity(PlanQueryResultDetailActivity.class);
		
	}

	private AbsActivityHandler<PlanQueryResultListActivity> mActivityHandler = new AbsActivityHandler<PlanQueryResultListActivity>(this) {
		@Override
		protected void handleMessage(PlanQueryResultListActivity theActivity,
				Message msg) {
			int _msgWhat = msg.what;
			switch (_msgWhat) {
			case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS:
				appendFrameworkCenter(R.layout.activity_plan_query_result_list);
				dismissProgressDialog();
				
				PlanQueryResultListBean _bean = (PlanQueryResultListBean)msg.obj;
				GlobalLog.i(_bean.toString());
				break;

			default:
				break;
			}
			
		}
	};

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		System.out.println("sdafasdfs");
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.out.println("ddddddddddd");
		return super.onKeyDown(keyCode, event);
	}
	
	
	
	
	
}
