package com.cndsteel.sysinform.activity;

import android.os.Bundle;
import android.widget.TextView;
import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;

public class SysInformResultDetailActivity extends FrameActivity {	
	
	private TextView SysInformDetailTitle;
	private TextView SysInformDetailCategoryValue;
	private TextView SysInformDetailTimeValue;
	private TextView SysInformDetailAuthorValue;
	private TextView SysInformDetailContentValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.SysInformDetail);
		
		appendFrameworkCenter(R.layout.activity_sysinform_detail);
		
		init();
		
	}
	
	private void init() {

		initView();

	}

	private void initView() {
		
		Bundle _result = getIntent().getExtras();

		SysInformDetailTitle = (TextView) findViewById(R.id.SysInformDetailTitle);
		SysInformDetailTitle.setText(_result.getString("title"));
		
		SysInformDetailCategoryValue = (TextView) findViewById(R.id.SysInformDetailCategoryValue);
		SysInformDetailCategoryValue.setText(_result.getString("cateName"));
		
		SysInformDetailTimeValue = (TextView) findViewById(R.id.SysInformDetailTimeValue);
		SysInformDetailTimeValue.setText(_result.getString("date"));
		
		SysInformDetailAuthorValue = (TextView) findViewById(R.id.SysInformDetailAuthorValue);
		SysInformDetailAuthorValue.setText(_result.getString("author"));
		
		SysInformDetailContentValue = (TextView) findViewById(R.id.SysInformDetailContentValue);
		SysInformDetailContentValue.setText(_result.getString("content"));

		
	}

}
