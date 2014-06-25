package com.cndsteel.stock.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.views.spinner.AbsSpinner.OnItemClickListener;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;

public class StockActivity extends FrameActivity implements OnClickListener,
		OnPageChangeListener, OnItemClickListener {

	/** 按库存 **/
	private static final int PAGE_ACCORDING_STOCK = 0;
	/** 按合同 **/
	private static final int PAGE_ACCORDING_CONTRACT = 1;
	/** 按商品 **/
	private static final int PAGE_ACCORDING_GOODS = 2;

	private ArrayList<View> viewList;
	private ViewPager stockViewPager;

	private View stock;
	private View contract;
	private View goods;

	/**
	 * title
	 */
	private Button stock_lay_stock;
	private Button stock_lay_contract;
	private Button stock_lay_goods;
	private ImageButton stock_img_stock;
	private ImageButton stock_img_contract;
	private ImageButton stock_img_goods;

	/**
	 * 按库存
	 */
	private Button btn_stock_accordingStock;

	private RelativeLayout lyot_stock_into_date_start;
	private TextView txtV_stock_into_date_start;

	private RelativeLayout lyot_stock_into_date_end;
	private TextView txtV_stock_into_date_end;

	private RelativeLayout lyot_stock_warehouse;
	private TextView txtV_stock_warehouse;

	/**
	 * 按合同
	 */
	private Button btn_StockFragmentContract;

	private RelativeLayout lyot_StockFragmentContract_into_date_start;
	private TextView txtV_StockFragmentContract_into_date_start;

	private RelativeLayout lyot_StockFragmentContract_into_date_end;
	private TextView txtV_StockFragmentContract_into_date_end;

	private RelativeLayout lyot_StockFragmentContract_warehouse;
	private TextView txtV_StockFragmentContract_warehouse;

	private RelativeLayout lyot_StockFragmentContract_contract_num;
	private TextView txtV_StockFragmentContract_contract_num;

	/**
	 * 按商品
	 */
	private Button btn_StockFragmentGoods;
	
	private RelativeLayout lyot_StockFragmentGoods_into_date_start;
	private TextView txtV_StockFragmentGoods_into_date_start;

	private RelativeLayout lyot_StockFragmentGoods_into_date_end;
	private TextView txtV_StockFragmentGoods_into_date_end;
	
	private RelativeLayout lyot_StockFragmentGoods_goodsName;
	private TextView txtV_StockFragmentGoods_goodsName;
	
	private RelativeLayout lyot_StockFragmentGoods_material;
	private TextView txtV_StockFragmentGoods_material;

	private RelativeLayout lyot_StockFragmentGoods_warehouse;
	private TextView txtV_StockFragmentGoods_warehouse;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appendFrameworkCenter(R.layout.activity_stock_main);

		init();

	}

	private void init() {

		initVariable();

		initView();

	}

	private void initVariable() {

		viewList = new ArrayList<View>();
		
	}

	private void initView() {

		setTopBarTitle(R.string.appModule_stock);

		initTitleBar();

		initViewPager();

		initButton();

	}

	private void initTitleBar() {

		stock_img_stock = (ImageButton) findViewById(R.id.stock_img_stock);
		stock_img_contract = (ImageButton) findViewById(R.id.stock_img_contract);
		stock_img_goods = (ImageButton) findViewById(R.id.stock_img_goods);

	}

	private void initButton() {

		stock_lay_stock = (Button) findViewById(R.id.stock_btn_stock);
		stock_lay_stock.setOnClickListener(this);

		stock_lay_contract = (Button) findViewById(R.id.stock_btn_contract);
		stock_lay_contract.setOnClickListener(this);

		stock_lay_goods = (Button) findViewById(R.id.stock_btn_goods);
		stock_lay_goods.setOnClickListener(this);

	}

	private void initViewPager() {

		stockViewPager = (ViewPager) findViewById(R.id.stockViewPager);

		stock = findViewById(R.layout.stock_according_stock);
		contract = findViewById(R.layout.stock_according_contract);
		goods = findViewById(R.layout.stock_according_goods);

		stock = inflateView(R.layout.stock_according_stock);
		contract = inflateView(R.layout.stock_according_contract);
		goods = inflateView(R.layout.stock_according_goods);

		viewList.add(stock);
		viewList.add(contract);
		viewList.add(goods);

		stockViewPager.setAdapter(new StovckViewPagerAdapter());
		stockViewPager.setCurrentItem(PAGE_ACCORDING_STOCK);
		stockViewPager.setOnPageChangeListener(this);

	}

	// 单击事件处理
	@Override
	public void onClick(View view) {

		CndSteelSpinner cndSteelSpinner = null;

		ArrayList<String> xx = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			xx.add("content_" + i);
		}

		switch (view.getId()) {

		// title--按库存
		case R.id.stock_btn_stock:

			// showToast("按库存");

			stock_img_stock.setVisibility(View.VISIBLE);
			stock_img_contract.setVisibility(View.GONE);
			stock_img_goods.setVisibility(View.GONE);

			stockViewPager.setCurrentItem(PAGE_ACCORDING_STOCK);

			break;
		// title--按合同
		case R.id.stock_btn_contract:

			// showToast("按合同");

			stock_img_stock.setVisibility(View.GONE);
			stock_img_contract.setVisibility(View.VISIBLE);
			stock_img_goods.setVisibility(View.GONE);

			stockViewPager.setCurrentItem(PAGE_ACCORDING_CONTRACT);

			break;
		// title--按商品
		case R.id.stock_btn_goods:

			// showToast("按商品");

			stock_img_stock.setVisibility(View.GONE);
			stock_img_contract.setVisibility(View.GONE);
			stock_img_goods.setVisibility(View.VISIBLE);

			stockViewPager.setCurrentItem(PAGE_ACCORDING_GOODS);

			break;

		// ///////////////// '按库存'页面下的单击事件处理 ///////////////////////////////

		// 查询按钮
		case R.id.btn_stock_accordingStock:

			startActivity(StockAccordingStockQueryResultActivity.class);

			break;

		// 入库日期从
		case R.id.lyot_stock_into_date_start:

			cndSteelSpinner = new CndSteelSpinner(this, xx,
					lyot_stock_into_date_start.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_stock_into_date_start, 0, -5);
			cndSteelSpinner.setOnItemClickListener(this);
			break;

		// 入库日期至
		case R.id.lyot_stock_into_date_end:

			cndSteelSpinner = new CndSteelSpinner(this, xx,
					lyot_stock_into_date_end.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_stock_into_date_end, 0, -5);
			cndSteelSpinner.setOnItemClickListener(this);

			break;

		// 仓库
		case R.id.lyot_stock_warehouse:

			cndSteelSpinner = new CndSteelSpinner(this, xx,
					lyot_stock_warehouse.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_stock_warehouse, 0, -5);
			cndSteelSpinner.setOnItemClickListener(this);

			break;

			// ///////////////// '按合同'页面下的单击事件处理 ///////////////////////////////
		// 查询按钮
		case R.id.btn_StockFragmentContract:

			startActivity(StockAccordingContractQueryResultActivity.class);

			break;

		// 入库日期从
		case R.id.lyot_StockFragmentContract_into_date_start:

			cndSteelSpinner = new CndSteelSpinner(this, xx,lyot_StockFragmentContract_into_date_start.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentContract_into_date_start, 0, -5);
			cndSteelSpinner.setOnItemClickListener(this);
			break;

		// 入库日期至
		case R.id.lyot_StockFragmentContract_into_date_end:

			cndSteelSpinner = new CndSteelSpinner(this, xx,lyot_StockFragmentContract_into_date_end.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentContract_into_date_end, 0, -5);
			cndSteelSpinner.setOnItemClickListener(this);

			break;

		// 合同号
		case R.id.lyot_StockFragmentContract_contract_num:

			cndSteelSpinner = new CndSteelSpinner(this, xx,lyot_StockFragmentContract_contract_num.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentContract_contract_num, 0, -5);
			cndSteelSpinner.setOnItemClickListener(this);

			break;

		// 仓库
		case R.id.lyot_StockFragmentContract_warehouse:

			cndSteelSpinner = new CndSteelSpinner(this, xx,
					lyot_StockFragmentContract_warehouse.getWidth());
			cndSteelSpinner.showAsDropDown(
					lyot_StockFragmentContract_warehouse, 0, -5);
			cndSteelSpinner.setOnItemClickListener(this);

			break;
			
		// ///////////////// '按商品'页面下的单击事件处理 ///////////////////////////////
			//查询按钮
		case R.id.btn_StockFragmentGoods:

			Intent _intent = new Intent(this,
					StockAccordingGoodsQueryResultActivity.class);

			this.startActivity(_intent);

			break;
			
		//入库日期从
		case R.id.lyot_StockFragmentGoods_into_date_start:
			
			cndSteelSpinner = new CndSteelSpinner(this, xx, lyot_StockFragmentGoods_into_date_start.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentGoods_into_date_start,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
			break;
			
		//入库日期至
		case R.id.lyot_StockFragmentGoods_into_date_end:
			
			cndSteelSpinner = new CndSteelSpinner(this, xx, lyot_StockFragmentGoods_into_date_end.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentGoods_into_date_end,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
			
			break;
			
		//品名
		case R.id.lyot_StockFragmentGoods_goodsName:
			
			cndSteelSpinner = new CndSteelSpinner(this, xx, lyot_StockFragmentGoods_goodsName.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentGoods_goodsName,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
			
			break;
			
		//材质
		case R.id.lyot_StockFragmentGoods_material:
			
			cndSteelSpinner = new CndSteelSpinner(this, xx, lyot_StockFragmentGoods_material.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentGoods_material,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
			
			break;
			
		//仓库
		case R.id.lyot_StockFragmentGoods_warehouse:
			
			cndSteelSpinner = new CndSteelSpinner(this, xx, lyot_StockFragmentGoods_warehouse.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentGoods_warehouse,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
	
			break;
		default:
			break;
		}

	}

	// 下拉item的事件处理
	@Override
	public void onItemClick(int position) {

	}

	// ///////////// adapter /////////////////////

	private class StovckViewPagerAdapter extends PagerAdapter {

		/** 所要显示的页面数 **/
		@Override
		public int getCount() {
			return viewList.size();
		}

		/** 判断是否由对象生成内容 **/
		@Override
		public boolean isViewFromObject(View view, Object object) {

			return view == object;

		}

		/** 返回当前位置上item的数据 **/
		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			container.addView(viewList.get(position), null);

			switch (position) {

			// 按库存
			case PAGE_ACCORDING_STOCK:

				btn_stock_accordingStock = (Button) stock
						.findViewById(R.id.btn_stock_accordingStock);
				btn_stock_accordingStock.setOnClickListener(StockActivity.this);

				lyot_stock_into_date_start = (RelativeLayout) stock
						.findViewById(R.id.lyot_stock_into_date_start);
				txtV_stock_into_date_start = (TextView) stock
						.findViewById(R.id.txtV_stock_into_date_start);
				lyot_stock_into_date_start
						.setOnClickListener(StockActivity.this);

				lyot_stock_into_date_end = (RelativeLayout) stock
						.findViewById(R.id.lyot_stock_into_date_end);
				txtV_stock_into_date_end = (TextView) stock
						.findViewById(R.id.txtV_stock_into_date_end);
				lyot_stock_into_date_end.setOnClickListener(StockActivity.this);

				lyot_stock_warehouse = (RelativeLayout) stock
						.findViewById(R.id.lyot_stock_warehouse);
				txtV_stock_warehouse = (TextView) stock
						.findViewById(R.id.txtV_stock_warehouse);
				lyot_stock_warehouse.setOnClickListener(StockActivity.this);

				break;
			// 按合同
			case PAGE_ACCORDING_CONTRACT:
				
				btn_StockFragmentContract = (Button) container.findViewById(R.id.btn_StockFragmentContract);
				btn_StockFragmentContract.setOnClickListener(StockActivity.this);

				lyot_StockFragmentContract_into_date_start = (RelativeLayout) container.findViewById(R.id.lyot_StockFragmentContract_into_date_start);
				lyot_StockFragmentContract_into_date_start.setOnClickListener(StockActivity.this);
				txtV_StockFragmentContract_into_date_start = (TextView) container.findViewById(R.id.txtV_StockFragmentContract_into_date_start);
				
				lyot_StockFragmentContract_into_date_end = (RelativeLayout) container.findViewById(R.id.lyot_StockFragmentContract_into_date_end);
				lyot_StockFragmentContract_into_date_end.setOnClickListener(StockActivity.this);
				txtV_StockFragmentContract_into_date_end = (TextView) container.findViewById(R.id.txtV_StockFragmentContract_into_date_end);
				
				lyot_StockFragmentContract_contract_num = (RelativeLayout) container.findViewById(R.id.lyot_StockFragmentContract_contract_num);
				lyot_StockFragmentContract_contract_num.setOnClickListener(StockActivity.this);
				txtV_StockFragmentContract_contract_num = (TextView) container.findViewById(R.id.txtV_StockFragmentContract_contract_num);

				lyot_StockFragmentContract_warehouse = (RelativeLayout) container.findViewById(R.id.lyot_StockFragmentContract_warehouse);
				lyot_StockFragmentContract_warehouse.setOnClickListener(StockActivity.this);
				txtV_StockFragmentContract_warehouse = (TextView) container.findViewById(R.id.txtV_StockFragmentContract_warehouse);
				
				
				
				break;

			// 按商品
			case PAGE_ACCORDING_GOODS:
				
				btn_StockFragmentGoods = (Button) goods.findViewById(R.id.btn_StockFragmentGoods);
				btn_StockFragmentGoods.setOnClickListener(StockActivity.this);
				
				lyot_StockFragmentGoods_into_date_start = (RelativeLayout) goods.findViewById(R.id.lyot_StockFragmentGoods_into_date_start);
				lyot_StockFragmentGoods_into_date_start.setOnClickListener(StockActivity.this);
				txtV_StockFragmentGoods_into_date_start = (TextView) goods.findViewById(R.id.txtV_StockFragmentGoods_into_date_start);

				lyot_StockFragmentGoods_into_date_end = (RelativeLayout) goods.findViewById(R.id.lyot_StockFragmentGoods_into_date_end);
				lyot_StockFragmentGoods_into_date_end.setOnClickListener(StockActivity.this);
				txtV_StockFragmentGoods_into_date_end = (TextView) goods.findViewById(R.id.txtV_StockFragmentGoods_into_date_end);

				lyot_StockFragmentGoods_warehouse = (RelativeLayout) goods.findViewById(R.id.lyot_StockFragmentGoods_warehouse);
				lyot_StockFragmentGoods_warehouse.setOnClickListener(StockActivity.this);
				txtV_StockFragmentGoods_warehouse = (TextView) goods.findViewById(R.id.txtV_StockFragmentGoods_warehouse);
				
				lyot_StockFragmentGoods_goodsName = (RelativeLayout) goods.findViewById(R.id.lyot_StockFragmentGoods_goodsName);
				lyot_StockFragmentGoods_goodsName.setOnClickListener(StockActivity.this);
				txtV_StockFragmentGoods_goodsName = (TextView) goods.findViewById(R.id.txtV_StockFragmentGoods_goodsName);
				
				lyot_StockFragmentGoods_material = (RelativeLayout) goods.findViewById(R.id.lyot_StockFragmentGoods_material);
				txtV_StockFragmentGoods_material = (TextView) goods.findViewById(R.id.txtV_StockFragmentGoods_material);
				lyot_StockFragmentGoods_material.setOnClickListener(StockActivity.this);				
				
				break;

			default:
				break;
			}

			return viewList.get(position);
		}

		/** 删除当前位置上的页面 **/
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(viewList.get(position));
		}

	}

	// /////////////////////////////////////////////
	/**
	 * ViewPager.setOnPageChangeListener的事件处理
	 **/
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {

		switch (position) {
		// title--按库存
		case PAGE_ACCORDING_STOCK:
			stock_img_stock.setVisibility(View.VISIBLE);
			stock_img_contract.setVisibility(View.GONE);
			stock_img_goods.setVisibility(View.GONE);
			break;
		// title--按合同
		case PAGE_ACCORDING_CONTRACT:
			stock_img_stock.setVisibility(View.GONE);
			stock_img_contract.setVisibility(View.VISIBLE);
			stock_img_goods.setVisibility(View.GONE);
			break;
		// title--按商品
		case PAGE_ACCORDING_GOODS:
			stock_img_stock.setVisibility(View.GONE);
			stock_img_contract.setVisibility(View.GONE);
			stock_img_goods.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}

	}

}
