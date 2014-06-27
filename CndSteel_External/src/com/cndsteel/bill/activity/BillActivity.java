package com.cndsteel.bill.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.cndsteel.R;
import com.cndsteel.bill.adapter.BillViewPagerAdapter;
import com.cndsteel.framework.activity.FrameActivity;

public class BillActivity extends FrameActivity implements OnClickListener, OnPageChangeListener {
	
	public static final int BILL_PAGE_CONTRACT_INVOICE = 0;
	public static final int BILL_PAGE_CONTRACT_TRACKING = 1;
	
	private ViewPager billViewPager;
	private ArrayList<View> viewList;
	
	private View pageContractInvoice;
	private View pageContractTracking;
	
	/**
	 * tab
	 */
	private Button btn_TheContractOfMakeOutAnInvoice;
	private Button btn_ContractTracking;
	private ImageButton img_TheContractOfMakeOutAnInvoice;
	private ImageButton img_ContractTracking;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.appModule_bill);
		
		appendFrameworkCenter(R.layout.activity_bill_main);
		
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
		
		pageContractInvoice = findViewById(R.layout.bill_contract_invoice);
		pageContractTracking = findViewById(R.layout.bill_contract_tracking);
		
		pageContractInvoice = inflateView(R.layout.bill_contract_invoice);
		pageContractTracking = inflateView(R.layout.bill_contract_tracking);
		
		viewList = new ArrayList<View>();
		viewList.add(pageContractInvoice);
		viewList.add(pageContractTracking);
		
		billViewPager = (ViewPager) findViewById(R.id.billViewPager);
		billViewPager.setAdapter(new BillViewPagerAdapter(this,viewList,pageContractInvoice,pageContractTracking));
		billViewPager.setOnPageChangeListener(this);
		
	}
	
	private void initTab() {
		btn_TheContractOfMakeOutAnInvoice = (Button) findViewById(R.id.btn_TheContractOfMakeOutAnInvoice);
		btn_TheContractOfMakeOutAnInvoice.setOnClickListener(this);
		btn_ContractTracking = (Button) findViewById(R.id.btn_ContractTracking);
		btn_ContractTracking.setOnClickListener(this);
		img_TheContractOfMakeOutAnInvoice = (ImageButton) findViewById(R.id.img_TheContractOfMakeOutAnInvoice);
		img_ContractTracking = (ImageButton) findViewById(R.id.img_ContractTracking);
	}
	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		
		//Tab的合同开票
		case R.id.btn_TheContractOfMakeOutAnInvoice:
			img_TheContractOfMakeOutAnInvoice.setVisibility(View.VISIBLE);
			img_ContractTracking.setVisibility(View.GONE);
			billViewPager.setCurrentItem(BILL_PAGE_CONTRACT_INVOICE);
			break;
		//Tab的合同正本跟踪
		case R.id.btn_ContractTracking:
			img_TheContractOfMakeOutAnInvoice.setVisibility(View.GONE);
			img_ContractTracking.setVisibility(View.VISIBLE);
			billViewPager.setCurrentItem(BILL_PAGE_CONTRACT_TRACKING);
			break;

		default:
			break;
		}
		
	}
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
		
		//Tab的合同开票
		case BILL_PAGE_CONTRACT_INVOICE:
			img_TheContractOfMakeOutAnInvoice.setVisibility(View.VISIBLE);
			img_ContractTracking.setVisibility(View.GONE);
			
			break;
		//Tab的合同正本跟踪
		case BILL_PAGE_CONTRACT_TRACKING:
			img_TheContractOfMakeOutAnInvoice.setVisibility(View.GONE);
			img_ContractTracking.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
		
	}
}
