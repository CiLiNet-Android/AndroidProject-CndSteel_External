package com.cndsteel.stock.activity;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.adapter.AbsBaseAdapter;

public class StockGoodsDetailActivity extends FrameActivity {
	
	private ListView StockWarehouseDetail_listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_stock_warehouse_detail);
		
		init();
		
	}

	private void init() {

		initView();
		
	}

	private void initView() {
		
		setTopBarTitle(R.string.No_1);
		
		
		TextView title_Money = (TextView) findViewById(R.id.title_Hide);
		title_Money.setText(getResources().getString(R.string.Money));
		title_Money.setVisibility(View.VISIBLE);
		
		TextView stock_moneyValue = (TextView) findViewById(R.id.stock_Value);
		stock_moneyValue.setText("13");
		stock_moneyValue.setVisibility(View.VISIBLE);
		
		initListView();
		
	}

	private void initListView() {
		
		ArrayList<StockDetail> _datas = new ArrayList<StockGoodsDetailActivity.StockDetail>();
		
		StockDetail _s1 = new StockDetail();
		_s1.stock_detail_into_date = "2014/01/01";
		_s1.stock_detail_package_num = "12345678";
		_s1.stock_detail_tonnage = "500";
		_s1.stock_detail_numberOfPackages = "1000";
		_datas.add(_s1);
		
		StockDetail _s2 = new StockDetail();
		_s2.stock_detail_into_date = "2014/01/01";
		_s2.stock_detail_package_num = "12345";
		_s2.stock_detail_tonnage = "500";
		_s2.stock_detail_numberOfPackages = "1000";
		_datas.add(_s2);
		
		StockDetail _s3 = new StockDetail();
		_s3.stock_detail_into_date = "2014/01/01";
		_s3.stock_detail_package_num = "12345";
		_s3.stock_detail_tonnage = "500";
		_s3.stock_detail_numberOfPackages = "1000";
		_datas.add(_s3);
		
		StockDetail _s4 = new StockDetail();
		_s4.stock_detail_into_date = "2014/01/01";
		_s4.stock_detail_package_num = "12345";
		_s4.stock_detail_tonnage = "500";
		_s4.stock_detail_numberOfPackages = "1000";
		_datas.add(_s4);
		
		StockDetail _s5 = new StockDetail();
		_s5.stock_detail_into_date = "2014/01/01";
		_s5.stock_detail_package_num = "12345";
		_s5.stock_detail_tonnage = "500";
		_s5.stock_detail_numberOfPackages = "1000";
		_datas.add(_s5);
		
		StockWarehouseDetail_listview = (ListView) findViewById(R.id.StockWarehouseDetail_listview);
		
		MyAdapter _adapter = new MyAdapter(this);
		_adapter.initDatas(_datas);
		
		StockWarehouseDetail_listview.setAdapter(_adapter);
		
	}
	
	private class MyAdapter extends AbsBaseAdapter<StockDetail>{

		public MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(int position, View view, ViewGroup viewGroup) {
			ViewHolder _holder;
			if(null == view){
				_holder = new ViewHolder();
				view = getLayoutInflater().inflate(R.layout.stock_detail_listview_item, null);
				_holder.stock_detail_into_date = (TextView) view.findViewById(R.id.stock_detail_into_date);
				_holder.stock_detail_package_num = (TextView) view.findViewById(R.id.stock_detail_package_num);
				_holder.stock_detail_tonnage = (TextView) view.findViewById(R.id.stock_detail_tonnage);
				_holder.stock_detail_numberOfPackages = (TextView) view.findViewById(R.id.stock_detail_numberOfPackages);
				
			}else{
				_holder = (ViewHolder) view.getTag();
			}
			
			StockDetail _stock = (StockDetail) getItem(position);
			_holder.stock_detail_into_date.setText(_stock.stock_detail_into_date);
			_holder.stock_detail_package_num.setText(_stock.stock_detail_package_num);
			_holder.stock_detail_tonnage.setText(_stock.stock_detail_tonnage);
			_holder.stock_detail_numberOfPackages.setText(_stock.stock_detail_numberOfPackages);
			
			return view;
		}
		
	}
	
	private class ViewHolder {
		
		private TextView stock_detail_into_date;
		private TextView stock_detail_package_num;
		private TextView stock_detail_tonnage;
		private TextView stock_detail_numberOfPackages;
		
	}
	
	public class StockDetail{
		
		private String stock_detail_into_date;
		private String stock_detail_package_num;
		private String stock_detail_tonnage;
		private String stock_detail_numberOfPackages;
	}

}
