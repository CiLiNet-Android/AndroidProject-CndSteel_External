package com.cndsteel.payment.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.contract.bean.Contract_item;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.payment.adapter.PaymentDetailAsContractAdapter;

public class PaymentDetailAsContractActivity extends FrameActivity {

	private ListView paymentDetailAsContractlistView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appendFrameworkCenter(R.layout.payment_detail_as_contract_activity);

		setTopBarTitle(R.string.contract_Sum_terms_detail);

		init();
	}

	private void init() {

		initListView();

	}

	private void initListView() {

		//以下为初使界面测试...
		ArrayList<Contract_item> _datas = new ArrayList<Contract_item>();

		Contract_item _item = new Contract_item();
		_item.payDate = "2014/06/01";
		_item.payAmouontPaid = "236541";
		_datas.add(_item);

		Contract_item _item2 = new Contract_item();
		_item2.payDate = "2014/06/01";
		_item2.payAmouontPaid = "236541";
		_datas.add(_item2);

		paymentDetailAsContractlistView = (ListView) findViewById(R.id.paymentDetailAsContractlistView);
		paymentDetailAsContractlistView.setAdapter(new PaymentDetailAsContractAdapter(this, _datas));

	}

}