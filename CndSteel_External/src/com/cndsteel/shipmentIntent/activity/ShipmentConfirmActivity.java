package com.cndsteel.shipmentIntent.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.shipmentIntent.adapter.shipmentIntentBillDetailAdapter;
import com.cndsteel.shipmentIntent.bean.ShipmentIntent;

public class ShipmentConfirmActivity extends FrameActivity implements OnClickListener {
	
	private ListView shipmentIntentBillDetailListView;
	
	private Button btn_confirm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.confirmPickUpTheGoodsIntent);
		
		appendFrameworkCenter(R.layout.shipment_confirm_intent);
		
		init();
		
	}

private void init() {
		
		initView();
		
	}

	private void initView() {
		
		btn_confirm = (Button) findViewById(R.id.btn_confirm);
		btn_confirm.setOnClickListener(this);
		
		initListView();
		
	}

	private void initListView() {
		
		ArrayList<ShipmentIntent> _datas = new ArrayList<ShipmentIntent>();
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "123456", "500吨", "321件"));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "123456", "500吨", "321件"));
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

	@Override
	public void onClick(View view) {
		showToast(R.string.confirmPickUpTheGoodsIntent);
	}
}
