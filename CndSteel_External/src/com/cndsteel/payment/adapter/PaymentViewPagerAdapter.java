package com.cndsteel.payment.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.views.spinner.AbsSpinner.OnItemClickListener;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;
import com.cndsteel.payment.activity.PaymentAsContractQueryResultActivity;
import com.cndsteel.payment.activity.PaymentAsInformationQueryResultActivity;

public class PaymentViewPagerAdapter extends PagerAdapter implements OnClickListener, OnItemClickListener {

	private Context context;
	
	private ArrayList<View> viewList;
	
	private View asInformation;
	private View asContract;
	
	//////////  按付款信息页面  ///////////////
	private Button btn_contractQuery;
	private RelativeLayout lyot_paymentDateStart,lyot_paymentDateEnd,lyot_paymentMethod,lyot_paymentBillNum;
	private TextView txtV_paymentDateStart,txtV_paymentDateEnd,txtV_paymentMethod,txtV_paymentBillNum;
	
	//////////按合同款项页面  ///////////////
	private Button btn_paymentContractNumQuery;
	private RelativeLayout lyot_paymentPlanDate,lyot_paymentContractNum;
	private TextView txtV_paymentPlanDate,txtV_paymentContractNum;
	
	
	
	
	//构造方法
	public PaymentViewPagerAdapter(Context context,ArrayList<View> viewList,View asInformation,View asContract){
		this.viewList = viewList;
		this.context = context;
		this.asContract = asContract;
		this.asInformation = asInformation;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		
		container.removeView(viewList.get(position));
		
	}
	
	@Override
	public int getCount() {
		return viewList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		container.addView(viewList.get(position), 0);
		
		initView();
		
		return viewList.get(position);
	}

	
	/** 初使化View并注册事件 **/
	private void initView() {
		initInformation();
		initContract();
	};
	
	// 按付款信息
	private void initInformation() {
		
		btn_contractQuery = (Button) asInformation.findViewById(R.id.btn_contractQuery);
		btn_contractQuery.setOnClickListener(this);
		
		lyot_paymentDateStart = (RelativeLayout) asInformation.findViewById(R.id.lyot_paymentDateStart);
		lyot_paymentDateStart.setOnClickListener(this);
		lyot_paymentDateEnd = (RelativeLayout) asInformation.findViewById(R.id.lyot_paymentDateEnd);
		lyot_paymentDateEnd.setOnClickListener(this);
		lyot_paymentMethod = (RelativeLayout) asInformation.findViewById(R.id.lyot_paymentMethod);
		lyot_paymentMethod.setOnClickListener(this);
		lyot_paymentBillNum = (RelativeLayout) asInformation.findViewById(R.id.lyot_paymentBillNum);
		lyot_paymentBillNum.setOnClickListener(this);
		
		txtV_paymentDateStart = (TextView) asInformation.findViewById(R.id.txtV_paymentDateStart);
		txtV_paymentDateEnd = (TextView) asInformation.findViewById(R.id.txtV_paymentDateEnd);
		txtV_paymentMethod = (TextView) asInformation.findViewById(R.id.txtV_paymentMethod);
		txtV_paymentBillNum = (TextView) asInformation.findViewById(R.id.txtV_paymentBillNum);
		
	}
	// 按合同款项
	private void initContract() {
		
		btn_paymentContractNumQuery = (Button) asContract.findViewById(R.id.btn_paymentContractNumQuery);
		btn_paymentContractNumQuery.setOnClickListener(this);
		
		lyot_paymentPlanDate = (RelativeLayout) asContract.findViewById(R.id.lyot_paymentPlanDate);
		lyot_paymentPlanDate.setOnClickListener(this);
		lyot_paymentContractNum = (RelativeLayout) asContract.findViewById(R.id.lyot_paymentContractNum);
		lyot_paymentContractNum.setOnClickListener(this);
		
		txtV_paymentPlanDate = (TextView) asContract.findViewById(R.id.txtV_paymentPlanDate);
		txtV_paymentContractNum = (TextView) asContract.findViewById(R.id.txtV_paymentContractNum);
		
	}


	@Override
	public void onClick(View view) {
		
		CndSteelSpinner _cndSteelSpinner = null;
		
		Intent _intent = null;
		
		switch (view.getId()) {
		/////////////////////////////////    按付款信息页面    ////////////////////////
		//查询按钮
		case R.id.btn_contractQuery:
			_intent = new Intent(context, PaymentAsInformationQueryResultActivity.class);
			context.startActivity(_intent);
			break;
		//支付日期从
		case R.id.lyot_paymentDateStart:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_paymentDateStart);
			
			break;
		//支付日期至
		case R.id.lyot_paymentDateEnd:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_paymentDateEnd);
			
			break;
		//支付方式
		case R.id.lyot_paymentMethod:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_paymentMethod);
			
			break;
		//票据号
		case R.id.lyot_paymentBillNum:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_paymentBillNum);
			
			break;
			
		/////////////////////////////////    按合同款项页面    ////////////////////////
		//查询按钮
		case R.id.btn_paymentContractNumQuery:
			_intent = new Intent(context,PaymentAsContractQueryResultActivity.class);
			context.startActivity(_intent);
			break;
		//计划年月
		case R.id.lyot_paymentPlanDate:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_paymentPlanDate);
			
			break;
			
		//合同号
		case R.id.lyot_paymentContractNum:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_paymentContractNum);
			
			break;

		default:
			break;
		}
		
	}

	/** spinner的item的事件 **/
	@Override
	public void onItemClick(int position) {
		
	}
	
	/** 跳出下拉**/
	public CndSteelSpinner ShowSpinnerView(View v){
		
		ArrayList<String> xx = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			xx.add("content_" + i);
		}
		
		CndSteelSpinner cndSteelSpinner = new CndSteelSpinner(context, xx,v.getWidth());
		cndSteelSpinner.showAsDropDown(v, 0, -5);
		cndSteelSpinner.setOnItemClickListener(this);
		
		return cndSteelSpinner;

	}

}
