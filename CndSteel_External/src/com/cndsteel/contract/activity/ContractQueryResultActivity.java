package com.cndsteel.contract.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.contract.adapter.ContractQueryResultListAdapter;
import com.cndsteel.contract.bean.Contract_item;
import com.cndsteel.framework.activity.FrameActivity;

public class ContractQueryResultActivity extends FrameActivity implements OnItemClickListener{
	
	private ListView contract_listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_contract_listview);
		
		setTopBarTitle(R.string.appModule_contract);
		
		init();
	}

	private void init() {
		
		initView();
		
	}

	private void initView() {
		
		
		//以下为初使界面测试...
		ArrayList<Contract_item> _itemList = new ArrayList<Contract_item>();
		
		Contract_item _item = new Contract_item();
		_item.num = "12345678901234";
		_item.isEnd = " 已完结";
		_item.date = "2014/06";
		_item.goodsName = "管坯";
		_item.tonnage = "321654";
		_item.PickUpGoods = "123456/321654";
		
		_itemList.add(_item);
		
//		Contract_item _item2 = new Contract_item();
//		_item2.num = "65489632478523";
//		_item2.isEnd = " 已完结";
//		_item2.date = "2014/06";
//		_item2.goodsName = "管坯";
//		_item2.tonnage = "321654";
//		_item2.PickUpGoods = "123456/321654";
//		
//		_itemList.add(_item2);
		
		
		contract_listView = (ListView) findViewById(R.id.contract_listView);
		
		contract_listView.setAdapter(new ContractQueryResultListAdapter(getContext(),_itemList));
		
		contract_listView.setOnItemClickListener(this);
	}

	/** ListView的item的事件处理 **/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		//启动合同款项
		startActivity(ContractTermsActivity.class);
		
	}
}
