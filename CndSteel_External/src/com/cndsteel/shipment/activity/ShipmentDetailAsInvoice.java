package com.cndsteel.shipment.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.shipment.adapter.ShipmentDetailAsInvoiceAdapter;
import com.cndsteel.shipment.bean.ShipmentAsInvoice;

public class ShipmentDetailAsInvoice extends FrameActivity {
	
	private ListView shipmentDetailListview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.shipmentDetail);
		
		appendFrameworkCenter(R.layout.shipment_detail_as_invoice);
		
		init();
	}

	private void init() {
		
		initView();
		
	}

	private void initView() {
		
		
		initListView();
	}

	private void initListView() {
		ArrayList<ShipmentAsInvoice> _datas = new ArrayList<ShipmentAsInvoice>();
		_datas.add(new ShipmentAsInvoice("管坯", "20#", "100*100", "2000", "50", "200000000"));
		_datas.add(new ShipmentAsInvoice("管坯", "20#", "100*100", "2000", "50", "200000000"));
		_datas.add(new ShipmentAsInvoice("管坯", "20#", "100*100", "2000", "50", "200000000"));
		_datas.add(new ShipmentAsInvoice("管坯", "20#", "100*100", "2000", "50", "200000000"));
		
		ShipmentDetailAsInvoiceAdapter _adapter = new ShipmentDetailAsInvoiceAdapter(this);
		_adapter.initDatas(_datas);
		
		shipmentDetailListview = (ListView) findViewById(R.id.shipmentDetailListview);
		shipmentDetailListview.setAdapter(_adapter);
		
	}
}
