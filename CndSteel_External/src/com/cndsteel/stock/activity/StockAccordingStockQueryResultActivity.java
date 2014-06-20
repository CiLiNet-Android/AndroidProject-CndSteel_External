package com.cndsteel.stock.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.stock.adapter.StockAccordingStockQueryResultAdapter;
import com.cndsteel.stock.bean.Stock;

public class StockAccordingStockQueryResultActivity extends FrameActivity implements OnItemClickListener{
	
	private ListView stockQueryResultListView;
	
	private ArrayList<Stock> fragmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_stock_according_stock_query_result);
		
		init();
		
	}

	private void init() {
		
		initView();
		
	}

	private void initView() {
		
		setTopBarTitle(R.string.stock_warehouse_detail);
		
		initListView();
		
	}

	private void initListView() {
		
		fragmentList = new ArrayList<Stock>();
		
		Stock _stock = new Stock();
		_stock.goodsName = "管坯";  //品名
		_stock.material = "20#";  //材质
		_stock.standard = "2165*112";  //规格
		_stock.tonnage = "500";  //吨数
		_stock.numberOfPackages = "50"; //件数
		
		fragmentList.add(_stock);
		
		Stock _stock2 = new Stock();
		_stock2.goodsName = "管坯";  //品名
		_stock2.material = "20#";  //材质
		_stock2.standard = "2165*112";  //规格
		_stock2.tonnage = "500";  //吨数
		_stock2.numberOfPackages = "50"; //件数
		
		fragmentList.add(_stock2);
		
		Stock _stock3 = new Stock();
		_stock3.goodsName = "管坯";  //品名
		_stock3.material = "20#";  //材质
		_stock3.standard = "2165*112";  //规格
		_stock3.tonnage = "500";  //吨数
		_stock3.numberOfPackages = "50"; //件数
		
		fragmentList.add(_stock3);
		
		Stock _stock4 = new Stock();
		_stock4.goodsName = "管坯";  //品名
		_stock4.material = "20#";  //材质
		_stock4.standard = "2165*112";  //规格
		_stock4.tonnage = "500";  //吨数
		_stock4.numberOfPackages = "50"; //件数
		
		fragmentList.add(_stock4);
		
		StockAccordingStockQueryResultAdapter _adapter = new StockAccordingStockQueryResultAdapter(this);
		_adapter.initDatas(fragmentList);
		
		stockQueryResultListView = (ListView) findViewById(R.id.stockQueryResultListView);
		stockQueryResultListView.setAdapter(_adapter);
		stockQueryResultListView.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		startActivity(StockWarehouseDetailActivity.class);
		
	}

}
