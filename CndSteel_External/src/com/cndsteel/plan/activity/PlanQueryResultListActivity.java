package com.cndsteel.plan.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.handler.AbsActivityHandler;
import com.cndsteel.framework.views.pullToRefreshListView.PullToRefreshListView;
import com.cndsteel.plan.adapter.PlanQueryResultListAdapter;
import com.cndsteel.plan.bean.PlanBean;

public class PlanQueryResultListActivity extends FrameActivity implements OnItemClickListener{
	
	private PullToRefreshListView listV_planList;
	
	private ArrayList<PlanBean> list;
	
	private ImageButton imgBtn_topRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_plan_query_result_list);
		
		initViews();
	}
	
	private void initViews(){
		
		setTopBarTitle(R.string.topBarTitle_planQueryResultList);
		
		showProgressDialog("订货计划", "加载中..");
		
		listV_planList = (PullToRefreshListView)findViewById(R.id.listV_planList);
		
		list = new ArrayList<PlanBean>();
		
		
		////////////////////////////////////////////////
		//只能显示4条item，只要去加载第五条item那么activity就自动关闭退出
		
		for(int i = 0; i < 50; i ++){
			
			PlanBean _plan = new PlanBean();
//			_plan.plan_isEnd = "已完结";
//			_plan.plan_year_month = "2014/06/01";
//			_plan.plan_reserve_reality = "123/321";
//			_plan.plan_must_get_pledge_money = "12345";
//			_plan.plan_must_get_pledge_date = "2014/06/01";
			
			list.add(_plan);
		}
		PlanQueryResultListAdapter _adapter = new PlanQueryResultListAdapter(getContext());
		_adapter.initDatas(list);
		
		listV_planList.setAdapter(_adapter);
		listV_planList.setOnItemClickListener(this);
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
			
		}
	};
	
}
