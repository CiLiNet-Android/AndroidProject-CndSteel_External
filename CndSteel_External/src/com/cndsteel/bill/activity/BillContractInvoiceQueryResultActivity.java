package com.cndsteel.bill.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.bill.adapter.BillContractInvoiceQueryResultAdapter;
import com.cndsteel.bill.bean.Bill;
import com.cndsteel.framework.activity.FrameActivity;

public class BillContractInvoiceQueryResultActivity extends FrameActivity implements OnItemClickListener {
	
	private ListView viewList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.appModule_bill);
		
		appendFrameworkCenter(R.layout.activity_listview);
		
		init();
		
	}

	private void init() {
		
		initeView();
		
	}

	private void initeView() {
		
		initListView();
		
	}

	private void initListView() {
		
		ArrayList<Bill> itemList = new ArrayList<Bill>();
		itemList.add(new Bill("21365487954263", "10,000,000", "5,000,000", "5,000,000"));
		itemList.add(new Bill("21365487954263", "10,000,000", "5,000,000", "5,000,000"));
		itemList.add(new Bill("21365487954263", "10,000,000", "5,000,000", "5,000,000"));
		itemList.add(new Bill("21365487954263", "10,000,000", "5,000,000", "5,000,000"));
		itemList.add(new Bill("21365487954263", "10,000,000", "5,000,000", "5,000,000"));
		
		viewList = (ListView) findViewById(R.id.contract_listView);
		viewList.setAdapter(new BillContractInvoiceQueryResultAdapter(this, itemList));
		viewList.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		startActivity(BillContractInvoiceDetailActivity.class);
		
	}

}
