package com.cndsteel.contract.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.views.spinner.AbsSpinner.OnItemClickListener;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;

public class ContractActivity extends FrameActivity implements OnClickListener,OnItemClickListener{
	
	//合同年月
	private RelativeLayout lyot_contractDate;
	private TextView txtV_contractDate;
	
	//合同号
	private RelativeLayout lyot_contractNum;
	private EditText txtV_contractNumber;
	
	//合同状态
	private RelativeLayout lyot_contractStatus;
	private TextView txtV_contractStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_contract);
		
		initView();
		
	}

	private void initView() {
		
		setTopBarTitle(R.string.appModule_contract);
		
		lyot_contractDate = (RelativeLayout) findViewById(R.id.lyot_contractDate);
		lyot_contractDate.setOnClickListener(this);
		txtV_contractDate = (TextView) findViewById(R.id.txtV_contractDate);
		
		lyot_contractNum = (RelativeLayout) findViewById(R.id.lyot_contractNum);
		lyot_contractNum.setOnClickListener(this);
		txtV_contractNumber = (EditText) findViewById(R.id.txtV_contractNumber);
		
		lyot_contractStatus = (RelativeLayout) findViewById(R.id.lyot_contractStatus);
		lyot_contractStatus.setOnClickListener(this);
		txtV_contractStatus = (TextView) findViewById(R.id.txtV_contractStatus);
		
		Button btn_contractQuery = (Button) findViewById(R.id.btn_contractQuery);
		btn_contractQuery.setOnClickListener(this);
		
	}

	/** 单击事件处理  **/
	@Override
	public void onClick(View view) {
		
		CndSteelSpinner cndSteelSpinner = null;
		
		ArrayList<String> xx;
		
		switch (view.getId()) {
		
		//合同年月
		case R.id.lyot_contractDate:
			showToast(R.string.contract_date);
			
			xx = new ArrayList<String>();
			for(int i = 0; i < 5; i ++){
				xx.add("content_" + i);
			}
			
			cndSteelSpinner = new CndSteelSpinner(getApplicationContext(), xx, lyot_contractDate.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_contractDate,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
			
			break;
			
		//合同号
		case R.id.lyot_contractNum:
			showToast(R.string.contract_number);
			break;
			
		//合同状态
		case R.id.lyot_contractStatus:
			showToast(R.string.contract_status);
			
			xx = new ArrayList<String>();
			for(int i = 0; i < 5; i ++){
				xx.add("content_" + i);
			}
			
			cndSteelSpinner = new CndSteelSpinner(getApplicationContext(), xx, lyot_contractStatus.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_contractStatus,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
			
			
			break;
			
		//查询按钮
		case R.id.btn_contractQuery:
			showToast(R.string.query);
			
			//启动:合同查询结果
			startActivity(ContractQueryResultActivity.class);

		default:
			break;
		}
		
	}

	//spinner的item的单击事件处理
	@Override
	public void onItemClick(int position) {
		
		//接口没有开放Adapter和View就不能判断和获取数据了?
		
		showToast(String.valueOf(position).trim());
		
	}
}
