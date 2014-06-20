package com.cndsteel.stock.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.views.spinner.AbsSpinner.OnItemClickListener;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;
import com.cndsteel.stock.activity.StockAccordingContractQueryResultActivity;

public class StockFragmentContract extends Fragment implements OnClickListener,OnItemClickListener {
	
	private Button btn_StockFragmentContract;

	private RelativeLayout lyot_StockFragmentContract_into_date_start;
	private TextView txtV_StockFragmentContract_into_date_start;

	private RelativeLayout lyot_StockFragmentContract_into_date_end;
	private TextView txtV_StockFragmentContract_into_date_end;

	private RelativeLayout lyot_StockFragmentContract_warehouse;
	private TextView txtV_StockFragmentContract_warehouse;
	
	private RelativeLayout lyot_StockFragmentContract_contract_num;
	private TextView txtV_StockFragmentContract_contract_num;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.stock_according_contract, container, false);
		
		btn_StockFragmentContract = (Button) view.findViewById(R.id.btn_StockFragmentContract);

		lyot_StockFragmentContract_into_date_start = (RelativeLayout) view.findViewById(R.id.lyot_StockFragmentContract_into_date_start);
		txtV_StockFragmentContract_into_date_start = (TextView) view.findViewById(R.id.txtV_StockFragmentContract_into_date_start);

		lyot_StockFragmentContract_into_date_end = (RelativeLayout) view.findViewById(R.id.lyot_StockFragmentContract_into_date_end);
		txtV_StockFragmentContract_into_date_end = (TextView) view.findViewById(R.id.txtV_StockFragmentContract_into_date_end);

		lyot_StockFragmentContract_warehouse = (RelativeLayout) view.findViewById(R.id.lyot_StockFragmentContract_warehouse);
		txtV_StockFragmentContract_warehouse = (TextView) view.findViewById(R.id.txtV_StockFragmentContract_warehouse);
		
		lyot_StockFragmentContract_contract_num = (RelativeLayout) view.findViewById(R.id.lyot_StockFragmentContract_contract_num);
		txtV_StockFragmentContract_contract_num = (TextView) view.findViewById(R.id.txtV_StockFragmentContract_contract_num);
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();

		btn_StockFragmentContract.setOnClickListener(this);
		
		lyot_StockFragmentContract_into_date_start.setOnClickListener(this);
		lyot_StockFragmentContract_into_date_end.setOnClickListener(this);
		lyot_StockFragmentContract_warehouse.setOnClickListener(this);

	}

	//按钮和item的事件处理
	@Override
	public void onClick(View view) {
		
		CndSteelSpinner cndSteelSpinner = null;
		
		ArrayList<String> xx = new ArrayList<String>();
		for(int i = 0; i < 5; i ++){
			xx.add("content_" + i);
		}

		switch (view.getId()) {

		//查询按钮
		case R.id.btn_StockFragmentContract:

			Intent _intent = new Intent(getActivity(),
					StockAccordingContractQueryResultActivity.class);

			getActivity().startActivity(_intent);

			break;
			
		//入库日期从
		case R.id.lyot_StockFragmentContract_into_date_start:
			
			cndSteelSpinner = new CndSteelSpinner(getActivity(), xx, lyot_StockFragmentContract_into_date_start.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentContract_into_date_start,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
			break;
			
		//入库日期至
		case R.id.lyot_StockFragmentContract_into_date_end:
			
			cndSteelSpinner = new CndSteelSpinner(getActivity(), xx, lyot_StockFragmentContract_into_date_end.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentContract_into_date_end,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
			
			break;
			
		//合同号
		case R.id.lyot_StockFragmentContract_contract_num:
			
			cndSteelSpinner = new CndSteelSpinner(getActivity(), xx, lyot_StockFragmentContract_contract_num.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentContract_contract_num,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
			
			break;
			
		//仓库
		case R.id.lyot_StockFragmentContract_warehouse:
			
			cndSteelSpinner = new CndSteelSpinner(getActivity(), xx, lyot_StockFragmentContract_warehouse.getWidth());
			cndSteelSpinner.showAsDropDown(lyot_StockFragmentContract_warehouse,0,-5);
			cndSteelSpinner.setOnItemClickListener(this);
	
			break;

		default:
			break;
		}

	}

	//下拉item的事件处理
	@Override
	public void onItemClick(int position) {
		
	}

}
