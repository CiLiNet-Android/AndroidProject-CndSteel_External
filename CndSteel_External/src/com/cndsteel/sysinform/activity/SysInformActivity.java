package com.cndsteel.sysinform.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.info.activity.InfoDetailActivity;
import com.cndsteel.sysinform.adapter.SysInformAdapter;
import com.cndsteel.sysinform.bean.SysInform;

public class SysInformActivity extends FrameActivity implements
		OnItemClickListener {

	private ListView itemListView;
	private ArrayList<SysInform> viewList;

	private ImageButton imgBtn_topRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTopBarTitle(R.string.appModule_info);

		appendFrameworkCenter(R.layout.activity_listview);

		init();

	}

	private void init() {

		initView();

	}

	private void initView() {

		initTopImageButton();
		initListView();

	}

	private void initTopImageButton() {
		imgBtn_topRight = (ImageButton) findViewById(R.id.imgBtn_topRight);
		imgBtn_topRight.setVisibility(View.VISIBLE);
		imgBtn_topRight.setImageResource(R.drawable.list);
	}

	private void initListView() {

		viewList = new ArrayList<SysInform>();
		viewList.add(new SysInform("系统通知:XXXXXXXXXXXXX", "2014/06/27"));
		viewList.add(new SysInform("系统通知:XXXXXXXXXXXXX", "2014/06/27"));
		viewList.add(new SysInform("系统通知:XXXXXXXXXXXXX", "2014/06/27"));
		viewList.add(new SysInform("系统通知:XXXXXXXXXXXXX", "2014/06/27"));
		viewList.add(new SysInform("系统通知:XXXXXXXXXXXXX", "2014/06/27"));
		viewList.add(new SysInform("系统通知:XXXXXXXXXXXXX", "2014/06/27"));
		viewList.add(new SysInform("系统通知:XXXXXXXXXXXXX", "2014/06/27"));
		viewList.add(new SysInform("系统通知:XXXXXXXXXXXXX", "2014/06/27"));
		viewList.add(new SysInform("系统通知:XXXXXXXXXXXXX", "2014/06/27"));
		viewList.add(new SysInform("系统通知:XXXXXXXXXXXXX", "2014/06/27"));

		itemListView = (ListView) findViewById(R.id.contract_listView);
		itemListView.setAdapter(new SysInformAdapter(this, viewList));
		itemListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		startActivity(SysInformDetailActivity.class);
	}

}
