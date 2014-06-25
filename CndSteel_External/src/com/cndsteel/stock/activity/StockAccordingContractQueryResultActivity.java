package com.cndsteel.stock.activity;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.stock.activity.StockWarehouseDetailActivity.StockDetail;
import com.cndsteel.stock.adapter.StockAccordingStockQueryResultAdapter;
import com.cndsteel.stock.bean.Stock;

public class StockAccordingContractQueryResultActivity extends FrameActivity implements OnItemClickListener{
	
	private ListView stock_AccordingContractQueryResultListView;
	
	private ArrayList<Stock> fragmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appendFrameworkCenter(R.layout.activity_stock_according_contract_query_result);

		init();

	}

	private void init() {

		initView();

	}

	private void initView() {

		setTopBarTitle(R.string.warehouse);
		


		initListView();

	}

	private void initListView() {

		fragmentList = new ArrayList<Stock>();

		Stock _stock = new Stock("管坯", "20#", "100*100", "1000", "100", "5");
		fragmentList.add(_stock);
		
		Stock _stock_2 = new Stock("管坯", "20#", "100*100", "1000", "100", "5");
		fragmentList.add(_stock_2);

		MyAdapter _adapter = new MyAdapter(this);
		_adapter.initDatas(fragmentList);
		

		stock_AccordingContractQueryResultListView = (ListView) findViewById(R.id.stock_AccordingContractQueryResultListView);
		stock_AccordingContractQueryResultListView.setAdapter(_adapter);
		stock_AccordingContractQueryResultListView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		startActivity(StockContractDetailActivity.class);

	}
	
	
	
	private class MyAdapter extends AbsBaseAdapter<Stock>{

		public MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(int position, View view, ViewGroup viewGroup) {
			ViewHolder _holder;
			if(null == view){
				_holder = new ViewHolder();
				view = getLayoutInflater().inflate(R.layout.activity_stock_according_stock_query_result_item, null);

				_holder.stock_goodsName = (TextView) view.findViewById(R.id.stock_goodsName);
				_holder.stock_Material = (TextView) view.findViewById(R.id.stock_Material);
				_holder.stock_Standard = (TextView) view.findViewById(R.id.stock_Standard);
				_holder.stock_Tonnage = (TextView) view.findViewById(R.id.stock_Tonnage);
				_holder.stock_NumberOfPackages = (TextView) view.findViewById(R.id.stock_NumberOfPackages);
				_holder.stock_warehouse = (TextView) view.findViewById(R.id.stock_Value);				
				_holder.title_Hide = (TextView) view.findViewById(R.id.title_Hide);

				view.setTag(_holder);
			}else{
				_holder = (ViewHolder) view.getTag();
			}

			
			Stock _stock = (Stock) getItem(position);
			_holder.stock_goodsName.setText(_stock.goodsName);
			_holder.stock_Material.setText(_stock.material);
			_holder.stock_Standard.setText(_stock.standard);
			_holder.stock_Tonnage.setText(_stock.tonnage);
			_holder.stock_NumberOfPackages.setText(_stock.numberOfPackages);
			_holder.stock_warehouse.setText(_stock.warehouse);
			_holder.stock_warehouse.setVisibility(View.VISIBLE);
			_holder.title_Hide.setVisibility(View.VISIBLE);
			_holder.title_Hide.setText(getResources().getString(R.string.Warehouse));
			
			return view;
		}
		
	}
	
	private class ViewHolder {
		
		private TextView stock_goodsName;
		private TextView stock_Material;
		private TextView stock_Standard;
		private TextView stock_Tonnage;
		private TextView stock_NumberOfPackages;
		private TextView stock_warehouse;
		private TextView title_Hide;
		
	}
	
}
