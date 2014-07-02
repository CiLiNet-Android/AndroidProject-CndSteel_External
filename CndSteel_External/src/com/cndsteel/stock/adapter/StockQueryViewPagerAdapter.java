package com.cndsteel.stock.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;

public class StockQueryViewPagerAdapter extends PagerAdapter {

	private ArrayList<View> mViewPages;
	
	public StockQueryViewPagerAdapter(Context context){
	}
	
	public void initViewPages(ArrayList<View> viewPages){
		mViewPages = viewPages;
	}
	
	/** 所要显示的页面数 **/
	@Override
	public int getCount() {
		return mViewPages.size();
	}

	/** 判断是否由对象生成内容 **/
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;

	}

	/** 返回当前位置上item的数据 **/
	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		container.addView(mViewPages.get(position), null);

//		switch (position) {

//		// 按库存
//		case ACCORDING_STOCK_PAGE_INDEX:
//
//			btn_stock_accordingStock = (Button) stock
//					.findViewById(R.id.btn_stock_accordingStock);
//			btn_stock_accordingStock.setOnClickListener(StockQueryActivity.this);
//
//			lyot_stock_into_date_start = (RelativeLayout) stock
//					.findViewById(R.id.lyot_stock_into_date_start);
//			txtV_stock_into_date_start = (TextView) stock
//					.findViewById(R.id.txtV_stock_into_date_start);
//			lyot_stock_into_date_start
//					.setOnClickListener(StockQueryActivity.this);
//
//			lyot_stock_into_date_end = (RelativeLayout) stock
//					.findViewById(R.id.lyot_stock_into_date_end);
//			txtV_stock_into_date_end = (TextView) stock
//					.findViewById(R.id.txtV_stock_into_date_end);
//			lyot_stock_into_date_end.setOnClickListener(StockQueryActivity.this);
//
//			lyot_stock_warehouse = (RelativeLayout) stock
//					.findViewById(R.id.lyot_stock_warehouse);
//			txtV_stock_warehouse = (TextView) stock
//					.findViewById(R.id.txtV_stock_warehouse);
//			lyot_stock_warehouse.setOnClickListener(StockQueryActivity.this);
//
//			break;
//		// 按合同
//		case ACCORDING_CONTRACT_PAGE_INDEX:
//			
//			btn_StockFragmentContract = (Button) container.findViewById(R.id.btn_StockFragmentContract);
//			btn_StockFragmentContract.setOnClickListener(StockQueryActivity.this);
//
//			lyot_StockFragmentContract_into_date_start = (RelativeLayout) container.findViewById(R.id.lyot_StockFragmentContract_into_date_start);
//			lyot_StockFragmentContract_into_date_start.setOnClickListener(StockQueryActivity.this);
//			txtV_StockFragmentContract_into_date_start = (TextView) container.findViewById(R.id.txtV_StockFragmentContract_into_date_start);
//			
//			lyot_StockFragmentContract_into_date_end = (RelativeLayout) container.findViewById(R.id.lyot_StockFragmentContract_into_date_end);
//			lyot_StockFragmentContract_into_date_end.setOnClickListener(StockQueryActivity.this);
//			txtV_StockFragmentContract_into_date_end = (TextView) container.findViewById(R.id.txtV_StockFragmentContract_into_date_end);
//			
//			lyot_StockFragmentContract_contract_num = (RelativeLayout) container.findViewById(R.id.lyot_StockFragmentContract_contract_num);
//			lyot_StockFragmentContract_contract_num.setOnClickListener(StockQueryActivity.this);
//			txtV_StockFragmentContract_contract_num = (TextView) container.findViewById(R.id.txtV_StockFragmentContract_contract_num);
//
//			lyot_StockFragmentContract_warehouse = (RelativeLayout) container.findViewById(R.id.lyot_StockFragmentContract_warehouse);
//			lyot_StockFragmentContract_warehouse.setOnClickListener(StockQueryActivity.this);
//			txtV_StockFragmentContract_warehouse = (TextView) container.findViewById(R.id.txtV_StockFragmentContract_warehouse);
//			
//			
//			
//			break;
//
//		// 按商品
//		case ACCORDING_GOODS_PAGE_INDEX:
//			
//			btn_StockFragmentGoods = (Button) goods.findViewById(R.id.btn_StockFragmentGoods);
//			btn_StockFragmentGoods.setOnClickListener(StockQueryActivity.this);
//			
//			lyot_StockFragmentGoods_into_date_start = (RelativeLayout) goods.findViewById(R.id.lyot_StockFragmentGoods_into_date_start);
//			lyot_StockFragmentGoods_into_date_start.setOnClickListener(StockQueryActivity.this);
//			txtV_StockFragmentGoods_into_date_start = (TextView) goods.findViewById(R.id.txtV_StockFragmentGoods_into_date_start);
//
//			lyot_StockFragmentGoods_into_date_end = (RelativeLayout) goods.findViewById(R.id.lyot_StockFragmentGoods_into_date_end);
//			lyot_StockFragmentGoods_into_date_end.setOnClickListener(StockQueryActivity.this);
//			txtV_StockFragmentGoods_into_date_end = (TextView) goods.findViewById(R.id.txtV_StockFragmentGoods_into_date_end);
//
//			lyot_StockFragmentGoods_warehouse = (RelativeLayout) goods.findViewById(R.id.lyot_StockFragmentGoods_warehouse);
//			lyot_StockFragmentGoods_warehouse.setOnClickListener(StockQueryActivity.this);
//			txtV_StockFragmentGoods_warehouse = (TextView) goods.findViewById(R.id.txtV_StockFragmentGoods_warehouse);
//			
//			lyot_StockFragmentGoods_goodsName = (RelativeLayout) goods.findViewById(R.id.lyot_StockFragmentGoods_goodsName);
//			lyot_StockFragmentGoods_goodsName.setOnClickListener(StockQueryActivity.this);
//			txtV_StockFragmentGoods_goodsName = (TextView) goods.findViewById(R.id.txtV_StockFragmentGoods_goodsName);
//			
//			lyot_StockFragmentGoods_material = (RelativeLayout) goods.findViewById(R.id.lyot_StockFragmentGoods_material);
//			txtV_StockFragmentGoods_material = (TextView) goods.findViewById(R.id.txtV_StockFragmentGoods_material);
//			lyot_StockFragmentGoods_material.setOnClickListener(StockQueryActivity.this);				
//			
//			break;
//
//		default:
//			break;
//		}

		return mViewPages.get(position);
	}

	/** 删除当前位置上的页面 **/
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mViewPages.get(position));
	}


}
