package com.cndsteel.shipmentIntent.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.shipmentIntent.adapter.shipmentIntentBillDetailAdapter;
import com.cndsteel.shipmentIntent.bean.ShipmentIntent;

public class ShipmentIntentBillDetailActivity extends FrameActivity {
	
	private ListView shipmentIntentBillDetailListView;
	
	private ImageButton imgBtn_topRight;  //顶部右边的"+"号按钮
	
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
		
		imgBtn_topRight = (ImageButton) findViewById(R.id.imgBtn_topRight);
		imgBtn_topRight.setBackgroundResource(R.drawable.add);
		imgBtn_topRight.setVisibility(View.VISIBLE);
		
		initListView();
		
	}

	private void initListView() {
		
		ArrayList<ShipmentIntent> _datas = new ArrayList<ShipmentIntent>();
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "123456", "500吨", "321件"));
		
		shipmentIntentBillDetailAdapter _adapter = new shipmentIntentBillDetailAdapter(this);
		_adapter.initDatas(_datas);
		
		shipmentIntentBillDetailListView = (ListView) findViewById(R.id.shipmentIntentBillDetailListView);
		shipmentIntentBillDetailListView.setAdapter(_adapter);
		
	}

}
