package com.cndsteel.bill.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.adapter.BillContractTrackingDetailAdapter;
import com.cndsteel.bill.bean.BillContractTrackingDetailItem;
import com.cndsteel.framework.activity.FrameActivity;

public class BillContractTrackingDetailActivity extends FrameActivity {
	
	private ListView contract_listView;
	
	private TextView billContract_num;
	
	private ArrayList<BillContractTrackingDetailItem> itemLists;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.TheContractOfMakeOutAnInvoiceDetail);
		
		appendFrameworkCenter(R.layout.bill_contract_tracking_detail);
		
		init();
	}

	private void init() {
		
		initView();
		
	}

	private void initView() {
		
		billContract_num = (TextView) findViewById(R.id.billContract_num);
		billContract_num.setText("DSFDFWER@#%$#%$%^%$#F");
		
		initListView();
		
	}

	private void initListView() {
		
		itemLists = new ArrayList<BillContractTrackingDetailItem>();
		itemLists.add(new BillContractTrackingDetailItem("建发寄出", "顺丰", "SDF64613215644DSF", "2014/06/01 12:00:00", 
				"张三", "李四", "2014/06/01 12:00:00", "......"));
		itemLists.add(new BillContractTrackingDetailItem("我司寄出", "顺丰", "SDF64613215644DSF", "2014/06/01 12:00:00", 
				"张三", "李四", "2014/06/01 12:00:00", "......"));

		contract_listView = (ListView) findViewById(R.id.contract_listView);
		contract_listView.setAdapter(new BillContractTrackingDetailAdapter(this, itemLists));
		
	}
}
