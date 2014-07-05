package com.cndsteel.bill.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;

public class BillContractInvoiceQueryResulltDetailActivity extends FrameActivity {
	
	/** 合同号 **/
	private TextView txtV_contractInvoiceDetailConCode;
	/** 已开发票金额 **/
	private TextView txtV_contractInvoiceDetailInvedAmt;
	/** 发票号 **/
	private TextView txtV_contractInvoiceDetailInvNO;
	/** 开票日期 **/
	private TextView txtV_contractInvoiceDetailInvDate;
	/** 发票金额 **/
	private TextView txtV_contractInvoiceDetailAmount;
	/** 签收状态 **/
	private TextView txtV_contractInvoiceDetailSignStatus;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.TheContractOfMakeOutAnInvoiceDetail);
		
		appendFrameworkCenter(R.layout.bill_contract_invoice_detail);
		
		init();
		
		txtV_contractInvoiceDetailConCode.setText("KSDFSIH1265489");
		txtV_contractInvoiceDetailInvedAmt.setText("321654987");
		txtV_contractInvoiceDetailInvNO.setText("564123654");
		txtV_contractInvoiceDetailInvDate.setText("2014/06");
		txtV_contractInvoiceDetailAmount.setText("100,000");		
		txtV_contractInvoiceDetailSignStatus.setText("已签收");
	}
	private void init() {
		
		initView();
		
	}
	private void initView() {
		
		txtV_contractInvoiceDetailConCode = (TextView) findViewById(R.id.txtV_contractInvoiceDetailConCode);
		txtV_contractInvoiceDetailInvedAmt = (TextView) findViewById(R.id.txtV_contractInvoiceDetailInvedAmt);
		txtV_contractInvoiceDetailInvNO = (TextView) findViewById(R.id.txtV_contractInvoiceDetailInvNO);
		txtV_contractInvoiceDetailInvDate = (TextView) findViewById(R.id.txtV_contractInvoiceDetailInvDate);
		txtV_contractInvoiceDetailAmount = (TextView) findViewById(R.id.txtV_contractInvoiceDetailAmount);
		txtV_contractInvoiceDetailSignStatus = (TextView) findViewById(R.id.txtV_contractInvoiceDetailSignStatus);
	}

}
