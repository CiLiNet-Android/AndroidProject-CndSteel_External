package com.cndsteel.info.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.utils.DateUtils;
import com.cndsteel.info.adapter.InfoDetailAdapter;
import com.cndsteel.info.bean.Info;

public class InfoDetailActivity extends FrameActivity implements OnClickListener {
	
	private ListView infoDetailListView;
	private ArrayList<Info> itemList;
	
	
	private TextView infoDetailTitle;
	private TextView infoDetailCategoryValue;
	private TextView infoDetailTimeValue;
	private TextView infoDetailIssuerValue;
	private TextView infoDetailContentValue;
	private EditText edt_infoDetailChat;
	private Button btn_infoDetailSend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.InfoDetail);
		
		appendFrameworkCenter(R.layout.activity_info_detail);
		
		init();
		
	}
	
	private void init() {

		initView();

	}

	private void initView() {

		infoDetailTitle = (TextView) findViewById(R.id.infoDetailTitle);
		infoDetailCategoryValue = (TextView) findViewById(R.id.infoDetailCategoryValue);
		infoDetailTimeValue = (TextView) findViewById(R.id.infoDetailTimeValue);
		infoDetailIssuerValue = (TextView) findViewById(R.id.infoDetailIssuerValue);
		infoDetailContentValue = (TextView) findViewById(R.id.infoDetailContentValue);
		
		edt_infoDetailChat = (EditText) findViewById(R.id.edt_infoDetailChat);
		
		btn_infoDetailSend = (Button) findViewById(R.id.btn_infoDetailSend);
		btn_infoDetailSend.setOnClickListener(this);
		
		itemList = new ArrayList<Info>();
		
		infoDetailListView = (ListView) findViewById(R.id.infoDetailListView);
		
		itemList.add(new Info("用户名", DateUtils.getCurrentDate(), "XXXXXXXX"));
		itemList.add(new Info("用户名", DateUtils.getCurrentDate(), "XXXXXXXX"));
		itemList.add(new Info("用户名", DateUtils.getCurrentDate(), "XXXXXXXX"));
		itemList.add(new Info("用户名", DateUtils.getCurrentDate(), "XXXXXXXX"));
		itemList.add(new Info("用户名", DateUtils.getCurrentDate(), "XXXXXXXX"));
		itemList.add(new Info("用户名", DateUtils.getCurrentDate(), "XXXXXXXX"));
		
		infoDetailListView.setAdapter(new InfoDetailAdapter(this, itemList));
		
		setListViewHeight(infoDetailListView);
		
	}

	@Override
	public void onClick(View view) {
		
		itemList.add(new Info("用户名", DateUtils.getCurrentDate(), edt_infoDetailChat.getText().toString()));
		infoDetailListView.setAdapter(new InfoDetailAdapter(this, itemList));
		setListViewHeight(infoDetailListView);
		
		edt_infoDetailChat.setText(null);
	}
	
	/** 该方法用来解决ScrollView嵌套ListView产生的不能正确显示ListView高度的冲突 **/
	public void setListViewHeight(ListView listView) {   
        
        // 获取ListView对应的Adapter   
       
        ListAdapter listAdapter = listView.getAdapter();   
       
        if (listAdapter == null) {   
            return;   
        }   
        int totalHeight = 0;   
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目   
            View listItem = listAdapter.getView(i, null, listView);   
            listItem.measure(0, 0); // 计算子项View 的宽高   
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度   
        }   
       
        ViewGroup.LayoutParams params = listView.getLayoutParams();   
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));   
        listView.setLayoutParams(params);   
    } 

}
