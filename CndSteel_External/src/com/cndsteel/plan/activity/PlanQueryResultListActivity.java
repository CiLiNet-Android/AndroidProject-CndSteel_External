package com.cndsteel.plan.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.views.pullToRefreshListView.PullToRefreshListView;
import com.cndsteel.plan.adapter.PlanQueryResultListAdapter;

public class PlanQueryResultListActivity extends FrameActivity {
	
	private PullToRefreshListView listV_planList;
	
	ArrayList<String> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_plan_query_result_list);
		
		initViews();
	}
	
	private void initViews(){
		setTopBarTitle(R.string.topBarTitle_planQueryResultList);
		
		listV_planList = (PullToRefreshListView)findViewById(R.id.listV_planList);
		
		list = new ArrayList<String>();
		for(int i = 0; i < 70; i ++){
			list.add("logger_" + i);
		}
		PlanQueryResultListAdapter _adapter = new PlanQueryResultListAdapter(getContext());
		_adapter.initDatas(list);
		
		listV_planList.setAdapter(_adapter);
		listV_planList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.d("COM.CNDSTEEL", "arg2 = " + arg2);
				if(arg2 > 0){
					listV_planList.stopRefresh();
					listV_planList.stopLoad();
				}
				
				listV_planList.setFooterMode(arg2 % 2);
			}
		});
	}

	
}
