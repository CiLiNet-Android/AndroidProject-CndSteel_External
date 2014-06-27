package com.cndsteel.info.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.R.color;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.info.adapter.InfoAdapter;
import com.cndsteel.info.bean.Info;

public class InfoActivity extends FrameActivity implements OnItemClickListener {
	
	private ListView itemListView;
	private ArrayList<Info> viewList;
	
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
		
		viewList = new ArrayList<Info>();
		viewList.add(new Info("资讯头条:XXXXXXXXXXXXX", "2014/06/27", "张三", "10评论"));
		viewList.add(new Info("资讯头条:XXXXXXXXXXXXX", "2014/06/27", "张三", "10评论"));
		viewList.add(new Info("资讯头条:XXXXXXXXXXXXX", "2014/06/27", "张三", "10评论"));
		viewList.add(new Info("资讯头条:XXXXXXXXXXXXX", "2014/06/27", "张三", "10评论"));
		viewList.add(new Info("资讯头条:XXXXXXXXXXXXX", "2014/06/27", "张三", "10评论"));
		viewList.add(new Info("资讯头条:XXXXXXXXXXXXX", "2014/06/27", "张三", "10评论"));
		viewList.add(new Info("资讯头条:XXXXXXXXXXXXX", "2014/06/27", "张三", "10评论"));
		viewList.add(new Info("资讯头条:XXXXXXXXXXXXX", "2014/06/27", "张三", "10评论"));
		viewList.add(new Info("资讯头条:XXXXXXXXXXXXX", "2014/06/27", "张三", "10评论"));
		viewList.add(new Info("资讯头条:XXXXXXXXXXXXX", "2014/06/27", "张三", "10评论"));
		
		itemListView = (ListView) findViewById(R.id.contract_listView);
		itemListView.setAdapter(new InfoAdapter(this, viewList));
		itemListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		startActivity(InfoDetailActivity.class);
	}

}
