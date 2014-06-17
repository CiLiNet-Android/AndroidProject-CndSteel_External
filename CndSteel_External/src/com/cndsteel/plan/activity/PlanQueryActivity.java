package com.cndsteel.plan.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.views.spinner.AbsSpinner.OnItemClickListener;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;

/**
 * 订货计划查询
 * 
 * @author zhxl
 * 
 */
public class PlanQueryActivity extends FrameActivity implements View.OnClickListener,OnItemClickListener {

	/** 计划年月 **/
	private RelativeLayout lyot_planDate;
	private TextView txtV_planDate;
	
	/** 计划状态 **/
	private RelativeLayout lyot_planStatue;
	private TextView txtV_planStatus;
	
	private Button btn_planQuery;
	
	private ImageButton imgBtn_topRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appendFrameworkCenter(R.layout.activity_plan_query);

		initViews();
	}

	private void initViews() {
		
		setTopBarTitle(R.string.topBarTitle_planQuery);
		
		imgBtn_topRight = (ImageButton) findViewById(R.id.imgBtn_topRight);
		imgBtn_topRight.setVisibility(View.VISIBLE);
		imgBtn_topRight.setBackgroundResource(R.drawable.add);

		lyot_planDate = (RelativeLayout) findViewById(R.id.lyot_planDate);
		lyot_planDate.setOnClickListener(this);

		lyot_planStatue = (RelativeLayout) findViewById(R.id.lyot_planStatus);
		lyot_planStatue.setOnClickListener(this);
		
		btn_planQuery = (Button) findViewById(R.id.btn_planQuery);
		btn_planQuery.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		
		CndSteelSpinner cndSteelSpinner;
		
		ArrayList<String> xx = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			xx.add("content_" + i);
		}

		switch (view.getId()) {

		//计划年月
		case R.id.lyot_planDate:
			
			cndSteelSpinner = new CndSteelSpinner(getApplicationContext(), xx, lyot_planDate.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_planDate, 0, -5);
			cndSteelSpinner.setOnItemClickListener(this);

			break;
			
		//计划状态
		case R.id.lyot_planStatus:

			cndSteelSpinner = new CndSteelSpinner(getApplicationContext(), xx, lyot_planStatue.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_planStatue, 0, -5);
			cndSteelSpinner.setOnItemClickListener(this);
			
			break;
			
		//查询按钮
		case R.id.btn_planQuery:
			
			startActivity(PlanQueryResultListActivity.class);

		default:
			break;
		}

	}

	//下拉spinner的item的点击事件处理
	@Override
	public void onItemClick(int position) {
		
	}


}
