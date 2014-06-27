package com.cndsteel.info.activity;

import android.os.Bundle;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;

public class InfoDetailActivity extends FrameActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTopBarTitle(R.string.InfoDetail);
		
		appendFrameworkCenter(R.layout.info_detail);
		
		
	}

}
