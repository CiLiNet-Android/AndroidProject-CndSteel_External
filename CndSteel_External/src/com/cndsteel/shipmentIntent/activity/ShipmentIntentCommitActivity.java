package com.cndsteel.shipmentIntent.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.views.spinner.AbsSpinner.OnItemClickListener;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;

public class ShipmentIntentCommitActivity extends FrameActivity implements OnClickListener, OnItemClickListener {

	/** 提交按钮 **/
	private Button btn_commit;
	/** 仓库名 **/
	private RelativeLayout lyot_warehouseName;
	
	/** 仓库名值 **/
	private TextView commitWarehouseName;
	/** 品名 **/
	private TextView commitGoodsName;	
	/** 材质 **/
	private TextView commitMaterial;
	/** 规格 **/
	private TextView commitStandard;
	/** 捆包号 **/
	private TextView commitPackageNum;
	/** 吨数 **/
	private TextView commitTonnage;
	/** 件数 **/
	private TextView commitNumberOfPackages;
	/** 备注 **/
	private TextView commitComment;
/*
	*//** 初使化并设置值 **//*
	private void initTextView() {
		commitWarehouseName = (TextView) findViewById(R.id.commitWarehouseName);
		commitWarehouseName.setText(text);
		
		commitGoodsName = (TextView) findViewById(R.id.commitGoodsName);
		commitGoodsName.setText(text);
		
		commitMaterial = (TextView) findViewById(R.id.commitMaterial);
		commitMaterial.setText(text);
		
		commitStandard = (TextView) findViewById(R.id.commitStandard);
		commitStandard.setText(text);
		
		commitPackageNum = (TextView) findViewById(R.id.commitPackageNum);
		commitPackageNum.setText(text);
		
		commitTonnage = (TextView) findViewById(R.id.commitTonnage);
		commitTonnage.setText(text);
		
		commitNumberOfPackages = (TextView) findViewById(R.id.commitNumberOfPackages);
		commitNumberOfPackages.setText(text);
		
		commitComment = (TextView) findViewById(R.id.commitComment);
		commitComment.setText(text);
	}
*/	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTopBarTitle(R.string.confirmCommitIntent);

		appendFrameworkCenter(R.layout.shipment_intent_commit);

		init();

	}

	private void init() {

		initView();

	}

	private void initView() {
		
		btn_commit = (Button) findViewById(R.id.btn_commit);
		btn_commit.setOnClickListener(this);

		lyot_warehouseName = (RelativeLayout) findViewById(R.id.lyot_warehouseName);
		lyot_warehouseName.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		//提交按钮
		case R.id.btn_commit:
			startActivity(ShipmentConfirmActivity.class);
			break;
		//仓库名的下拉
		case R.id.lyot_warehouseName:

			CndSteelSpinner _spinner = pullDown(lyot_warehouseName);
			_spinner.setOnItemClickListener(this);
			
			break;
			
		default:
			break;
		}
			
	}

	// 显示下拉框
	private CndSteelSpinner pullDown(View v) {
		ArrayList<String> xx = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			xx.add("content_" + i);
		}
		CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(this, xx,v.getWidth());
		_cndSteelSpinner.showAsDropDown(v, 0, -5);
		return _cndSteelSpinner;
	}

	//下拉item的事件处理
	@Override
	public void onItemClick(int position) {
		
	}

}
