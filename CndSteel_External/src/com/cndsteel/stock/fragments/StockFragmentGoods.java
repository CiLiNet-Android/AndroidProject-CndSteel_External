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
import com.cndsteel.stock.activity.StockAccordingGoodsQueryResultActivity;

public class StockFragmentGoods extends Fragment implements OnClickListener,OnItemClickListener {
	
	private Button btn_StockFragmentGoods;
	
	private RelativeLayout lyot_StockFragmentGoods_into_date_start;
	private TextView txtV_StockFragmentGoods_into_date_start;

	private RelativeLayout lyot_StockFragmentGoods_into_date_end;
	private TextView txtV_StockFragmentGoods_into_date_end;
	
	private RelativeLayout lyot_StockFragmentGoods_goodsName;
	private TextView txtV_StockFragmentGoods_goodsName;
	
	private RelativeLayout lyot_StockFragmentGoods_material;
	private TextView txtV_StockFragmentGoods_material;

	private RelativeLayout lyot_StockFragmentGoods_warehouse;
	private TextView txtV_StockFragmentGoods_warehouse;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.stock_according_goods, container, false);
		
		btn_StockFragmentGoods = (Button) view.findViewById(R.id.btn_StockFragmentGoods);
		
		lyot_StockFragmentGoods_into_date_start = (RelativeLayout) view.findViewById(R.id.lyot_StockFragmentGoods_into_date_start);
		txtV_StockFragmentGoods_into_date_start = (TextView) view.findViewById(R.id.txtV_StockFragmentGoods_into_date_start);

		lyot_StockFragmentGoods_into_date_end = (RelativeLayout) view.findViewById(R.id.lyot_StockFragmentGoods_into_date_end);
		txtV_StockFragmentGoods_into_date_end = (TextView) view.findViewById(R.id.txtV_StockFragmentGoods_into_date_end);

		lyot_StockFragmentGoods_warehouse = (RelativeLayout) view.findViewById(R.id.lyot_StockFragmentGoods_warehouse);
		txtV_StockFragmentGoods_warehouse = (TextView) view.findViewById(R.id.txtV_StockFragmentGoods_warehouse);
		
		lyot_StockFragmentGoods_goodsName = (RelativeLayout) view.findViewById(R.id.lyot_StockFragmentGoods_goodsName);
		txtV_StockFragmentGoods_goodsName = (TextView) view.findViewById(R.id.txtV_StockFragmentGoods_goodsName);
		
		lyot_StockFragmentGoods_material = (RelativeLayout) view.findViewById(R.id.lyot_StockFragmentGoods_material);
		txtV_StockFragmentGoods_material = (TextView) view.findViewById(R.id.txtV_StockFragmentGoods_material);
		
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();

		btn_StockFragmentGoods.setOnClickListener(this);
		
		lyot_StockFragmentGoods_into_date_start.setOnClickListener(this);
		lyot_StockFragmentGoods_into_date_end.setOnClickListener(this);
		lyot_StockFragmentGoods_warehouse.setOnClickListener(this);
		lyot_StockFragmentGoods_goodsName.setOnClickListener(this);
		lyot_StockFragmentGoods_material.setOnClickListener(this);

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
			case R.id.btn_StockFragmentGoods:

				Intent _intent = new Intent(getActivity(),
						StockAccordingGoodsQueryResultActivity.class);

				getActivity().startActivity(_intent);

				break;
				
			//入库日期从
			case R.id.lyot_StockFragmentGoods_into_date_start:
				
				cndSteelSpinner = new CndSteelSpinner(getActivity(), xx, lyot_StockFragmentGoods_into_date_start.getWidth());
				cndSteelSpinner.showAsDropDown(lyot_StockFragmentGoods_into_date_start,0,-5);
				cndSteelSpinner.setOnItemClickListener(this);
				break;
				
			//入库日期至
			case R.id.lyot_StockFragmentGoods_into_date_end:
				
				cndSteelSpinner = new CndSteelSpinner(getActivity(), xx, lyot_StockFragmentGoods_into_date_end.getWidth());
				cndSteelSpinner.showAsDropDown(lyot_StockFragmentGoods_into_date_end,0,-5);
				cndSteelSpinner.setOnItemClickListener(this);
				
				break;
				
			//品名
			case R.id.lyot_StockFragmentGoods_goodsName:
				
				cndSteelSpinner = new CndSteelSpinner(getActivity(), xx, lyot_StockFragmentGoods_goodsName.getWidth());
				cndSteelSpinner.showAsDropDown(lyot_StockFragmentGoods_goodsName,0,-5);
				cndSteelSpinner.setOnItemClickListener(this);
				
				break;
				
			//材质
			case R.id.lyot_StockFragmentGoods_material:
				
				cndSteelSpinner = new CndSteelSpinner(getActivity(), xx, lyot_StockFragmentGoods_material.getWidth());
				cndSteelSpinner.showAsDropDown(lyot_StockFragmentGoods_material,0,-5);
				cndSteelSpinner.setOnItemClickListener(this);
				
				break;
				
			//仓库
			case R.id.lyot_StockFragmentGoods_warehouse:
				
				cndSteelSpinner = new CndSteelSpinner(getActivity(), xx, lyot_StockFragmentGoods_warehouse.getWidth());
				cndSteelSpinner.showAsDropDown(lyot_StockFragmentGoods_warehouse,0,-5);
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
