package com.cndsteel.shipment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndsteel.R;
import com.cndsteel.framework.adapter.AbsBaseAdapter;
import com.cndsteel.shipment.bean.ShipmentBean;

public class ShipmentDetailQueryResultListAdapter extends AbsBaseAdapter<ShipmentBean> {

	public ShipmentDetailQueryResultListAdapter(Context context) {
		super(context);
	}

	private class ViewHolder {
		private TextView txtV_shipmentDetailQueryResultListItemPkgNo;
		private TextView txtV_shipmentDetailQueryResultListItemWeight;
		private TextView txtV_shipmentDetailQueryResultListItemPiece;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder _viewHolder;
		if(null == convertView){
			_viewHolder = new ViewHolder();
			convertView = getLayoutInflater().inflate(R.layout.layout_shipment_detail_query_result_detail_list_item, null);
			_viewHolder.txtV_shipmentDetailQueryResultListItemPkgNo = (TextView) convertView.findViewById(R.id.txtV_shipmentDetailQueryResultListItemPkgNo);
			_viewHolder.txtV_shipmentDetailQueryResultListItemWeight = (TextView) convertView.findViewById(R.id.txtV_shipmentDetailQueryResultListItemWeight);
			_viewHolder.txtV_shipmentDetailQueryResultListItemPiece = (TextView) convertView.findViewById(R.id.txtV_shipmentDetailQueryResultListItemPiece);
			convertView.setTag(_viewHolder);
		}else{
			_viewHolder = (ViewHolder) convertView.getTag();
		}
		
		ShipmentBean _shipBean = (ShipmentBean) getItem(position);
		_viewHolder.txtV_shipmentDetailQueryResultListItemPkgNo.setText(_shipBean.pkgNo);
		_viewHolder.txtV_shipmentDetailQueryResultListItemWeight.setText(_shipBean.weight);
		_viewHolder.txtV_shipmentDetailQueryResultListItemPiece.setText(_shipBean.piece);
		
		return convertView;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}


}
