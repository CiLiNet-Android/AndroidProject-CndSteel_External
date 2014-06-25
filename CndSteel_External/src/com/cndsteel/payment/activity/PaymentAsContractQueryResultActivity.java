package com.cndsteel.payment.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.contract.beans.ContractBean;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.payment.adapter.PaymentAsContractQueryResultAdapter;

public class PaymentAsContractQueryResultActivity extends FrameActivity implements OnItemClickListener{
	
	private ListView contract_listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_listview);
		
		initView();
	}

	private void initView() {
		
		setTopBarTitle(R.string.contract_Sum_terms);
		
		initListView();
		
	}

	private void initListView() {
		
		//以下为初使界面测试...
		ArrayList<ContractBean> _datas = new ArrayList<ContractBean>();
		
		ContractBean _item = new ContractBean();
//		_item.num = "63254102178956";
//		_item.amountPaid = "524103";
//		_item.unpaidAmount = "236541";
		
		_datas.add(_item);
		
		ContractBean _item2 = new ContractBean();
//		_item2.num = "63254102178956";
//		_item2.amountPaid = "524103";
//		_item2.unpaidAmount = "236541";
		
		_datas.add(_item2);
		
		contract_listView = (ListView) findViewById(R.id.contract_listView);
		
		contract_listView.setAdapter(new PaymentAsContractQueryResultAdapter(getContext(), _datas));
		
		contract_listView.setOnItemClickListener(this);
		
	}
	
	
	/** ListView的item的事件处理 **/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		//启动合同款项明细
		startActivity(PaymentDetailAsContractActivity.class);
		
	}
}
