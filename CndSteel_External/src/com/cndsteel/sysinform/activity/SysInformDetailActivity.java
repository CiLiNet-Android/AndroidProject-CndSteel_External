package com.cndsteel.sysinform.activity;

import android.os.Bundle;
import android.widget.TextView;
import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;

public class SysInformDetailActivity extends FrameActivity {	
	
	private TextView infoDetailTitle;
	private TextView infoDetailCategoryValue;
	private TextView infoDetailTimeValue;
	private TextView infoDetailIssuerValue;
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

		infoDetailTitle = (TextView) findViewById(R.id.SysInformDetailTitle);
		infoDetailCategoryValue = (TextView) findViewById(R.id.SysInformDetailCategoryValue);
		infoDetailTimeValue = (TextView) findViewById(R.id.SysInformDetailTimeValue);
		infoDetailIssuerValue = (TextView) findViewById(R.id.SysInformDetailIssuerValue);
		SysInformDetailContentValue = (TextView) findViewById(R.id.SysInformDetailContentValue);
		
	}

}
