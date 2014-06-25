package com.cndsteel.shipmentIntent.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;

public class ShipmentIntentQueryResultActivity extends FrameActivity implements OnClickListener {
	
	private TextView txtV_intentBillNum;
	private TextView txtV_tonnage;
	private TextView txtV_numberOfPackages;
	private TextView txtV_warehouse;
	private TextView txtV_recordDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.appModule_shipmentIntent);
		appendFrameworkCenter(R.layout.pick_up_the_goods_intention_bill_query_result);
		
		init();
		
		setText();
		
		
		txtV_intentBillNum.setOnClickListener(this);
	}

	private void init() {
		
		initView();
		
	}

	private void initView() {
		txtV_intentBillNum = (TextView) findViewById(R.id.txtV_intentBillNum);
		txtV_tonnage = (TextView) findViewById(R.id.txtV_tonnage);
		txtV_numberOfPackages = (TextView) findViewById(R.id.txtV_numberOfPackages);
		txtV_warehouse = (TextView) findViewById(R.id.txtV_warehouse);
		txtV_recordDate = (TextView) findViewById(R.id.txtV_recordDate);
	}
	
	private void setText() {
		txtV_intentBillNum.setText("3256489756");
		txtV_tonnage.setText("1000吨");
		txtV_numberOfPackages.setText("500件");
		txtV_warehouse.setText("7号仓");
		txtV_recordDate.setText("2014/06/01");
	}

	@Override
	public void onClick(View view) {
		
		startActivity(ShipmentIntentBillDetailActivity.class);
		
	}
}
