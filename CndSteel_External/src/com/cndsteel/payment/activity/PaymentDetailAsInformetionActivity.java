package com.cndsteel.payment.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.payment.adapter.PaymentAsInformationAdapter;
import com.cndsteel.payment.bean.Payment;

public class PaymentDetailAsInformetionActivity extends FrameActivity {
	
	private ListView paymentDetailAsContractList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.paymentDetail);
		
		appendFrameworkCenter(R.layout.payment_detail_as_information);
		
		init();
		
	}

	private void init() {
		
		initView();
		
	}

	private void initView() {
		initListView();
	}

	private void initListView() {
		
		ArrayList<Payment> _datas = new ArrayList<Payment>();
		_datas.add(new Payment("235468795132", "2014/06", "123,456,789", "货款", null));
		_datas.add(new Payment("235468795132", "2014/06", "123,456,789", "货款", null));
		_datas.add(new Payment("235468795132", "2014/06", "123,456,789", "货款", null));
		_datas.add(new Payment("235468795132", "2014/06", "123,456,789", "货款", null));
		_datas.add(new Payment("235468795132", "2014/06", "123,456,789", "货款", null));		

		PaymentAsInformationAdapter _adapter = new PaymentAsInformationAdapter(this);
		_adapter.initDatas(_datas);
		
		paymentDetailAsContractList = (ListView) findViewById(R.id.PaymentDetailAsContractList);	
		paymentDetailAsContractList.setAdapter(_adapter);
	}
}
