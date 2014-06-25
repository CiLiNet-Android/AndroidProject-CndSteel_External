package com.cndsteel.plan.activity;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.handler.AbsActivityHandler;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.plan.adapter.PlanQueryResultListAdapter;
import com.cndsteel.plan.bean.PlanBean;
import com.cndsteel.plan.bean.PlanQueryResultListBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class PlanQueryResultListActivity extends FrameActivity implements OnItemClickListener,OnRefreshListener<ListView>{
	
	private WebServiceThread mWebServiceThread;
	
	/** 查询条件 **/
	private String mQueryParamYear;
	private String mQueryParamMonth;
	private String mQueryParamStatus;
	private int mQueryParamPageIndex = 1;
	private int mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
	private String mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	
	/** 视图 **/
	private PullToRefreshListView listV_planList;
	/** 适配器 **/
	private PlanQueryResultListAdapter mPlanQueryResultListAdapter;
	
	private ImageButton imgBtn_topRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendFrameworkCenter(R.layout.activity_plan_query_result_list);
		
		initViews();
		
		initVariables();
		
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}
	
	/**
	 * 初始化变量
	 */
	private void initVariables() {
		Bundle _queryParams = getIntent().getExtras();
		mQueryParamYear = _queryParams.getString(QueryParams.QUERY_PARAM_PLAN_DATE_YEAR);
		mQueryParamMonth = _queryParams.getString(QueryParams.QUERY_PARAM_PLAN_DATE_MONTH);
		mQueryParamStatus = _queryParams.getString(QueryParams.QUERY_PARAM_PLAN_STATUS);
	}

	private void loadDatas(int datasMsgWhat){
		showLoadingProgressDialog();
		
		LinkedHashMap<String, String> _webServiceRequestParams = createWebServiceQueryParams();
		
		mWebServiceThread = new WebServiceThread(new PlanQueryResultListBean(), _webServiceRequestParams, mActivityHandler, datasMsgWhat);
		mWebServiceThread.start();
	}
	
	private void initViews(){
		setTopBarTitle(R.string.topBarTitle_planQueryResultList);
		
		listV_planList = (PullToRefreshListView)findViewById(R.id.listV_planList);
		listV_planList.setMode(Mode.BOTH);
		
		listV_planList.setOnRefreshListener(this);
		listV_planList.setOnItemClickListener(this);
		
		mPlanQueryResultListAdapter = new PlanQueryResultListAdapter(getContext());
		mPlanQueryResultListAdapter.initDatas(new LinkedList<PlanBean>());
		listV_planList.setAdapter(mPlanQueryResultListAdapter);
	}

	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent _intent = new Intent(this,PlanQueryResultDetailActivity.class);
		
		PlanBean _planBean = (PlanBean)parent.getAdapter().getItem(position);
		Bundle _queryParams = new Bundle();
		_queryParams.putString(QueryParams.QUERY_PARAM_PLAN_BOOKING_ID, _planBean.bookingId);
		_intent.putExtras(_queryParams);
		
		startActivity(_intent);
	}

	private AbsActivityHandler<PlanQueryResultListActivity> mActivityHandler = new AbsActivityHandler<PlanQueryResultListActivity>(this) {
		@Override
		protected void handleMessage(final PlanQueryResultListActivity theActivity,
				Message msg) {
			int _msgWhat = msg.what;
			switch (_msgWhat) {
			//第一次加载
			case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
				theActivity.dismissProgressDialog();
				
				PlanQueryResultListBean _planQueryResultListBean = (PlanQueryResultListBean)msg.obj;
				if(_planQueryResultListBean.code > 0){
					theActivity.mPlanQueryResultListAdapter.refreshDatas(_planQueryResultListBean.mPlanBeans);
				}else {
					theActivity.showAlertDialog(R.string.topBarTitle_planQuery, theActivity.getString(R.string.DialogMsgNoDatas, theActivity.getString(R.string.topBarTitle_planQueryResultList)), new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if(which == DialogInterface.BUTTON_NEUTRAL){
								theActivity.finish();
							}
						}
					});
					
				}
				
				break;
			}
			//重复加载
			case Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS: {
				theActivity.dismissProgressDialog();
				
				PlanQueryResultListBean _planQueryResultListBean = (PlanQueryResultListBean)msg.obj;
				if(_planQueryResultListBean.code > 0){
					theActivity.mPlanQueryResultListAdapter.refreshDatas(_planQueryResultListBean.mPlanBeans);
				}
				
				listV_planList.onRefreshComplete();
				break;
			}
			case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
				theActivity.dismissProgressDialog();
				
				PlanQueryResultListBean _planQueryResultListBean = (PlanQueryResultListBean)msg.obj;
				if(_planQueryResultListBean.code > 0){
					theActivity.mPlanQueryResultListAdapter.appendDatas(_planQueryResultListBean.mPlanBeans);
				}
				
				listV_planList.onRefreshComplete();
				break;
			}
			default:
				break;
			}
			
		}
	};

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		//下拉更新
		if(listV_planList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_planList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.out.println("onKeyDown : " + keyCode);
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(null != mWebServiceThread){
				mWebServiceThread.cancel();
				mWebServiceThread = null;
			}
		}
		
		return super.onKeyDown(keyCode, event);
	}

	private LinkedHashMap<String,String> createWebServiceQueryParams(){
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PLAN_DATE_YEAR, mQueryParamYear);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PLAN_DATE_MONTH,mQueryParamMonth);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PLAN_STATUS, mQueryParamStatus);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		return _webServiceRequestParams;
	}
	
}
