package com.cndsteel.plan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;

public class PlanDetailActivity extends FrameActivity {
	
	private TextView plan_detail_yearMonth;  //订货计划年月
	private TextView plan_detail_must_get_pledge_money;  //应收保证金金额
	private TextView plan_detail_must_get_pledge_date;  //应收保证金日期
	private TextView plan_detail_status;  //状态
	private TextView plan_detail_tonnage;  //计划吨数
	private TextView plan_detail_reality_tonnage;  //实际吨数
	private TextView plan_detail_goodsName;  //品名
	private TextView plan_detail_material;  //材质
	private TextView plan_detail_reserve_tonnage;  //预定吨数
	private TextView plan_detail_btm_reality_tonnage;  //下面实际吨数
	private TextView plan_detail_steelWorks;  //钢厂
	
	
	private ImageButton imgBtn_topRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_plan_detail);

		init();

	}

	private void init() {
		
		initView();
		
	}

	private void initView() {
		
		setTopBarTitle(R.string.topBarTitle_plan_datail);
		
		imgBtn_topRight = (ImageButton) findViewById(R.id.imgBtn_topRight);
		imgBtn_topRight.setVisibility(View.VISIBLE);
		imgBtn_topRight.setBackgroundResource(R.drawable.add);
		
		initTextView();
		textViewSetText();
		
	}

	private void initTextView() {
		
		plan_detail_yearMonth = (TextView) findViewById(R.id.plan_detail_yearMonth);
		plan_detail_must_get_pledge_money = (TextView) findViewById(R.id.plan_detail_must_get_pledge_money);
		plan_detail_must_get_pledge_date = (TextView) findViewById(R.id.plan_detail_must_get_pledge_date);
		plan_detail_status = (TextView) findViewById(R.id.plan_detail_status);
		plan_detail_tonnage = (TextView) findViewById(R.id.plan_detail_tonnage);
		plan_detail_reality_tonnage = (TextView) findViewById(R.id.plan_detail_reality_tonnage);
		plan_detail_goodsName = (TextView) findViewById(R.id.plan_detail_goodsName);
		plan_detail_material = (TextView) findViewById(R.id.plan_detail_material);
		plan_detail_reserve_tonnage = (TextView) findViewById(R.id.plan_detail_reserve_tonnage);
		plan_detail_btm_reality_tonnage = (TextView) findViewById(R.id.plan_detail_btm_reality_tonnage);
		plan_detail_steelWorks = (TextView) findViewById(R.id.plan_detail_steelWorks);
		
	}


	private void textViewSetText() {
		
		plan_detail_yearMonth.setText("2014/06");
		plan_detail_must_get_pledge_money.setText("1000");
		plan_detail_must_get_pledge_date.setText("2014/06/01");
		plan_detail_status.setText("已完结");
		plan_detail_tonnage.setText("10000");
		plan_detail_reality_tonnage.setText("10000");
		plan_detail_goodsName.setText("管坯");
		plan_detail_material.setText("20#");
		plan_detail_reserve_tonnage.setText("12345");
		plan_detail_btm_reality_tonnage.setText("20000");
		plan_detail_steelWorks.setText("厦门");
		
	}


}