package com.cndsteel.shipmentIntent.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;

public class ShipmentIntentActivity extends FrameActivity implements OnClickListener {
	
	private ViewPager shipmentIntentViewPager;
	
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
		
		initTab();
		
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
		
		//待提货意向
		case R.id.btn_toPickUpTheGoodsIntention:
			img_toPickUpTheGoodsIntention.setVisibility(View.VISIBLE);
			img_pickUpTheGoodsIntentionBill.setVisibility(View.GONE);
			break;
		//提货意向单	
		case R.id.btn_pickUpTheGoodsIntentionBill:
			img_toPickUpTheGoodsIntention.setVisibility(View.GONE);
			img_pickUpTheGoodsIntentionBill.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}
