package com.cndsteel.payment.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.payment.adapter.PaymentInformationListAdapter;
import com.cndsteel.payment.bean.Payment;

public class PaymentAsInformationQueryResultActivity extends FrameActivity implements OnItemClickListener {
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.payment_information);
		
		appendFrameworkCenter(R.layout.activity_listview);
		
		init();
	}

	private void init() {
		
		initListView();
		
	}

	private void initListView() {
		
		ArrayList<Payment> _datas = new ArrayList<Payment>();
		_datas.add(new Payment("51DS2SDW212156SC", "2014/06", "现金", "50,000"));
		_datas.add(new Payment("51DS2SDW212156SC", "2014/06", "现金", "50,000"));
		_datas.add(new Payment("51DS2SDW212156SC", "2014/06", "现金", "50,000"));
		_datas.add(new Payment("51DS2SDW212156SC", "2014/06", "现金", "50,000"));
		_datas.add(new Payment("51DS2SDW212156SC", "2014/06", "现金", "50,000"));
		
		PaymentInformationListAdapter _adapter = new PaymentInformationListAdapter(this);
		_adapter.initDatas(_datas);
		
		listView = (ListView) findViewById(R.id.contract_listView);
		listView.setAdapter(_adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		startActivity(PaymentDetailAsInformetionActivity.class);
	}

}
