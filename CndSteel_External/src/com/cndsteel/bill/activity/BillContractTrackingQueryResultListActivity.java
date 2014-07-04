package com.cndsteel.bill.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.bill.adapter.BillContractTrackingQueryResultAdapter;
import com.cndsteel.bill.bean.Bill;
import com.cndsteel.framework.activity.FrameActivity;

public class BillContractTrackingQueryResultListActivity extends FrameActivity implements OnItemClickListener {
	
	private ListView viewList;
	
	private ArrayList<Bill> itemList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.appModule_bill);
		
		appendFrameworkCenter(R.layout.activity_listview);
		
		init();
	}

	private void init() {
		initView();
	}

	private void initView() {
		initListView();
	}

	private void initListView() {
		
		itemList = new ArrayList<Bill>();
		itemList.add(new Bill("SC664dcas56S", "已收取", "已收取"));
		itemList.add(new Bill("SC664dcas56S", "未收取", "未收取"));
		itemList.add(new Bill("SC664dcas56S", "已收取", "已收取"));
		itemList.add(new Bill("SC664dcas56S", "未收取", "未收取"));
		itemList.add(new Bill("SC664dcas56S", "已收取", "已收取"));
		itemList.add(new Bill("SC664dcas56S", "未收取", "未收取"));
		
		viewList = (ListView) findViewById(R.id.contract_listView);
		viewList.setAdapter(new BillContractTrackingQueryResultAdapter(this, itemList));
		viewList.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		startActivity(BillContractTrackingDetailActivity.class);
	}

}
