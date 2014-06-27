package com.cndsteel.bill.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;

public class BillContractInvoiceQueryResultActivity extends FrameActivity {
	
	private ListView viewList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.appModule_bill);
		
		appendFrameworkCenter(R.layout.activity_listview);
		
		
	}

}
