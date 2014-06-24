package com.cndsteel.stock.activity;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.stock.bean.Stock;

public class StockAccordingGoodsQueryResultActivity extends FrameActivity implements OnItemClickListener {
	
	private ListView StockAccordingGoodsQueryResultListView;
	
	private ArrayList<Stock> fragmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_stock_according_goods_query_result);
		
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

		Stock _stock = new Stock("管坯",  "1000", "100");
		fragmentList.add(_stock);
		
		Stock _stock_2 = new Stock("管坯", "1000", "100");
		fragmentList.add(_stock_2);

		MyAdapter _adapter = new MyAdapter(this);
		_adapter.initDatas(fragmentList);
		
		StockAccordingGoodsQueryResultListView = (ListView) findViewById(R.id.StockAccordingGoodsQueryResultListView);
		StockAccordingGoodsQueryResultListView.setAdapter(_adapter);
		StockAccordingGoodsQueryResultListView.setOnItemClickListener(this);
		
	}
	

	/** listview的item的单击事件 **/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		startActivity(StockGoodsDetailActivity.class);
		
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
				view = getLayoutInflater().inflate(R.layout.activity_stock_according_goods_query_result_item, null);

				_holder.StockAccordingGoodsQueryResult_warehouse = (TextView) view.findViewById(R.id.StockAccordingGoodsQueryResult_warehouse);
				_holder.StockAccordingGoodsQueryResult_Tonnage = (TextView) view.findViewById(R.id.StockAccordingGoodsQueryResult_Tonnage);
				_holder.StockAccordingGoodsQueryResult_NumberOfPackages = (TextView) view.findViewById(R.id.StockAccordingGoodsQueryResult_NumberOfPackages);

				view.setTag(_holder);
			}else{
				_holder = (ViewHolder) view.getTag();
			}

			
			Stock _stock = (Stock) getItem(position);
			_holder.StockAccordingGoodsQueryResult_warehouse.setText(_stock.warehouse);
			_holder.StockAccordingGoodsQueryResult_Tonnage.setText(_stock.tonnage);
			_holder.StockAccordingGoodsQueryResult_NumberOfPackages.setText(_stock.numberOfPackages);
			
			return view;
		}
		
	}
	
	private class ViewHolder {
		
		private TextView StockAccordingGoodsQueryResult_warehouse;
		private TextView StockAccordingGoodsQueryResult_Tonnage;
		private TextView StockAccordingGoodsQueryResult_NumberOfPackages;
		
	}


}
