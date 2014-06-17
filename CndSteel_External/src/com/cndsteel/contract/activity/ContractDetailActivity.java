package com.cndsteel.contract.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;

public class ContractDetailActivity extends FrameActivity {
	
	
	private TextView contract_detail_number;  //合同号
	private TextView contract_detail_signDate;  //签约日期
	private TextView contract_detail_tonnage;  //合同吨数
	private TextView contract_detail_amountOfMoney;  //合同金额
	private TextView contract_detail_mustGetPledgeDate;  //应收保证日期
	private TextView contract_detail_mustGetPledgeMoney;  //应收保证金金额
	private TextView contract_detail_realityGetPledgeDate;  //实收保证日期
	private TextView contract_detail_maturityDate;  //到期日
	private TextView contract_detail_goodsName;  //品名
	private TextView contract_detail_material;  //材质
	private TextView contract_detail_reserve;  //预订吨数

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_contract_detail);
		
		setTopBarTitle(R.string.contract_detail);
		
		init();

	}

	private void init() {
		
		initView();
		
	}

	private void initView() {
		
		initTextView();
		textViewSetText();
		
	}

	private void initTextView() {
		
		contract_detail_number = (TextView) findViewById(R.id.contract_detail_number);
		contract_detail_signDate = (TextView) findViewById(R.id.contract_detail_signDate);
		contract_detail_tonnage = (TextView) findViewById(R.id.contract_detail_tonnage);
		contract_detail_amountOfMoney = (TextView) findViewById(R.id.contract_detail_amountOfMoney);
		contract_detail_mustGetPledgeDate = (TextView) findViewById(R.id.contract_detail_mustGetPledgeDate);
		contract_detail_mustGetPledgeMoney = (TextView) findViewById(R.id.contract_detail_mustGetPledgeMoney);
		contract_detail_realityGetPledgeDate = (TextView) findViewById(R.id.contract_detail_realityGetPledgeDate);
		contract_detail_maturityDate = (TextView) findViewById(R.id.contract_detail_maturityDate);
		contract_detail_goodsName = (TextView) findViewById(R.id.contract_detail_goodsName);
		contract_detail_material = (TextView) findViewById(R.id.contract_detail_material);
		contract_detail_reserve = (TextView) findViewById(R.id.contract_detail_reserve);
		
	}


	private void textViewSetText() {
		
		contract_detail_number.setText("JKSDJF3J9Y7FY74JKDSF");
		contract_detail_signDate.setText("2014/06/01");
		contract_detail_tonnage.setText("0");
		contract_detail_amountOfMoney.setText("852025");
		contract_detail_mustGetPledgeDate.setText("2014/06/20");
		contract_detail_mustGetPledgeMoney.setText("10000");
		contract_detail_realityGetPledgeDate.setText("2014/07/01");
		contract_detail_maturityDate.setText("2014/07/01");
		contract_detail_goodsName.setText("管坯");
		contract_detail_material.setText("20#");
		contract_detail_reserve.setText("0");
		
	}


}
