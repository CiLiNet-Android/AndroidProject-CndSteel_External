package com.cndsteel.shipment.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.shipment.adapter.AsContractNumQueryResultAdapter;
import com.cndsteel.shipment.bean.QueryResultItem;

public class ShipmentAsContractNumQueryResultActivity extends FrameActivity implements OnItemClickListener{
	
	private ListView ViewListView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.appModule_shipment);
		
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
		
		ArrayList<QueryResultItem> _datas = new ArrayList<QueryResultItem>();
		_datas.add(new QueryResultItem("25asd4fe1asd6", "1000", "150"));
		_datas.add(new QueryResultItem("25asd4fe1asd6", "1000", "150"));
		_datas.add(new QueryResultItem("25asd4fe1asd6", "1000", "150"));
		_datas.add(new QueryResultItem("25asd4fe1asd6", "1000", "150"));
		
		AsContractNumQueryResultAdapter _adapter = new AsContractNumQueryResultAdapter(this);
		_adapter.initDatas(_datas);
		
		ViewListView = (ListView) findViewById(R.id.contract_listView);
		
		ViewListView.setAdapter(_adapter);
		
		ViewListView.setOnItemClickListener(this);
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		startActivity(ShipmentDetailAsContract.class);
		
	}
}
