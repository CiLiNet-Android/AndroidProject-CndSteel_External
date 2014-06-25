package com.cndsteel.shipmentIntent.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cndsteel.R;
import com.cndsteel.framework.views.spinner.CndSteelSpinner;
import com.cndsteel.shipmentIntent.activity.ShipmentIntentActivity;
import com.cndsteel.shipmentIntent.bean.ShipmentIntent;

public class ShipmentIntentViewPagerAdapter extends PagerAdapter implements OnClickListener, OnItemClickListener,com.cndsteel.framework.views.spinner.AbsSpinner.OnItemClickListener {

	private Context context;
	
	private ArrayList<View> viewList;

	private ListView toPickUpTheGoodsIntentListView;

	private View toPickUpTheGoodsIntention;
	private View pickUpTheGoodsIntentionBill;
	
	private Button btn_intent;
	
	private RelativeLayout lyot_contractDate;
	
	private RelativeLayout lyot_recordDateStart,lyot_recordDateEnd,lyot_intentBillNumer;

	public ShipmentIntentViewPagerAdapter(Context context,ArrayList<View> viewList,View toPickUpTheGoodsIntention, View pickUpTheGoodsIntentionBill) {
		this.context = context;
		this.viewList = viewList;
		this.toPickUpTheGoodsIntention = toPickUpTheGoodsIntention;
		this.pickUpTheGoodsIntentionBill = pickUpTheGoodsIntentionBill;
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

		// 判断当前的位置来处理不同页面的数据
		switch (position) {

		// 待提货意向页面
		case ShipmentIntentActivity.PAGE_TO_INTENT:
			initPageLeftIntent();
			break;

		// 提货意向单页面
		case ShipmentIntentActivity.PAGE_INTENT_BILL:
			initPageRightIntent();
			break;

		default:
			break;
		}

		return viewList.get(position);
	}

	// 待提货意向页面的数据和事件
	private void initPageLeftIntent() {
		
		lyot_contractDate = (RelativeLayout) toPickUpTheGoodsIntention.findViewById(R.id.lyot_contractDate);
		lyot_contractDate.setOnClickListener(this);
		
		
		ArrayList<ShipmentIntent> _datas = new ArrayList<ShipmentIntent>();
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "500吨", "50件", R.drawable.evoke_dark));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "500吨", "50件", R.drawable.evoke_checked));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "500吨", "50件", R.drawable.evoke_checked));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "500吨", "50件", R.drawable.evoke_dark));
		_datas.add(new ShipmentIntent("管坯", "20#", "100*100", "500吨", "50件", R.drawable.evoke_checked));
		
		ShipmentIntentViewPagerListViewAdapter _adapter = new ShipmentIntentViewPagerListViewAdapter(context);
		_adapter.initDatas(_datas);
		
		toPickUpTheGoodsIntentListView = (ListView) toPickUpTheGoodsIntention.findViewById(R.id.toPickUpTheGoodsIntentListView);
		toPickUpTheGoodsIntentListView.setAdapter(_adapter);
		toPickUpTheGoodsIntentListView.setOnItemClickListener(this);
		
	}

	// 提货意向单页面的数据和事件
	private void initPageRightIntent() {
		
		lyot_recordDateStart = (RelativeLayout) pickUpTheGoodsIntentionBill.findViewById(R.id.lyot_recordDateStart);
		lyot_recordDateStart.setOnClickListener(this);
		lyot_recordDateEnd = (RelativeLayout) pickUpTheGoodsIntentionBill.findViewById(R.id.lyot_recordDateEnd);
		lyot_recordDateEnd.setOnClickListener(this);
		lyot_intentBillNumer = (RelativeLayout) pickUpTheGoodsIntentionBill.findViewById(R.id.lyot_intentBillNumer);
		lyot_intentBillNumer.setOnClickListener(this);
		
		btn_intent = (Button) pickUpTheGoodsIntentionBill.findViewById(R.id.btn_intent);
		btn_intent.setOnClickListener(this);
	}

	
	@Override
	public void onClick(View view) {
		
		CndSteelSpinner cs ;
		
		switch (view.getId()) {
		
		case R.id.lyot_contractDate:
			
			cs = pullDown(lyot_contractDate);
			cs.setOnItemClickListener(this);
			break;
			
		case R.id.lyot_recordDateStart:
			
			cs = pullDown(lyot_recordDateStart);
			cs.setOnItemClickListener(this);
			break;
			
		case R.id.lyot_recordDateEnd:
			
			cs = pullDown(lyot_recordDateEnd);
			cs.setOnItemClickListener(this);
			break;
			
		case R.id.lyot_intentBillNumer:
			
			cs = pullDown(lyot_intentBillNumer);
			cs.setOnItemClickListener(this);
			break;
			
		//查询按钮
		case R.id.btn_intent:
			Toast.makeText(context, "...", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

	//Listview的item事件
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}

	//下拉的item事件
	@Override
	public void onItemClick(int position) {
		
	}
	
	//显示下拉框
	private CndSteelSpinner pullDown(View v){
		ArrayList<String> xx = new ArrayList<String>();
		for(int i = 0; i < 5; i ++){
			xx.add("content_" + i);
		}
		CndSteelSpinner _cndSteelSpinner = new CndSteelSpinner(context, xx, v.getWidth());
		_cndSteelSpinner.showAsDropDown(v,0,-5);
		return _cndSteelSpinner;
	}

}
