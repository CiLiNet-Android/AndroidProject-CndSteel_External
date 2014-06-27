package com.cndsteel.bill.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.bill.activity.BillActivity;
import com.cndsteel.bill.activity.BillContractInvoiceQueryResultActivity;
import com.cndsteel.bill.activity.BillContractTrackingQueryResultActivity;
import com.cndsteel.framework.views.spinner.AbsSpinner.OnItemClickListener;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;

public class BillViewPagerAdapter extends PagerAdapter implements OnClickListener, OnItemClickListener {
	
	private Context context;
	private ArrayList<View> viewList;
	
	private View pageContractInvoice;
	private View pageContractTracking;
	
	//合同开票
	private RelativeLayout lyot_contractDateStart,lyot_contractDateEnd,lyot_contractNum;
	private TextView txtV_contractDateStart,txtV_contractDateEnd;
	private EditText txtV_contractNum;
	private Button btn_contractInvoiceQuery;
	
	
	//合同正本跟踪
	private RelativeLayout lyot_contractTrackingNumber,lyot_corporationSendAndReceiveState,lyot_logisticsSendAndReceiveState;
	private TextView txtV_corporationSendAndReceiveState,txtV_logisticsSendAndReceiveState;
	private EditText txtV_contractTrackingNum;
	private Button btn_contractTrackingQuery;
	
	public BillViewPagerAdapter(Context context,ArrayList<View> viewList,View pageContractInvoice,View pageContractTracking) {
		this.context = context;
		this.viewList = viewList;
		this.pageContractInvoice = pageContractInvoice;
		this.pageContractTracking = pageContractTracking;
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
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewList.get(position));
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		container.addView(viewList.get(position));
		
		//判断当前位置是哪个页面做相对应的处理
		switch (position) {
		
		//合同开票页面
		case BillActivity.BILL_PAGE_CONTRACT_INVOICE:
				initContractInvoicePage();
			break;
			
		//合同正本跟踪页面
		case BillActivity.BILL_PAGE_CONTRACT_TRACKING:
				initContractTrackingPage();
			break;

		default:
			break;
		}
		
		return viewList.get(position);
	}
	
	/** 初使化 合同开票 页面的数据 **/
	private void initContractInvoicePage() {
		
		lyot_contractDateStart = (RelativeLayout) pageContractInvoice.findViewById(R.id.lyot_contractDateStart);
		lyot_contractDateStart.setOnClickListener(this);
		lyot_contractDateEnd = (RelativeLayout) pageContractInvoice.findViewById(R.id.lyot_contractDateEnd);
		lyot_contractDateEnd.setOnClickListener(this);
		lyot_contractNum = (RelativeLayout) pageContractInvoice.findViewById(R.id.lyot_contractNum);
		lyot_contractNum.setOnClickListener(this);
		
		btn_contractInvoiceQuery = (Button) pageContractInvoice.findViewById(R.id.btn_contractInvoiceQuery);
		btn_contractInvoiceQuery.setOnClickListener(this);
		
		txtV_contractDateStart = (TextView) pageContractInvoice.findViewById(R.id.txtV_contractDateStart);
		txtV_contractDateEnd = (TextView) pageContractInvoice.findViewById(R.id.txtV_contractDateEnd);
		txtV_contractNum = (EditText) pageContractInvoice.findViewById(R.id.txtV_contractNum);
	}
	
	//设置文本的值
	private void setTextValue() {
		
		txtV_contractDateStart.setText(null);
		txtV_contractDateEnd.setText(null);
		txtV_contractNum.setText(null);
		
		txtV_corporationSendAndReceiveState.setText(null);
		txtV_logisticsSendAndReceiveState.setText(null);
		txtV_contractTrackingNum.setText(null);
	}
	
	/** 初使化 合同正本跟踪 页面的数据 **/
	private void initContractTrackingPage() {
		
		lyot_contractTrackingNumber = (RelativeLayout) pageContractTracking.findViewById(R.id.lyot_contractTrackingNumber);
		lyot_contractTrackingNumber.setOnClickListener(this);
		lyot_corporationSendAndReceiveState = (RelativeLayout) pageContractTracking.findViewById(R.id.lyot_corporationSendAndReceiveState);
		lyot_corporationSendAndReceiveState.setOnClickListener(this);
		lyot_logisticsSendAndReceiveState = (RelativeLayout) pageContractTracking.findViewById(R.id.lyot_logisticsSendAndReceiveState);
		lyot_logisticsSendAndReceiveState.setOnClickListener(this);
		
		btn_contractTrackingQuery = (Button) pageContractTracking.findViewById(R.id.btn_contractTrackingQuery);
		btn_contractTrackingQuery.setOnClickListener(this);
		
		txtV_corporationSendAndReceiveState = (TextView) pageContractTracking.findViewById(R.id.txtV_corporationSendAndReceiveState);
		txtV_logisticsSendAndReceiveState = (TextView) pageContractTracking.findViewById(R.id.txtV_logisticsSendAndReceiveState);
		txtV_contractTrackingNum = (EditText) pageContractTracking.findViewById(R.id.txtV_contractTrackingNum);
	}
	
	//显示下拉框
	private CndSteelSpinner pullDown(RelativeLayout v){
		ArrayList<String> xx = new ArrayList<String>();
		for(int i = 0; i < 5; i ++){
			xx.add("content_" + i);
		}
		CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(context, xx, v.getWidth());
		_cndSteelSpinner.showAsDropDown(v,0,-5);
		return _cndSteelSpinner;
	}

	//单击事件处理
	@Override
	public void onClick(View view) {
		
		CndSteelSpinner _spinner;
		
		switch (view.getId()) {
		/**
		 * 合同开票
		 */
		//合同年月从
		case R.id.lyot_contractDateStart:
				_spinner = pullDown(lyot_contractDateStart);
				_spinner.setOnItemClickListener(this);
			break;
		//合同年月至	
		case R.id.lyot_contractDateEnd:
			_spinner = pullDown(lyot_contractDateEnd);
			_spinner.setOnItemClickListener(this);
			break;
		//合同号	
		case R.id.lyot_contractNum:
			_spinner = pullDown(lyot_contractNum);
			_spinner.setOnItemClickListener(this);
			break;
		//查询按钮
		case R.id.btn_contractInvoiceQuery:
			startActivity(BillContractInvoiceQueryResultActivity.class);
			break;

		/**
		 * 合同正本跟踪
		 */
		//合同号
		case R.id.lyot_contractTrackingNumber:
			_spinner = pullDown(lyot_contractTrackingNumber);
			_spinner.setOnItemClickListener(this);
			break;
		//我司收发状态
		case R.id.lyot_corporationSendAndReceiveState:
			_spinner = pullDown(lyot_corporationSendAndReceiveState);
			_spinner.setOnItemClickListener(this);
			break;
		//物流收发状态
		case R.id.lyot_logisticsSendAndReceiveState:
			_spinner = pullDown(lyot_logisticsSendAndReceiveState);
			_spinner.setOnItemClickListener(this);
			break;
		//查询按钮
		case R.id.btn_contractTrackingQuery:
			startActivity(BillContractTrackingQueryResultActivity.class);
			break;
			
		default:
			break;
		}
		
	}
	
	private void startActivity(Class<?> cla){
		Intent _intent = new Intent(context,cla);
		context.startActivity(_intent);
	}

	//下拉的item的事件处理
	@Override
	public void onItemClick(int position) {
		
	}

}
