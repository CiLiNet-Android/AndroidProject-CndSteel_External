package com.cndsteel.payment.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.payment.adapter.PaymentViewPagerAdapter;

public class PaymentActivity extends FrameActivity implements OnClickListener, OnPageChangeListener {
	
	/** 按付款信息 **/
	private static final int PAGE_AS_INFORMATION = 0;
	/** 按合同款项 **/
	private static final int PAGE_AS_CONTRACT = 1;
	
	private ViewPager paymentViewPager;
	private ArrayList<View> viewList;
	private View asInformation;
	private View asContract;

	/**
	 * title
	 */
	private Button payment_btn_information;
	private Button payment_btn_contractFunds;
	private ImageButton payment_img_information;
	private ImageButton payment_img_contractFunds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appendFrameworkCenter(R.layout.activity_payment_main);

		setTopBarTitle(R.string.appModule_runningAccount);

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
		
		asInformation = findViewById(R.layout.activity_payment_viewpager_information);
		asContract = findViewById(R.layout.activity_payment_viewpager_contract);
		
		asInformation = inflateView(R.layout.activity_payment_viewpager_information);
		asContract = inflateView(R.layout.activity_payment_viewpager_contract);
		
		viewList = new ArrayList<View>();
		viewList.add(asInformation);
		viewList.add(asContract);
		
		
		paymentViewPager = (ViewPager) findViewById(R.id.paymentViewPager);
		paymentViewPager.setAdapter(new PaymentViewPagerAdapter(this,viewList,asInformation,asContract));
		paymentViewPager.setOnPageChangeListener(this);
		
	}

	private void initTab() {

		payment_btn_information = (Button) findViewById(R.id.payment_btn_information);
		payment_btn_information.setOnClickListener(this);
		
		payment_btn_contractFunds = (Button) findViewById(R.id.payment_btn_contractFunds);
		payment_btn_contractFunds.setOnClickListener(this);
		
		payment_img_information = (ImageButton) findViewById(R.id.payment_img_information);
		payment_img_contractFunds = (ImageButton) findViewById(R.id.payment_img_contractFunds);

	}

	/** 按付款信息的单击事件 **/
	public void invoiceNumChick() {

		payment_img_information.setVisibility(View.VISIBLE);
		payment_img_contractFunds.setVisibility(View.GONE);
		
		paymentViewPager.setCurrentItem(PAGE_AS_INFORMATION);

	}

	/** 按合同款项的单击事件 **/
	public void contractNumChick() {

		payment_img_information.setVisibility(View.GONE);
		payment_img_contractFunds.setVisibility(View.VISIBLE);
		
		paymentViewPager.setCurrentItem(PAGE_AS_CONTRACT);

	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		//按付款信息
		case R.id.payment_btn_information:
			
			invoiceNumChick();

			break;
		//按合同款项
		case R.id.payment_btn_contractFunds:
			
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
		
		case PAGE_AS_INFORMATION:
			payment_img_information.setVisibility(View.VISIBLE);
			payment_img_contractFunds.setVisibility(View.GONE);
			break;
		case PAGE_AS_CONTRACT:
			payment_img_information.setVisibility(View.GONE);
			payment_img_contractFunds.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
		
	}
}
