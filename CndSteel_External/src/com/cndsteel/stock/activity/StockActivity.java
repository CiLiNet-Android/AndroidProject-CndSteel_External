package com.cndsteel.stock.activity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.stock.fragments.StockFragmentContract;
import com.cndsteel.stock.fragments.StockFragmentGoods;
import com.cndsteel.stock.fragments.StockFragmentStock;

public class StockActivity extends FrameActivity implements OnClickListener {

	// private ArrayList<Fragment> fragmentList;

	private Button stock_lay_stock;
	private Button stock_lay_contract;
	private Button stock_lay_goods;
	
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	
	private Fragment stock;
	private Fragment contract;
	private Fragment goods;

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
		
		//fragmentManager = getFragmentManager();
		
		//stock = new StockFragmentStock();
		contract = new StockFragmentContract();
		goods = new StockFragmentGoods();
		
	}

	private void initView() {

		setTopBarTitle(R.string.appModule_stock);
		
		//fragmentTransaction = fragmentManager.beginTransaction();
		
		//fragmentTransaction.add(R.id.stock_fragment, stock);
		//fragmentTransaction.commit();

		stock_lay_stock = (Button) findViewById(R.id.stock_btn_stock);
		stock_lay_stock.setOnClickListener(this);

		stock_lay_contract = (Button) findViewById(R.id.stock_btn_contract);
		stock_lay_contract.setOnClickListener(this);

		stock_lay_goods = (Button) findViewById(R.id.stock_btn_goods);
		stock_lay_goods.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		
		//fragmentTransaction = fragmentManager.beginTransaction();
		
		ImageButton stock_img_stock = (ImageButton) findViewById(R.id.stock_img_stock);
		ImageButton stock_img_contract = (ImageButton) findViewById(R.id.stock_img_contract);
		ImageButton stock_img_goods = (ImageButton) findViewById(R.id.stock_img_goods);

		switch (view.getId()) {

		// 按库存
		case R.id.stock_btn_stock:
			
//			showToast("按库存");
			
			stock_img_stock.setVisibility(View.VISIBLE);
			stock_img_contract.setVisibility(View.GONE);
			stock_img_goods.setVisibility(View.GONE);
			
			//fragmentTransaction.replace(R.id.stock_fragment, new StockFragmentStock());
			//fragmentTransaction.commit();

			break;
		// 按合同
		case R.id.stock_btn_contract:
			
//			showToast("按合同");
			
			stock_img_stock.setVisibility(View.GONE);
			stock_img_contract.setVisibility(View.VISIBLE);
			stock_img_goods.setVisibility(View.GONE);
			
//			fragmentTransaction.replace(R.id.stock_fragment, contract);
//			fragmentTransaction.commit();

			break;
		// 按商品
		case R.id.stock_btn_goods:
			
//			showToast("按商品");
			
			stock_img_stock.setVisibility(View.GONE);
			stock_img_contract.setVisibility(View.GONE);
			stock_img_goods.setVisibility(View.VISIBLE);
			
//			fragmentTransaction.replace(R.id.stock_fragment, goods);
//			fragmentTransaction.commit();

			break;

		default:
			break;
		}

	}

}
