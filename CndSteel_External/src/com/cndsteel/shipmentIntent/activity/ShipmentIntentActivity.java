package com.cndsteel.shipmentIntent.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.shipmentIntent.adapter.ShipmentIntentViewPagerAdapter;

public class ShipmentIntentActivity extends FrameActivity implements
		OnClickListener, OnPageChangeListener {
	
	public static final int PAGE_TO_INTENT = 0;
	public static final int PAGE_INTENT_BILL = 1;

	private ViewPager shipmentIntentViewPager;

	private ImageButton imgBtn_topRight; // 顶部右边的购物车;
	
	private View toPickUpTheGoodsIntention;
	private View pickUpTheGoodsIntentionBill;
	
	private ArrayList<View> viewList;

	/**
	 * title
	 */
	private Button btn_toPickUpTheGoodsIntention;
	private Button btn_pickUpTheGoodsIntentionBill;
	private ImageButton img_toPickUpTheGoodsIntention;
	private ImageButton img_pickUpTheGoodsIntentionBill;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTopBarTitle(R.string.appModule_shipmentIntent);

		appendFrameworkCenter(R.layout.activity_shipmentintent_main);

		init();
	}

	private void init() {
		initView();
	}

	private void initView() {

		setTopRightImageButton(R.drawable.shopping_cart_red);

		initTab();
		
		initViewPager();

	}

	private void initViewPager() {
		
		toPickUpTheGoodsIntention = findViewById(R.layout.to_pick_up_the_goods_intent);
		pickUpTheGoodsIntentionBill = findViewById(R.layout.pick_up_the_goods_intention_bill);
		
		toPickUpTheGoodsIntention = inflateView(R.layout.to_pick_up_the_goods_intent);
		pickUpTheGoodsIntentionBill = inflateView(R.layout.pick_up_the_goods_intention_bill);
		
		viewList = new ArrayList<View>();
		viewList.add(toPickUpTheGoodsIntention);
		viewList.add(pickUpTheGoodsIntentionBill);
		
		shipmentIntentViewPager = (ViewPager) findViewById(R.id.shipmentIntentViewPager);
		shipmentIntentViewPager.setAdapter(new ShipmentIntentViewPagerAdapter(this,viewList,toPickUpTheGoodsIntention,pickUpTheGoodsIntentionBill));
		shipmentIntentViewPager.setOnPageChangeListener(this);
		
	}

	/** 顶部右边的购物车 **/
	private void setTopRightImageButton(int resId) {

		imgBtn_topRight = (ImageButton) findViewById(R.id.imgBtn_topRight);
		imgBtn_topRight.setVisibility(View.VISIBLE);
		imgBtn_topRight.setBackgroundResource(resId);

	}

	private void initTab() {
		btn_toPickUpTheGoodsIntention = (Button) findViewById(R.id.btn_toPickUpTheGoodsIntention);
		btn_toPickUpTheGoodsIntention.setOnClickListener(this);
		btn_pickUpTheGoodsIntentionBill = (Button) findViewById(R.id.btn_pickUpTheGoodsIntentionBill);
		btn_pickUpTheGoodsIntentionBill.setOnClickListener(this);

		img_toPickUpTheGoodsIntention = (ImageButton) findViewById(R.id.img_toPickUpTheGoodsIntention);
		img_pickUpTheGoodsIntentionBill = (ImageButton) findViewById(R.id.img_pickUpTheGoodsIntentionBill);

	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

		// 待提货意向
		case R.id.btn_toPickUpTheGoodsIntention:
			
			setTopRightImageButton(R.drawable.shopping_cart_red);
			img_toPickUpTheGoodsIntention.setVisibility(View.VISIBLE);
			img_pickUpTheGoodsIntentionBill.setVisibility(View.GONE);
			shipmentIntentViewPager.setCurrentItem(PAGE_TO_INTENT);
			
			break;
			
		// 提货意向单
		case R.id.btn_pickUpTheGoodsIntentionBill:
			
			setTopRightImageButton(R.drawable.shopping_cart);
			img_toPickUpTheGoodsIntention.setVisibility(View.GONE);
			img_pickUpTheGoodsIntentionBill.setVisibility(View.VISIBLE);
			shipmentIntentViewPager.setCurrentItem(PAGE_INTENT_BILL);
			
			break;

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
		
		
		case PAGE_TO_INTENT:
			
			setTopRightImageButton(R.drawable.shopping_cart_red);
			img_toPickUpTheGoodsIntention.setVisibility(View.VISIBLE);
			img_pickUpTheGoodsIntentionBill.setVisibility(View.GONE);
			
			break;
			
		case PAGE_INTENT_BILL:
			
			setTopRightImageButton(R.drawable.shopping_cart);
			img_toPickUpTheGoodsIntention.setVisibility(View.GONE);
			img_pickUpTheGoodsIntentionBill.setVisibility(View.VISIBLE);
			
			break;

		default:
			break;
		}
		
	}
}
