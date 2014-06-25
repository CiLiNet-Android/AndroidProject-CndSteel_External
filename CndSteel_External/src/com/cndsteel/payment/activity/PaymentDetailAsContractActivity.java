package com.cndsteel.payment.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.contract.beans.ContractBean;
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
		ArrayList<ContractBean> _datas = new ArrayList<ContractBean>();

		ContractBean _item = new ContractBean();
//		_item.payDate = "2014/06/01";
//		_item.payAmouontPaid = "236541";
		_datas.add(_item);

		ContractBean _item2 = new ContractBean();
//		_item2.payDate = "2014/06/01";
//		_item2.payAmouontPaid = "236541";
		_datas.add(_item2);

		paymentDetailAsContractlistView = (ListView) findViewById(R.id.paymentDetailAsContractlistView);
		paymentDetailAsContractlistView.setAdapter(new PaymentDetailAsContractAdapter(this, _datas));

	}

}
