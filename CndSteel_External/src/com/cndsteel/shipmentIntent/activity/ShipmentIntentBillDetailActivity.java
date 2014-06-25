package com.cndsteel.shipmentIntent.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.shipmentIntent.adapter.shipmentIntentBillDetailAdapter;
import com.cndsteel.shipmentIntent.bean.ShipmentIntent;

public class ShipmentIntentBillDetailActivity extends FrameActivity {
	
	private ListView shipmentIntentBillDetailListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.shipmentIntentBillDetail);
		
		appendFrameworkCenter(R.layout.shipment_intent_bill_detail);
		
		init();
		
	}

	private void init() {
		
		initView();
		
	}

	private void initView() {
		
		initListView();
		
	}

	private void initListView() {
		
		ArrayList<ShipmentIntent> _datas = new ArrayList<ShipmentIntent>();
		_datas.add(new ShipmentIntent("管坯", "20#", "  ", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "  ", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "  ", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "  ", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "  ", "123456", "500吨", "321件"));
		
		shipmentIntentBillDetailAdapter _adapter = new shipmentIntentBillDetailAdapter(this);
		_adapter.initDatas(_datas);
		
		shipmentIntentBillDetailListView = (ListView) findViewById(R.id.shipmentIntentBillDetailListView);
		shipmentIntentBillDetailListView.setAdapter(_adapter);
		
	}

}
