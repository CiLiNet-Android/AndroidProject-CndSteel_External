package com.cndsteel.plan.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.RelativeLayout;

import android.view.View;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;

/**
 * 订货计划查询
 * @author zhxl
 *
 */
public class PlanQueryActivity extends FrameActivity implements View.OnClickListener {
	
	/** 计划年月 **/
	private RelativeLayout lyot_planDate;
	/** 计划状态 **/
	private RelativeLayout lyot_planStatue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_plan_query);
		
		initViews();
	}

	private void initViews(){
		setTopBarTitle(R.string.topBarTitle_planQuery);
		
		lyot_planDate = (RelativeLayout)findViewById(R.id.lyot_planDate);
		lyot_planDate.setOnClickListener(this);
		
		lyot_planStatue = (RelativeLayout)findViewById(R.id.lyot_planStatus);
		lyot_planStatue.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		ArrayList<String> xx = new ArrayList<String>();
		for(int i = 0; i < 20; i ++){
			xx.add("content_" + i);
		}
		
		CndSteelSpinner cndSteelSpinner = new CndSteelSpinner(getApplicationContext(), xx, lyot_planDate.getWidth());
		cndSteelSpinner.showAsDropDown(lyot_planDate,0,-5);
	}
	
}
