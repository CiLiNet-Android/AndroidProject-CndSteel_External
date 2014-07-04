package com.cndsteel.sysinform.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.sysinform.bean.SysInformBean;

public class SysInformQueryResultDetailActivity extends FrameActivity {	
	
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
		
		SysInformBean _sysInformBean = (SysInformBean) getIntent().getSerializableExtra("sysInformBean");

		((TextView) findViewById(R.id.SysInformDetailTitle)).setText(_sysInformBean.title);
		
		((TextView) findViewById(R.id.SysInformDetailCategoryValue)).setText(_sysInformBean.title);
		
		((TextView) findViewById(R.id.SysInformDetailTimeValue)).setText(_sysInformBean.date);;
		
		((TextView) findViewById(R.id.SysInformDetailAuthorValue)).setText(_sysInformBean.author);;
		
		((TextView) findViewById(R.id.SysInformDetailContentValue)).setText(_sysInformBean.content);;

		
	}

}
