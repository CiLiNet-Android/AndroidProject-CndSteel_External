package com.cndsteel.shipment.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.shipment.adapter.ShipmentViewpagerAdapter;

public class ShipmentActivity extends FrameActivity implements OnClickListener,OnPageChangeListener {

	/** 按库存 **/
	private static final int PAGE_AS_INVOICE_NUMBER = 0;
	/** 按合同 **/
	private static final int PAGE_AS_CONTRACT_NUMBER = 1;

	private ViewPager shipmentViewPager;
	private ArrayList<View> viewList;
	private View asInvoiceNum;
	private View asContractNum;

	/**
	 * title
	 */
	private Button shipment_btn_invoiceNum;
	private Button shipment_btn_contractNum;
	private ImageButton shipment_img_invoiceNum;
	private ImageButton shipment_img_contractNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appendFrameworkCenter(R.layout.activity_shipment_main);

		setTopBarTitle(R.string.appModule_shipment);

		init();

	}

	private void init() {

		initView();

	}

	private void initView() {

		initTab();
		
		initViewPager();

	}

	private void initViewPager() {
		
		asInvoiceNum = findViewById(R.layout.shipment_as_invoice_number);
		asContractNum = findViewById(R.layout.shipment_as_contract_number);
		
		asInvoiceNum = inflateView(R.layout.shipment_as_invoice_number);
		asContractNum = inflateView(R.layout.shipment_as_contract_number);
		
		viewList = new ArrayList<View>();
		viewList.add(asInvoiceNum);
		viewList.add(asContractNum);
		
		
		shipmentViewPager = (ViewPager) findViewById(R.id.shipmentViewPager);
		shipmentViewPager.setAdapter(new ShipmentViewpagerAdapter(this, viewList, asInvoiceNum, asContractNum));
		shipmentViewPager.setOnPageChangeListener(this);
		
	}

	private void initTab() {

		shipment_btn_invoiceNum = (Button) findViewById(R.id.shipment_btn_invoiceNum);
		shipment_btn_invoiceNum.setOnClickListener(this);
		
		shipment_btn_contractNum = (Button) findViewById(R.id.shipment_btn_contractNum);
		shipment_btn_contractNum.setOnClickListener(this);
		
		shipment_img_invoiceNum = (ImageButton) findViewById(R.id.shipment_img_invoiceNum);
		shipment_img_contractNum = (ImageButton) findViewById(R.id.shipment_img_contractNum);

	}

	/** 按发货单号的单击事件 **/
	public void invoiceNumChick() {

		shipment_img_invoiceNum.setVisibility(View.VISIBLE);
		shipment_img_contractNum.setVisibility(View.GONE);
		
		shipmentViewPager.setCurrentItem(PAGE_AS_INVOICE_NUMBER);

	}

	/** 按合同号的单击事件 **/
	public void contractNumChick() {

		shipment_img_invoiceNum.setVisibility(View.GONE);
		shipment_img_contractNum.setVisibility(View.VISIBLE);
		
		shipmentViewPager.setCurrentItem(PAGE_AS_CONTRACT_NUMBER);

	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		//按发货单号
		case R.id.shipment_btn_invoiceNum:
			
			invoiceNumChick();

			break;
		//按合同号
		case R.id.shipment_btn_contractNum:
			
			contractNumChick();

			break;
			
//		case value:
//
//			break;

		default:
			break;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int position) {
		
		switch (position) {
		
		case PAGE_AS_INVOICE_NUMBER:
			shipment_img_invoiceNum.setVisibility(View.VISIBLE);
			shipment_img_contractNum.setVisibility(View.GONE);
			break;
		case PAGE_AS_CONTRACT_NUMBER:
			shipment_img_invoiceNum.setVisibility(View.GONE);
			shipment_img_contractNum.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
		
	}

}
