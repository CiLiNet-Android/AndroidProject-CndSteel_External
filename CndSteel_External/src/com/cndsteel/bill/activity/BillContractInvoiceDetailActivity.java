package com.cndsteel.bill.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;

public class BillContractInvoiceDetailActivity extends FrameActivity {
	
	/** 合同号 **/
	private TextView billContract_num;
	/** 已开发票金额 **/
	private TextView billAlreadyMakeOutAnInvoiceAmount;
	/** 发票号 **/
	private TextView BillNumber;
	/** 开票日期 **/
	private TextView invoiceDate;
	/** 发票金额 **/
	private TextView invoiceAmount;
	/** 签收状态 **/
	private TextView signForStatus;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.TheContractOfMakeOutAnInvoiceDetail);
		
		appendFrameworkCenter(R.layout.bill_contract_invoice_detail);
		
		init();
		
		billContract_num.setText("KSDFSIH1265489");
		billAlreadyMakeOutAnInvoiceAmount.setText("321654987");
		BillNumber.setText("564123654");
		invoiceDate.setText("2014/06");
		invoiceAmount.setText("100,000");		
		signForStatus.setText("已签收");
	}
	private void init() {
		
		initView();
		
	}
	private void initView() {
		
		billContract_num = (TextView) findViewById(R.id.billContract_num);
		billAlreadyMakeOutAnInvoiceAmount = (TextView) findViewById(R.id.billAlreadyMakeOutAnInvoiceAmount);
		BillNumber = (TextView) findViewById(R.id.BillNumber);
		invoiceDate = (TextView) findViewById(R.id.invoiceDate);
		invoiceAmount = (TextView) findViewById(R.id.invoiceAmount);
		signForStatus = (TextView) findViewById(R.id.signForStatus);
	}

}
