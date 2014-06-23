package com.cndsteel.shipment.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.shipment.adapter.AsInvoiceQueryResultAdapter;
import com.cndsteel.shipment.bean.AsInvoiceQueryResultItem;

public class ShipmentAsInvoiceNumQueryResultActivity extends FrameActivity implements OnItemClickListener{
	
	private ListView itemList;
	
//	private TextView shipment_AsInvoiceNum_value;    //发货单号
//	private TextView shipment_AsInvoiceNum_tonnage;    //吨数
//	private TextView shipment_AsInvoiceNum_numberOfPackages;    //件数
//	private TextView shipment_AsInvoiceNum_warehouse;    //仓库
//	private TextView shipment_AsInvoiceNum_date;    //发货日期
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_listview);
		
		setTopBarTitle(R.string.appModule_shipment);
		
		init();
		
	}

	private void init() {
		initView();
	}

	private void initView() {
		
		itemList = (ListView) findViewById(R.id.contract_listView);
		
		ArrayList<AsInvoiceQueryResultItem> _datas = new ArrayList<AsInvoiceQueryResultItem>();
		_datas.add(new AsInvoiceQueryResultItem("231564983", "2000", "200", "1号仓", "2014/06/01"));
		_datas.add(new AsInvoiceQueryResultItem("231564983", "2000", "200", "1号仓", "2014/06/01"));
		_datas.add(new AsInvoiceQueryResultItem("231564983", "2000", "200", "1号仓", "2014/06/01"));
		_datas.add(new AsInvoiceQueryResultItem("231564983", "2000", "200", "1号仓", "2014/06/01"));
		_datas.add(new AsInvoiceQueryResultItem("231564983", "2000", "200", "1号仓", "2014/06/01"));
		
		AsInvoiceQueryResultAdapter _adapter = new AsInvoiceQueryResultAdapter(this);
		_adapter.initDatas(_datas);
		
		itemList.setAdapter(_adapter);
		itemList.setOnItemClickListener(this);
		
//		shipment_AsInvoiceNum_value = (TextView) findViewById(R.id.shipment_AsInvoiceNum_value);
//		shipment_AsInvoiceNum_tonnage = (TextView) findViewById(R.id.shipment_AsInvoiceNum_tonnage);
//		shipment_AsInvoiceNum_numberOfPackages = (TextView) findViewById(R.id.shipment_AsInvoiceNum_numberOfPackages);
//		shipment_AsInvoiceNum_warehouse = (TextView) findViewById(R.id.shipment_AsInvoiceNum_warehouse);
//		shipment_AsInvoiceNum_date = (TextView) findViewById(R.id.shipment_AsInvoiceNum_date);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		startActivity(ShipmentDetailAsInvoice.class);
	}
}
