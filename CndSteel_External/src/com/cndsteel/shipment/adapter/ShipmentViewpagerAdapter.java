package com.cndsteel.shipment.adapter;

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
import com.cndsteel.shipment.activity.ShipmentAsContractNumQueryResultActivity;
import com.cndsteel.shipment.activity.ShipmentAsInvoiceNumQueryResultActivity;

public class ShipmentViewpagerAdapter extends PagerAdapter implements OnClickListener,OnItemClickListener{
	
	private Context context;
	
	private ArrayList<View> viewList;
	
	private View asInvoiceNum;
	private View asContractNum;
	
	//////////  按发货单号页面  ///////////////
	private Button btn_shipmentAsInvoiceNum;
	private RelativeLayout lyot_shipment_invoiceDate_start,lyot_shipment_invoiceDate_end,lyot_shipment_invoiceNum,lyot_shipment_warehouse;
	private TextView txtV_shipment_invoiceDate_start,txtV_shipment_invoiceDate_end,txtV_shipment_invoiceNum,txtV_shipment_warehouse;
	
	//////////按合同号页面  ///////////////
	private Button btn_shipmentAsContractNum;
	private RelativeLayout lyot_shipmentAsContractNum_invoiceDate_start,lyot_shipmentAsContractNum_invoiceDate_end,lyot_shipmentAsContractNum_contractNum,lyot_shipmentAsContractNum_goodsName,lyot_shipmentAsContractNum_material;
	private TextView txtV_shipmentAsContractNum_invoiceDate_start,txtV_shipmentAsContractNum_invoiceDate_end,txtV_shipmentAsContractNum_contractNum,txtV_shipmentAsContractNum_goodsName,txtV_shipmentAsContractNum_material;
	
	
	
	
	//构造方法
	public ShipmentViewpagerAdapter(Context context,ArrayList<View> viewList,View asInvoiceNum,View asContractNum){
		this.viewList = viewList;
		this.context = context;
		this.asContractNum = asContractNum;
		this.asInvoiceNum = asInvoiceNum;
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
		initInvoiceNum();
		initContractNum();
	};
	
	// 按发货单号
	private void initInvoiceNum() {
		
		btn_shipmentAsInvoiceNum = (Button) asInvoiceNum.findViewById(R.id.btn_shipmentAsInvoiceNum);
		btn_shipmentAsInvoiceNum.setOnClickListener(this);
		
		lyot_shipment_invoiceDate_start = (RelativeLayout) asInvoiceNum.findViewById(R.id.lyot_shipment_invoiceDate_start);
		lyot_shipment_invoiceDate_start.setOnClickListener(this);
		lyot_shipment_invoiceDate_end = (RelativeLayout) asInvoiceNum.findViewById(R.id.lyot_shipment_invoiceDate_end);
		lyot_shipment_invoiceDate_end.setOnClickListener(this);
		lyot_shipment_invoiceNum = (RelativeLayout) asInvoiceNum.findViewById(R.id.lyot_shipment_invoiceNum);
		lyot_shipment_invoiceNum.setOnClickListener(this);
		lyot_shipment_warehouse = (RelativeLayout) asInvoiceNum.findViewById(R.id.lyot_shipment_warehouse);
		lyot_shipment_warehouse.setOnClickListener(this);
		
		txtV_shipment_invoiceDate_start = (TextView) asInvoiceNum.findViewById(R.id.txtV_shipment_invoiceDate_start);
		txtV_shipment_invoiceDate_end = (TextView) asInvoiceNum.findViewById(R.id.txtV_shipment_invoiceDate_end);
		txtV_shipment_invoiceNum = (TextView) asInvoiceNum.findViewById(R.id.txtV_shipment_invoiceNum);
		txtV_shipment_warehouse = (TextView) asInvoiceNum.findViewById(R.id.txtV_shipment_warehouse);
		
	}
	// 按合同号
	private void initContractNum() {
		
		btn_shipmentAsContractNum = (Button) asContractNum.findViewById(R.id.btn_shipmentAsContractNum);
		btn_shipmentAsContractNum.setOnClickListener(this);
		
		lyot_shipmentAsContractNum_invoiceDate_start = (RelativeLayout) asContractNum.findViewById(R.id.lyot_shipmentAsContractNum_invoiceDate_start);
		lyot_shipmentAsContractNum_invoiceDate_start.setOnClickListener(this);
		lyot_shipmentAsContractNum_invoiceDate_end = (RelativeLayout) asContractNum.findViewById(R.id.lyot_shipmentAsContractNum_invoiceDate_end);
		lyot_shipmentAsContractNum_invoiceDate_end.setOnClickListener(this);
		lyot_shipmentAsContractNum_contractNum = (RelativeLayout) asContractNum.findViewById(R.id.lyot_shipmentAsContractNum_contractNum);
		lyot_shipmentAsContractNum_contractNum.setOnClickListener(this);
		lyot_shipmentAsContractNum_goodsName = (RelativeLayout) asContractNum.findViewById(R.id.lyot_shipmentAsContractNum_goodsName);
		lyot_shipmentAsContractNum_goodsName.setOnClickListener(this);
		lyot_shipmentAsContractNum_material = (RelativeLayout) asContractNum.findViewById(R.id.lyot_shipmentAsContractNum_material);
		lyot_shipmentAsContractNum_material.setOnClickListener(this);
		
		txtV_shipmentAsContractNum_invoiceDate_start = (TextView) asContractNum.findViewById(R.id.txtV_shipmentAsContractNum_invoiceDate_start);
		txtV_shipmentAsContractNum_invoiceDate_end = (TextView) asContractNum.findViewById(R.id.txtV_shipmentAsContractNum_invoiceDate_end);
		txtV_shipmentAsContractNum_contractNum = (TextView) asContractNum.findViewById(R.id.txtV_shipmentAsContractNum_contractNum);
		txtV_shipmentAsContractNum_goodsName = (TextView) asContractNum.findViewById(R.id.txtV_shipmentAsContractNum_goodsName);
		txtV_shipmentAsContractNum_material = (TextView) asContractNum.findViewById(R.id.txtV_shipmentAsContractNum_material);
		
	}


	@Override
	public void onClick(View view) {
		
		CndSteelSpinner _cndSteelSpinner = null;
		
		Intent _intent = null;
		
		switch (view.getId()) {
		/////////////////////////////////    按发货单号页面    ////////////////////////
		//查询按钮
		case R.id.btn_shipmentAsInvoiceNum:
			_intent = new Intent(context, ShipmentAsInvoiceNumQueryResultActivity.class);
			context.startActivity(_intent);
			break;
		//发货日期从
		case R.id.lyot_shipment_invoiceDate_start:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_shipment_invoiceDate_start);
			
			break;
		//发货日期至
		case R.id.lyot_shipment_invoiceDate_end:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_shipment_invoiceDate_end);
			
			break;
		//发货单号
		case R.id.lyot_shipment_invoiceNum:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_shipment_invoiceNum);
			
			break;
		//仓库
		case R.id.lyot_shipment_warehouse:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_shipment_warehouse);
			
			break;
			
		/////////////////////////////////    按合同号页面    ////////////////////////
		//查询按钮
		case R.id.btn_shipmentAsContractNum:
			_intent = new Intent(context, ShipmentAsContractNumQueryResultActivity.class);
			context.startActivity(_intent);
			break;
		//发货日期从
		case R.id.lyot_shipmentAsContractNum_invoiceDate_start:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_shipmentAsContractNum_invoiceDate_start);
			
			break;
			
		//发货日期至
		case R.id.lyot_shipmentAsContractNum_invoiceDate_end:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_shipmentAsContractNum_invoiceDate_end);
			
			break;
			
		//合同号
		case R.id.lyot_shipmentAsContractNum_contractNum:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_shipmentAsContractNum_contractNum);
			
			break;
			
		//品名
		case R.id.lyot_shipmentAsContractNum_goodsName:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_shipmentAsContractNum_goodsName);
			
			break;
			
		//材质
		case R.id.lyot_shipmentAsContractNum_material:
			
			_cndSteelSpinner = ShowSpinnerView(lyot_shipmentAsContractNum_material);
						
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
